package com.example.motilaltask.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.motilaltask.R
import com.example.motilaltask.api.RetroServiceInterface
import com.example.motilaltask.application.MyApplication
import com.example.motilaltask.database.dao.RepositoryDao
import com.example.motilaltask.model.RepositoryList
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DownloadWork(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel

    var retroService: RetroServiceInterface =
        (context.applicationContext as MyApplication).getRetroComponent().getRetroServiceInterface()

    val notificationTitle: String = "Repository Download"
    private val channelId = applicationContext.getString(R.string.notification_channel_id)
    private val description = applicationContext.getString(R.string.notification_title)

    @Inject
    lateinit var repositoryDao: RepositoryDao

    init {
        (context.applicationContext as MyApplication).getAppDbComponent().inject(this)
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val progress = "Downloading Repositories ..."
        showNotification(notificationTitle, progress)
        return@withContext getGithubData()
    }

    private fun getGithubData(): Result {
        val call: Call<RepositoryList>? = retroService.getDataFromAPI("india")
        call?.enqueue(object : Callback<RepositoryList> {
            override fun onResponse(
                call: Call<RepositoryList>,
                response: Response<RepositoryList>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items?.let {
                        if (it.isNotEmpty()) {
                            repositoryDao.deleteAll()
                            repositoryDao.insertRepositories(it)
                        }
                    }
                    cancelNotification()
                    Result.success()
                }
            }

            override fun onFailure(call: Call<RepositoryList>, t: Throwable) {
                cancelNotification()
                Result.failure()
            }
        })
        return Result.success()
    }

    private fun showNotification(notificationTitle: String, notificationText: String) {
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(applicationContext, channelId)
                .setContentText(notificationText)
                .setContentTitle(notificationTitle)
                .setSmallIcon(R.drawable.ic_launcher_background)
        } else {

            builder = Notification.Builder(applicationContext)
                .setContentText(notificationText)
                .setContentTitle(notificationTitle)
                .setSmallIcon(R.drawable.ic_launcher_background)
        }
        notificationManager.notify(channelId.toInt(), builder.build())
    }

    private fun cancelNotification() {
        notificationManager.cancel(channelId.toInt())
    }
}
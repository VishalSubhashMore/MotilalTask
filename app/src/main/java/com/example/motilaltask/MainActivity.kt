package com.example.motilaltask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.example.motilaltask.adapter.RepositoryRecyclerViewAdapter
import com.example.motilaltask.databinding.ActivityMainBinding
import com.example.motilaltask.viewmodel.MainViewModel
import com.example.motilaltask.work.DownloadWork
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerviewAdapter: RepositoryRecyclerViewAdapter

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        downloadData()

        viewModel.getAllRepositories().observe(this, {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            if (it != null) {
                recyclerviewAdapter.setList(it)
            } else {
                Toast.makeText(this, "Error in getting Data", Toast.LENGTH_SHORT).show()
            }
        })
        initRecyclerView()
    }

    private fun downloadData(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<DownloadWork>(15,TimeUnit.MINUTES)
                .setConstraints(constraints).build()

        WorkManager
            .getInstance(this.applicationContext)
            .enqueueUniquePeriodicWork("DownloadList",ExistingPeriodicWorkPolicy.REPLACE,uploadWorkRequest)
    }

    private fun initRecyclerView() {
        recyclerviewAdapter = RepositoryRecyclerViewAdapter()
        recyclerviewAdapter.setList(emptyList())
        binding.recyclerView.adapter = recyclerviewAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

}
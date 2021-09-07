package com.example.motilaltask.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repository")
data class Repository(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "srno")  val srno: Int = 0,
    @ColumnInfo(name = "repoId") @SerializedName("id") val repoId: Int,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String?,
    @ColumnInfo(name = "full_name") @SerializedName("full_name") val fullName: String?,
    @ColumnInfo(name = "description") @SerializedName("description") val description: String?,
    @ColumnInfo(name = "created_at") @SerializedName("created_at") val createdAt: String?,
    @ColumnInfo(name = "updated_at") @SerializedName("updated_at") val updatedAt: String?,
    @ColumnInfo(name = "pushed_at") @SerializedName("pushed_at") val pushedAt: String?,
    @ColumnInfo(name = "language") @SerializedName("language") val language: String?
)

package com.example.androidtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidtest.data.Media
import com.example.androidtest.data.User
import com.google.gson.annotations.SerializedName


data class Response(
		var feeds: List<FeedsModel>
)
@Entity(tableName = "FeedsModel")
data class FeedsModel (
		@PrimaryKey
		@SerializedName("id") val id : Int,
		@SerializedName("createdAt") val createdAt : String,
		@SerializedName("content") val content : String,
		@SerializedName("comments") val comments : Int,
		@SerializedName("likes") val likes : Int,
		@SerializedName("media") val media : List<Media>?,
		@SerializedName("user") val user : List<User>
)
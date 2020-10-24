package com.ansar.techbay.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comments(
    val postId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val email: String?,
    val body: String?
)
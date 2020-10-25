package com.ansar.techbay.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Posts::class,
        parentColumns = arrayOf("id"),
    childColumns = arrayOf("postId"),
    onDelete = ForeignKey.CASCADE)))
data class Comments(
    val postId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val email: String?,
    val body: String?
)
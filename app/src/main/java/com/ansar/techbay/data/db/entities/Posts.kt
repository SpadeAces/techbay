package com.ansar.techbay.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Posts(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String?,
    val body: String?
)
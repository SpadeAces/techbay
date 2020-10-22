package com.ansar.techbay.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Posts(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String?
)
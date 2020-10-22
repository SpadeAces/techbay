package com.ansar.techbay.data.network.responces

import com.ansar.techbay.data.db.entities.Posts


data class PostsResponse (
    val posts: List<Posts>
)
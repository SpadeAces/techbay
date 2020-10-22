package com.ansar.techbay.ui.Posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ansar.techbay.data.repostiory.PostsRepository

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(
    private val repository: PostsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repository) as T
    }
}
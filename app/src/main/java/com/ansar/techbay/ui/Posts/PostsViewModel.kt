package com.ansar.techbay.ui.Posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel;
import com.ansar.techbay.data.repostiory.PostsRepository
import com.ansar.techbay.util.lazyDeferred

@RequiresApi(Build.VERSION_CODES.O)
class PostsViewModel(
    repository: PostsRepository
) : ViewModel() {

    val posts by lazyDeferred {
        repository.setPosts()
    }
}

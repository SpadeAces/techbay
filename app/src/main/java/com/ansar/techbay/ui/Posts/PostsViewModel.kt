package com.ansar.techbay.ui.Posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel;
import com.ansar.techbay.data.repostiory.AppRepository
import com.ansar.techbay.util.lazyDeferred

@RequiresApi(Build.VERSION_CODES.O)
class PostsViewModel(
    repository: AppRepository
) : ViewModel() {

    val posts by lazyDeferred {
        repository.setPosts()
    }
}

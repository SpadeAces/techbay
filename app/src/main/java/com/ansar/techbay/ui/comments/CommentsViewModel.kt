package com.ansar.techbay.ui.comments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.ansar.techbay.data.db.entities.Posts
import com.ansar.techbay.data.preferences.PreferenceProvider
import com.ansar.techbay.data.repostiory.CommentsRepository
import com.ansar.techbay.data.repostiory.PostsRepository
import com.ansar.techbay.util.lazyDeferred

@RequiresApi(Build.VERSION_CODES.O)
class CommentsViewModel(
    repository: CommentsRepository
) : ViewModel() {

    val comments by lazyDeferred {
        repository.setComments()
    }
}
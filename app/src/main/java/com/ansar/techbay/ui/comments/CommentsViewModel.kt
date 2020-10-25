package com.ansar.techbay.ui.comments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel;
import com.ansar.techbay.data.repostiory.AppRepository
import com.ansar.techbay.util.lazyDeferred

@RequiresApi(Build.VERSION_CODES.O)
class CommentsViewModel(
    repository: AppRepository
) : ViewModel() {

    val comments by lazyDeferred {
        repository.setComments()
    }
}
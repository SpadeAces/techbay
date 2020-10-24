package com.ansar.techbay.data.repostiory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ansar.techbay.data.db.AppDatabase
import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.data.db.entities.Posts
import com.ansar.techbay.data.network.MyApi
import com.ansar.techbay.data.network.SafeApiRequest
import com.ansar.techbay.data.preferences.PreferenceProvider
import com.ansar.techbay.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


@RequiresApi(Build.VERSION_CODES.O)
class CommentsRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val comments = MutableLiveData<List<Comments>>()
    init {
        comments.observeForever {
            saveComments(it)
        }
    }

    suspend fun setComments(): LiveData<List<Posts>> {
        return withContext(Dispatchers.IO) {

            fetchComments(prefs.getPostId() as Int)
            db.getPostsDao().getPosts()
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchComments(postId: Int) {
           try {
                val response = apiRequest { api.getComments(postId) }
                comments.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveComments(comments: List<Comments>) {
        Coroutines.io {
            db.getCommentsDao().saveAllComments(comments)
        }
    }
}
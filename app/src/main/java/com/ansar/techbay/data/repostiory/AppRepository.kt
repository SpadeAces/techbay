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
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

@RequiresApi(Build.VERSION_CODES.O)
class AppRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {


    //AppRepository
    private val posts = MutableLiveData<List<Posts>>()
    init {
        posts.observeForever {
            savePosts(it)
        }
    }

    suspend fun setPosts(): LiveData<List<Posts>> {
        return withContext(Dispatchers.IO) {
            fetchPosts()
            db.getPostsDao().getPosts()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchPosts() {
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            try {
                val response = apiRequest { api.getPosts() }
                posts.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun savePosts(posts: List<Posts>) {
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getPostsDao().saveAllPosts(posts)
        }
    }


    //Comments
    private val comments = MutableLiveData<List<Comments>>()
    init {
        comments.observeForever {
            saveComments(it)
        }
    }

    suspend fun setComments(): LiveData<List<Comments>> {
        return withContext(Dispatchers.IO) {

            fetchComments(prefs.getPostId())
            db.getCommentsDao().getComments(prefs.getPostId().toInt())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchComments(postId: String) {
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
package com.ansar.techbay.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ansar.techbay.data.db.entities.Posts

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPosts(quotes : List<Posts>)

    @Query("SELECT * FROM Posts")
    fun getPosts() : LiveData<List<Posts>>

}
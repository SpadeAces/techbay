package com.ansar.techbay.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.data.db.entities.Posts

@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllComments(comments : List<Comments>)

    @Query("SELECT * FROM Comments")
    fun getComments() : LiveData<List<Comments>>

}
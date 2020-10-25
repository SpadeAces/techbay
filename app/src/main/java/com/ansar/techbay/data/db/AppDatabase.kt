package com.ansar.techbay.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.data.db.entities.Posts

@Database(
    entities = [Posts::class, Comments::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostsDao(): PostDao

    abstract fun getCommentsDao(): CommentsDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}
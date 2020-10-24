package com.ansar.techbay.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"
private const val KEY_POST_ID = "key_post_id"

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(savedAt: String) {
        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastSavedAt(): String? {
        return preference.getString(KEY_SAVED_AT, null)
    }

    fun savePostId(postId: String){
        preference.edit().putString(
            KEY_SAVED_AT,
            postId
        ).apply()
    }

    fun getPostId(): String{
        return preference.getString(KEY_POST_ID,null)!!
    }
}
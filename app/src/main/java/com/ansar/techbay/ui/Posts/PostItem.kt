package com.ansar.techbay.ui.Posts

import com.ansar.techbay.R
import com.ansar.techbay.data.db.entities.Posts
import com.xwray.groupie.databinding.BindableItem
import com.ansar.techbay.databinding.ItemPostBinding

class PostItem(
    private val post: Posts
) : BindableItem<ItemPostBinding>(){

    override fun getLayout() = R.layout.item_post

    override fun bind(viewBinding: ItemPostBinding, position: Int) {
        viewBinding.setPosts(post)
    }
}
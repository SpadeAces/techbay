package com.ansar.techbay.ui.comments

import com.ansar.techbay.R
import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.databinding.ItemCommentBinding
import com.xwray.groupie.databinding.BindableItem

class CommentsItem(
    private val comments: Comments
) : BindableItem<ItemCommentBinding>(){

    override fun getLayout() = R.layout.item_comment

    override fun bind(viewBinding: ItemCommentBinding, position: Int) {
        viewBinding.setComments(comments)
    }
}
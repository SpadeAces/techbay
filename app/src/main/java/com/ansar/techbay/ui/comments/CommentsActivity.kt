package com.ansar.techbay.ui.comments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansar.techbay.R
import com.ansar.techbay.data.db.entities.Comments
import com.ansar.techbay.databinding.ActivityCommentsBinding
import com.ansar.techbay.ui.Posts.PostItem
import com.ansar.techbay.util.Coroutines
import com.ansar.techbay.util.hide
import com.ansar.techbay.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.progress_bar
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware

class CommentsActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: CommentsViewModelFactory by instance()

    private val POSTTITLE: String = "PostTitle"
    private val POSTBODY: String = "PostBody"

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var viewModel: CommentsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)
        viewModel = ViewModelProvider(this, factory).get(CommentsViewModel::class.java)

        val postTitle :  String = intent.getStringExtra(POSTTITLE)
        val postBody :  String = intent.getStringExtra(POSTBODY)

        post_title.setText(postTitle)
        post_body.setText(postBody)

        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.comments.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toCommentItem())
        })
    }

    private fun initRecyclerView(commentsItem: List<CommentsItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(commentsItem)
        }

        commentsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = mAdapter
        }
    }

    private fun List<Comments>.toCommentItem() : List<CommentsItem>{
        return this.map {
            CommentsItem(it)
        }
    }
}

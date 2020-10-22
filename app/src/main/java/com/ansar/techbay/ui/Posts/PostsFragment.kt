package com.ansar.techbay.ui.Posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansar.techbay.R
import com.ansar.techbay.data.db.entities.Posts
import com.ansar.techbay.util.Coroutines
import com.ansar.techbay.util.hide
import com.ansar.techbay.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.posts_fragment.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PostsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: PostsViewModelFactory by instance()
    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)
        bindUI()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.posts.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(postItem: List<PostItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(postItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
            this.adapter = mAdapter
        }
    }

    private fun List<Posts>.toQuoteItem() : List<PostItem>{
        return this.map {
            PostItem(it)
        }
    }
}

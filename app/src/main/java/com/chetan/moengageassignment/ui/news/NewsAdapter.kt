package com.chetan.moengageassignment.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chetan.moengageassignment.databinding.NoteItemBinding
import com.chetan.moengageassignment.models.NewsListResponse

class NewsAdapter(private val onNoteClicked: (NewsListResponse.Article) -> Unit) :
    ListAdapter<NewsListResponse.Article, NewsAdapter.NoteViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsListResponse.Article) {
            binding.title.text = article.title
            binding.desc.text = article.description

            Glide.with(binding.root.context).load(article.urlToImage).into(binding.ivNews);

            binding.root.setOnClickListener {
                onNoteClicked(article)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<NewsListResponse.Article>() {
        override fun areItemsTheSame(oldItem: NewsListResponse.Article, newItem: NewsListResponse.Article): Boolean {
            return oldItem.source.id == newItem.source.id
        }

        override fun areContentsTheSame(oldItem: NewsListResponse.Article, newItem: NewsListResponse.Article): Boolean {
            return oldItem == newItem
        }
    }
}
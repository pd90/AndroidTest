package com.example.androidtest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidtest.R
import com.example.androidtest.data.FeedsModel
import kotlinx.android.synthetic.main.item_list_header.view.*
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(news: FeedsModel?, position: Int?) {
        if (news != null) {
            itemView.txt_article_content.text = news.content
            itemView.likes.text = news.likes.toString()
            itemView.comments.text = news.comments.toString()

            if(news.media?.size!! >0) {
                if (!news.media[0].image.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                            .load(news.media[0].image)
                            .centerCrop().placeholder(R.drawable.placeholder)
                            .diskCacheStrategy( DiskCacheStrategy.ALL )
                            .into(itemView.img_article)
                }
                itemView.txt_article_title.text = news.media[0].title
                itemView.txt_article_url.text = news.media[0].url
            }else{
                itemView.img_article.setImageResource(R.drawable.placeholder)
            }

            if(news.user.isNotEmpty()) {
                if (!news.user[0].avatar.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                            .load(news.user[0].avatar)
                            .centerCrop().placeholder(R.drawable.placeholder)
                            .diskCacheStrategy( DiskCacheStrategy.ALL )
                            .into(itemView.img_user)
                }
                itemView.text_username.text = "${news.user[0].name} ${news.user[0].lastname}"
                itemView.text_designation.text = news.user[0].designation
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }

}
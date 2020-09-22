package com.example.androidtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidtest.R
import com.example.androidtest.data.FeedsModel
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: FeedsModel?,position: Int?) {
        if (news != null) {
            itemView.txt_news_name.text = news.content
            if(news.media?.size!! >0) {
                if (!news.media[0].image.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                            .load(news.media[0].image)
                            .centerCrop().placeholder(R.drawable.placeholder)
                            .diskCacheStrategy( DiskCacheStrategy.ALL )
                            .into(itemView.img_article)
                }
            }else{
                itemView.img_article.setImageResource(R.drawable.placeholder)
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
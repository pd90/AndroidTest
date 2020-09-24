package com.example.androidtest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidtest.R
import com.example.androidtest.data.FeedsModel
import kotlinx.android.synthetic.main.item_list_header.view.*
import kotlinx.android.synthetic.main.item_news.view.*
import kotlin.math.abs

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(news: FeedsModel?) {
        if (news != null) {
            itemView.txt_article_content.text = news.content
            itemView.likes.text = parseLikes(news.likes)
            itemView.comments.text = parseComments(news.comments)

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

    fun parseLikes(input: Int?):String?{
        var formatString = ""
        formatString = when {
            input?.div(1000000)?.let { abs(it) }!! > 1 -> {
                (input / 1000000).toString() + "m Likes"
            }
            abs(input / 1000) > 1 -> {
                (input / 1000).toString() + "k Likes"
            }
            else -> {
                "$input Likes"
            }
        }
       return formatString
    }

    fun parseComments(input: Int?):String?{
        var formatString = ""
        formatString = when {
            input?.div(1000000)?.let { abs(it) }!! > 1 -> {
                (input / 1000000).toString() + "m Comments"
            }
            abs(input / 1000) > 1 -> {
                (input / 1000).toString() + "k Comments"
            }
            else -> {
                "$input Comments"
            }
        }
        return formatString
    }
}
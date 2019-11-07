package com.example.newstest.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import com.example.newstest.NewsTestApplication
import com.example.newstest.R
import com.example.newstest.common.AppConstants
import com.example.newstest.model.Rows

class NewsListAdapter(val articleList: List<Rows>) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.bindItems(articleList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return articleList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(article: Rows) {
            val textViewTitle = itemView.findViewById(R.id.tv_news_title) as TextView
            val textViewDescription  = itemView.findViewById(R.id.tv_news_description) as TextView
            val networkImageView  = itemView.findViewById(R.id.iv_news_thumb) as NetworkImageView
            textViewTitle.text = if (article?.title != null) article?.title else AppConstants.PLACEHOLDER_NO_TITLE
           textViewDescription.text = if (article?.description != null) article?.description else AppConstants.PLACEHOLDER_NO_TITLE

            if(!TextUtils.isEmpty(article?.imageHref))
                networkImageView.setImageUrl(article.imageHref, NewsTestApplication.instance?.imageLoader)

            networkImageView.setDefaultImageResId(R.drawable.ic_no_thumbnail);
            networkImageView.setErrorImageResId(R.drawable.ic_no_thumbnail);
        }
    }
}
package com.example.countryinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstest.R
import com.example.countryinfo.model.Rows

/**
 *
 * Adapter to load country info into each list item of RecyclerView
 *
 */
class CountryInfoListAdapter(val articleList: List<Rows>) : RecyclerView.Adapter<CountryInfoListAdapter.ViewHolder>() {
    //return a view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return ViewHolder(v)
    }

    //binding the data to the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(articleList[position])
    }

    //list size
    override fun getItemCount(): Int {
        return articleList.size
    }

    //the class is holding the list view and loads data into the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(article: Rows) {
            val textViewTitle = itemView.findViewById(R.id.tv_country_info_title) as TextView
            val textViewDescription  = itemView.findViewById(R.id.tv_country_info_description) as TextView
            val thumbnailImageView  = itemView.findViewById(R.id.iv_country_info_thumb) as ImageView

            // load data into text views from article(Rows) model class which is preloaded with server data
            textViewTitle.text = if (article?.title != null) article?.title else itemView.context.getString(R.string.placeholder_no_news_title)
            textViewDescription.text = if (article?.description != null) article?.description else itemView.context.getString(R.string.placeholder_no_news_title)

            // load image for url into the thumbnailImageView using Glide 3rd party library
             Glide
                .with(itemView)
                .load(article?.imageHref)
                .centerCrop()
                .placeholder(R.drawable.ic_no_thumbnail)
                .into(thumbnailImageView)
        }
    }
}
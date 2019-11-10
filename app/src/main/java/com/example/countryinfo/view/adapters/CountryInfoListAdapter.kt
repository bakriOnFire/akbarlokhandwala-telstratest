package com.example.countryinfo.view.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstest.R
import com.example.countryinfo.model.Rows
import kotlinx.android.synthetic.main.list_item.view.*

/**
 *
 * Adapter to load country info into each list item of RecyclerView
 *
 */
class CountryInfoListAdapter : RecyclerView.Adapter<CountryInfoListAdapter.ViewHolder>() {

    private var _articleList = emptyList<Rows>()

    //return a view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    fun setCountryInfoList(articleList: List<Rows>) {
        _articleList = articleList
        notifyDataSetChanged()
    }

    //binding the data to the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(_articleList[position])
    }

    //list size
    override fun getItemCount(): Int {
        return _articleList?.size
    }

    //the class is holding the list view and loads data into the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(article: Rows) {

            // load data into text views from article(Rows) model class which is preloaded with server data
            itemView.tv_country_info_title.text = if (!TextUtils.isEmpty(article?.title)) article?.title else itemView.context.getString(R.string.placeholder_no_news_title)
            itemView.tv_country_info_description.text = if (!TextUtils.isEmpty(article?.description)) article?.description else itemView.context.getString(R.string.placeholder_no_news_title)

            // load image for url into the thumbnailImageView using Glide 3rd party library
             Glide
                .with(itemView)
                .load(article?.imageHref)
                .centerCrop()
                .placeholder(R.drawable.ic_no_thumbnail)
                .into(itemView.iv_country_info_thumb)
        }
    }
}
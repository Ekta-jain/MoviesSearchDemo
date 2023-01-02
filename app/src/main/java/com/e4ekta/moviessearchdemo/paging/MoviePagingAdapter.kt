package com.e4ekta.moviessearchdemo.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e4ekta.moviessearchdemo.Constants
import com.e4ekta.moviessearchdemo.R


class MoviePagingAdapter : PagingDataAdapter<com.e4ekta.moviessearchdemo.models.MovieData, MoviePagingAdapter.QuoteViewHolder>(
    COMPARATOR) {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieName: TextView = itemView.findViewById<TextView>(R.id.movieName)
        val posterImage = itemView.findViewById<AppCompatImageView>(R.id.posterImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.movieName.isSelected = true
        holder.movieName.text = item?.name
        /*Getting image url from local server*/
        val url = Constants.BASE_URL+Constants.IMAGES_FOLDER+item?.posterImage
        if (url.isNotEmpty()) {
            Glide.with(holder.posterImage.context).load(url).into(holder.posterImage)
        }
    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<com.e4ekta.moviessearchdemo.models.MovieData>(){
            override fun areItemsTheSame(oldItem: com.e4ekta.moviessearchdemo.models.MovieData, newItem: com.e4ekta.moviessearchdemo.models.MovieData): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: com.e4ekta.moviessearchdemo.models.MovieData, newItem: com.e4ekta.moviessearchdemo.models.MovieData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
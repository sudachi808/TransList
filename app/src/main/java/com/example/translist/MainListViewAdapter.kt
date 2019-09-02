package com.example.translist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_list_item.view.*
import java.io.Serializable

class MainListViewAdapter(private val context: Context) : RecyclerView.Adapter<MainListViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.titleTextView
        val image: ImageView = itemView.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener { v ->
            this.onClickListener?.onClick(v, holder.adapterPosition)
        }

        return holder
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.items.elementAt(position).let {
            holder.title.text = it.title
            Picasso.get().load(it.imageUrl).into(holder.image)
        }
    }

    // region リスナー

    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setOnClickListener(l: OnClickListener) {
        this.onClickListener = l
    }

    // endregion

    class Item(
        val title: String,
        val imageUrl: String
    ) : Serializable

    var items = listOf(
        Item(title = "岳沢湿原", imageUrl = "file:///android_asset/photo_1.jpg"),
        Item(title = "明神橋", imageUrl = "file:///android_asset/photo_2.jpg"),
        Item(title = "大正池", imageUrl = "file:///android_asset/photo_3.jpg"),
        Item(title = "大正池", imageUrl = "file:///android_asset/photo_3.jpg"),
        Item(title = "大正池", imageUrl = "file:///android_asset/photo_3.jpg")
    )

}
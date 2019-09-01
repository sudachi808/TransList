package com.example.translist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.main_list_item.view.*
import java.io.Serializable

class MainListViewAdapter(context: Context) : BaseAdapter() {

    class Item(
        val title: String,
        val imageResId: Int
    ) : Serializable

    var items = listOf(
        Item(title = "岳沢湿原", imageResId = R.drawable.photo_1),
        Item(title = "明神橋", imageResId = R.drawable.photo_2),
        Item(title = "大正池", imageResId = R.drawable.photo_3)
    )

    private val inflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val newConvertView = convertView ?: this.inflater.inflate(R.layout.main_list_item, null)

        this.items.elementAt(position).let {
            with(newConvertView) {
                titleTextView.text = it.title
                imageView.setImageDrawable(context.getDrawable(it.imageResId))
            }
        }

        return newConvertView
    }

    override fun getItem(position: Int): Any {
        return this.items.elementAt(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.count()
    }
}
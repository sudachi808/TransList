package com.example.translist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val adapter = MainListViewAdapter(this)
        this.listView.adapter = adapter
        this.listView.setOnItemClickListener { _, view, position, _ ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_KEY_ITEM, adapter.items[position])
            }
            val option = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,
                    Pair(view.findViewById(R.id.imageView), "imageTransition"),
                    Pair(view.findViewById(R.id.titleTextView), "titleTransition")
                )
                .toBundle()
            startActivity(intent, option)
        }
    }
}

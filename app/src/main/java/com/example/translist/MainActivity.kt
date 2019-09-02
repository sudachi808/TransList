package com.example.translist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        val adapter = MainListViewAdapter(this)
//        this.listView.adapter = adapter
//        this.listView.setOnItemClickListener { _, view, position, _ ->
//            println("_/_/ onItemClick")
//            val intent = Intent(this, DetailActivity::class.java).apply {
//                putExtra(DetailActivity.EXTRA_KEY_ITEM, adapter.items[position])
//            }
//            val option = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(this,
//                    Pair(view.findViewById(R.id.imageView), "imageTransition"),
//                    Pair(view.findViewById(R.id.titleTextView), "titleTransition")
//                )
//                .toBundle()
//            startActivity(intent, option)
//        }


        this.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MainListViewAdapter(this)
        adapter.setOnClickListener(object : MainListViewAdapter.OnClickListener {
            override fun onClick(v: View, position: Int) {
                val item = adapter.items[position]
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_KEY_ITEM, item)
                }
                val option = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(
                        this@MainActivity,
                        Pair(v.findViewById(R.id.imageView), "imageTransition"),
                        Pair(v.findViewById(R.id.titleTextView), "titleTransition")
                    )
                    .toBundle()
                startActivity(intent, option)
            }
        })

        this.recyclerView.adapter = adapter
    }
}

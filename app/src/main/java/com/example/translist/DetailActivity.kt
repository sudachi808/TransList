package com.example.translist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY_ITEM = "EXTRA_KEY_ITEM"
    }

    private val item
        get() = this.intent.getSerializableExtra(EXTRA_KEY_ITEM) as? MainListViewAdapter.Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        this.item?.let {
            println("_/_/ ${it.title}")
            this.imageView.setImageDrawable(getDrawable(it.imageResId))
        }
    }
}
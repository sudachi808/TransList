package com.example.translist

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.*
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.detail_activity.*
import kotlin.math.abs

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY_ITEM = "EXTRA_KEY_ITEM"
    }

    private val item
        get() = this.intent.getSerializableExtra(EXTRA_KEY_ITEM) as? MainListViewAdapter.Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.sharedElementEnterTransition = DetailTransitionSet()

        setContentView(R.layout.detail_activity)

        this.setSupportActionBar(this.toolbar)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.item?.let {
            this.titleTextView.text = it.title
            this.imageView.setImageDrawable(getDrawable(it.imageResId))
        }

        findViewById<View>(android.R.id.content).systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class DetailTransitionSet : TransitionSet() {

        init {
            this.addTransition(ChangeBounds())
                .addTransition(ChangeTransform())
                .addTransition(ChangeClipBounds())
                .addTransition(ChangeImageTransform())
                .addTransition(DetailTransition().addTarget(R.id.titleTextView))
        }
    }

    class DetailTransition : Transition() {

        override fun captureStartValues(transitionValues: TransitionValues?) {
        }

        override fun captureEndValues(transitionValues: TransitionValues?) {
        }

        override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues, endValues: TransitionValues): Animator {

            val view = startValues.view

            return ObjectAnimator.ofFloat(view, View.ALPHA.name, view.alpha, abs(view.alpha - 1.0f)).setDuration(300)
//            ani.addListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationStart(animation: Animator?) {
//                    println("_/_/ onAnimationStart")
//                    super.onAnimationStart(animation)
//                }
//                override fun onAnimationEnd(animation: Animator?) {
//                    println("_/_/ onAnimationEnd")
//                    super.onAnimationEnd(animation)
//                }
//            })
//            return ani
        }
    }
}
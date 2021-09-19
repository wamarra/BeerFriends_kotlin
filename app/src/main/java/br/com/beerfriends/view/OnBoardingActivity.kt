package br.com.beerfriends.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_onboarding.*
import br.com.beerfriends.R
import br.com.beerfriends.config.AppPrefs
import br.com.beerfriends.view.adapter.SliderAdapter

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: SliderAdapter
    private var dots: Array<TextView?>? = null
    private lateinit var layouts: Array<Int>
    private val sliderChangeListener = object : OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            if (position == layouts.size.minus(1)) {
                nextBtn.hide()
                skipBtn.hide()
                startBtn.show()
            } else {
                nextBtn.show()
                skipBtn.show()
                startBtn.hide()
            }
        }

        override fun onPageScrollStateChanged(state: Int) { }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        init()
        dataSet()
        interactions()
    }

    private fun init() {
        layouts = arrayOf(
            R.layout.onboarding_slide1,
            R.layout.onboarding_slide2,
            R.layout.onboarding_slide3
        )

        sliderAdapter = SliderAdapter(this, layouts)
    }

    private fun dataSet() {
        addBottomDots(0)

        slider.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun interactions() {
        skipBtn.setOnClickListener {
            navigateToAuthentication()
        }

        startBtn.setOnClickListener {
            navigateToAuthentication()
        }

        nextBtn.setOnClickListener {
            val current = getCurrentScreen(+1)
            if (current < layouts.size) {
                slider.currentItem = current
            } else {
                navigateToAuthentication()
            }
        }
    }

    private fun navigateToAuthentication() {
        AppPrefs(this).setFirstTimeLaunch(false)
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size)

        dotsLayout.removeAllViews()
        for (i in dots!!.indices) {
            dots!![i] = TextView(this)
            dots!![i]?.text = HtmlCompat.fromHtml( "&#8226;", HtmlCompat.FROM_HTML_MODE_LEGACY)
            dots!![i]?.textSize = 35f
            dots!![i]?.setTextColor(ContextCompat.getColor(this, R.color.secondary))
            dotsLayout.addView(dots!![i])
        }

        if (dots!!.isNotEmpty()) {
            dots!![currentPage]?.setTextColor(ContextCompat.getColor(this, R.color.primary))
        }
    }

    private fun getCurrentScreen(i: Int): Int = slider.currentItem.plus(i)

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.GONE
    }
}

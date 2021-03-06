package com.zackratos.ultimatebarx.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.sample.extension.getStatusBarHeight
import kotlinx.android.synthetic.main.fragment_scroll.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  10:34 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class ScrollActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_scroll)
        toolbar.title = "九阴真经"
        toolbar.post {
            toolbar.layoutParams = (toolbar.layoutParams as FrameLayout.LayoutParams)
                .apply { topMargin = getStatusBarHeight() }
        }
        UltimateBarX.create(UltimateBarX.STATUS_BAR).transparent().apply(this)
        scrollView.setOnScrollChangeListener { _: NestedScrollView?, _, scrollY: Int, _, oldScrollY: Int ->
            val height = imageView.height - getStatusBarHeight() - toolbar.height
            if (height in (oldScrollY + 1)..scrollY) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .fitWindow(false)
                    .bgColor(Color.WHITE)
                    .light(true)
                    .apply(this)
                toolbar.visibility = View.VISIBLE
            } else if (height in (scrollY + 1)..oldScrollY) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .transparent()
                    .apply(this)
                toolbar.visibility = View.INVISIBLE
            }
        }
    }

}
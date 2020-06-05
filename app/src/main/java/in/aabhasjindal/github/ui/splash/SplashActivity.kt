package `in`.aabhasjindal.github.ui.splash

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.databinding.ActivitySplashBinding
import `in`.aabhasjindal.github.ui.base.BaseActivity
import `in`.aabhasjindal.github.utils.navigateToSearchActivity
import android.os.Bundle

class SplashActivity : BaseActivity() {
    private val binding: ActivitySplashBinding by lazyBinding()

    override val layoutResource = R.layout.activity_splash
    override fun getViewModel(): Nothing? = null

    override fun setBindings() {}

    override fun initUi(savedInstanceState: Bundle?) {
        binding.linearLayout.postDelayed({
            navigateToSearchActivity(null)
        }, 2000)
    }

    override fun setEventHandler() {}
}
package com.merseyside.testsharedproject.presentation.view.activity.mainActivity.view

import android.os.Bundle
import com.merseyside.testsharedproject.BR
import com.merseyside.testsharedproject.R
import com.merseyside.testsharedproject.TestClass
import com.merseyside.testsharedproject.presentation.base.BaseYobitActivity
import com.merseyside.testsharedproject.presentation.view.activity.mainActivity.model.MainViewModel
import com.merseyside.testsharedproject.databinding.ActivityMainBinding
import com.merseyside.testsharedproject.presentation.di.component.DaggerMainComponent
import com.merseyside.testsharedproject.presentation.di.module.MainActivityModule

class MainActivity : BaseYobitActivity<ActivityMainBinding, MainViewModel>() {
    override fun setBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun clear() {}

    override fun loadingObserver(isLoading: Boolean) {}

    override fun performInjection() {
        DaggerMainComponent.builder()
            .appComponent(getAppComponent())
            .mainActivityModule(getMainActivityModule())
            .build().inject(this)
    }

    private fun getMainActivityModule() : MainActivityModule {
        return MainActivityModule(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

package com.merseyside.testsharedproject.presentation.base

import androidx.databinding.ViewDataBinding
import com.merseyside.testsharedproject.TestSharedApplication
import com.merseyside.testsharedproject.presentation.di.component.AppComponent
import com.upstream.basemvvmimpl.presentation.activity.BaseMvvmActivity

abstract class BaseYobitActivity<B : ViewDataBinding, M : BaseYobitViewModel> : BaseMvvmActivity<B, M>() {

    fun getAppComponent(): AppComponent {
        return TestSharedApplication.getInstance().appComponent
    }
}
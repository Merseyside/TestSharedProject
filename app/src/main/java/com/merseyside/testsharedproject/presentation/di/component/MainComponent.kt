package com.merseyside.testsharedproject.presentation.di.component

import com.merseyside.testsharedproject.presentation.di.module.MainActivityModule
import com.merseyside.testsharedproject.presentation.view.activity.mainActivity.view.MainActivity
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [MainActivityModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
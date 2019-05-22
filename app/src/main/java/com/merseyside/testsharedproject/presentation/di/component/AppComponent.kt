package com.merseyside.testsharedproject.presentation.di.component

import android.app.Application
import android.content.Context
import com.merseyside.testsharedproject.TestSharedApplication
import com.merseyside.testsharedproject.presentation.di.module.AppModule
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: TestSharedApplication)

    @ApplicationContext
    fun context() : Context

    fun application() : Application
}
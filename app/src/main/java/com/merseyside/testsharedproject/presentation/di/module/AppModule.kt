package com.merseyside.testsharedproject.presentation.di.module

import android.app.Application
import android.content.Context
import com.merseyside.testsharedproject.TestSharedApplication
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: TestSharedApplication) {

    @Provides
    @ApplicationContext
    fun provideContext() : Context {
        return application
    }

    @Provides
    fun provideApplication() : Application {
        return application
    }


}
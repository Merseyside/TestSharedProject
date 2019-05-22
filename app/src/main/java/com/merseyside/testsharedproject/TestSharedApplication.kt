package com.merseyside.testsharedproject

import android.app.Application
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.merseyside.testsharedproject.data.db.CoinDatabase
import com.merseyside.testsharedproject.di.sqlDriver
import com.merseyside.testsharedproject.presentation.di.component.AppComponent
import com.merseyside.testsharedproject.presentation.di.component.DaggerAppComponent
import com.merseyside.testsharedproject.presentation.di.module.AppModule
import com.squareup.sqldelight.android.AndroidSqliteDriver

class TestSharedApplication : Application() {

    companion object {

        private lateinit var instance: TestSharedApplication

        fun getInstance() : TestSharedApplication {
            return instance
        }
    }

    lateinit var appComponent : AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        initDB()

        appComponent = buildComponent()
        appComponent.inject(this)

    }

    private fun initDB() {
        val config = SupportSQLiteOpenHelper.Configuration.builder(this)
            .name("database.db")
            .callback(object : SupportSQLiteOpenHelper.Callback(1) {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    val driver = AndroidSqliteDriver(db)
                    CoinDatabase.Schema.create(driver)
                }

                override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                }

            })
            .build()

        val sqlHelper = FrameworkSQLiteOpenHelperFactory().create(config)

        sqlDriver = AndroidSqliteDriver(sqlHelper)
    }

    private fun buildComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
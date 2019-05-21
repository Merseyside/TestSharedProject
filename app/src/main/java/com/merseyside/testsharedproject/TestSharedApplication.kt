package com.merseyside.testsharedproject

import android.app.Application
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.merseyside.testsharedproject.data.db.CoinDatabase
import com.merseyside.testsharedproject.di.sqlDriver
import com.squareup.sqldelight.android.AndroidSqliteDriver

class TestSharedApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDB()

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
}
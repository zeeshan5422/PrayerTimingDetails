package com.zues.islamic.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.zues.islamic.MyApp
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import com.zues.islamic.utils.TestClass
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(val myApp: MyApp) {

    @Singleton
    @Provides
    fun provideTestClass() = TestClass()

    @Singleton
    @Provides
    fun provideContext(): Context = myApp

    @Singleton
    @Provides
    fun provideApp() : MyApp = myApp
}

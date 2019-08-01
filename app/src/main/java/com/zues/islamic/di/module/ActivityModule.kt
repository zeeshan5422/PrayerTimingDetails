package com.zues.islamic.di.module

import com.zues.islamic.di.scope.PerActivity
import com.zues.islamic.ui.main.MainActivity
import com.zues.islamic.ui.main.MainActivityContract
import com.zues.islamic.ui.main.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule {


    @PerActivity
    @Provides
    fun provideActivity(mainActivity: MainActivity) = mainActivity
/*
    @PerActivity
    @Provides
    fun provideTestClass() = TestClass()*/

    @Provides
    fun providePresenter(): MainActivityContract.Presenter {
        return MainPresenter()
    }

}

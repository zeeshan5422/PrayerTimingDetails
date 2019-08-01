package com.zues.islamic.di.component

import android.content.Context
import com.zues.islamic.MyApp
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import com.zues.islamic.di.module.ActivityModule
import com.zues.islamic.di.module.RoomModule
import com.zues.islamic.di.scope.PerActivity
import com.zues.islamic.ui.main.MainActivity
import dagger.Component


/* ---  Created by akhtarz on 6/27/2019. ---*/
@PerActivity
@Component(
    dependencies = [
        AppComponent::class], modules = arrayOf(
        ActivityModule::class
    )
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun injectDatabase(): DB

    fun injectMyDbInterface(): MyDBInterface

    @Component.Builder
    interface Builder {

        fun appModule(appComponent: AppComponent): Builder

//        fun activityModule(activityComponent: ActivityComponent): Builder

        fun build(): ActivityComponent
    }

}
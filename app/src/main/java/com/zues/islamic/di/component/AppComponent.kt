package com.zues.islamic.di.component

import com.zues.islamic.MyApp
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import com.zues.islamic.di.module.AppModule
import com.zues.islamic.di.module.RoomModule
import dagger.Component
import javax.inject.Singleton

/* ---  Created by akhtarz on 6/27/2019. ---*/

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        RoomModule::class
    )
)
interface AppComponent {

    fun inject(myApp: MyApp)

    fun injectDatabase(): DB

    fun injectMyDbInterface(): MyDBInterface

}
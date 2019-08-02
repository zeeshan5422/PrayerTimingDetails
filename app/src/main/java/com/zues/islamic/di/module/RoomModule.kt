package com.zues.islamic.di.module

import android.arch.persistence.room.Room
import com.zues.islamic.MyApp
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.db.MyDBInterface
import com.zues.islamic.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* ---  Created by akhtarz on 6/28/2019. ---*/

@Module
class RoomModule {

   /* @PerActivity
    @Provides
    fun provideDbInstance(context: MyApp): DB {
        return MyDBInterface.createDB(context)
    }

    @PerActivity
    @Provides
    fun provideMyDBInstance(myDB: DB): MyDBInterface {
        return MyDBInterface.createMyDBInterface(myDB)
    }*/

    @Singleton
    @Provides
    fun provideRoomModule(context: MyApp): DB {
        return Room.databaseBuilder(context,
            DB::class.java ,"zues.db")
            .allowMainThreadQueries()
            .addMigrations(DB.MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: DB): MyDBInterface {
        return demoDatabase.myDbDao()
    }
}
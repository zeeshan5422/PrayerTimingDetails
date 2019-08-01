package com.zues.islamic.di.module

import com.zues.islamic.data.network.MyApiInterface
import com.zues.islamic.utils.Constants
import com.zues.islamic.utils.TestClass
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {


/*    @Singleton
    @Provides
    fun provideApiService(): MyApiInterface = MyApiInterface.create()*/

    /*@Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.baseURL)
            .build()
    }

    @Provides
    internal fun provideApi(retrofit: Retrofit): MyApiInterface {
        return retrofit.create<MyApiInterface>(MyApiInterface::class.java)
    }*/

}

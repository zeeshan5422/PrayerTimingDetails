package com.zues.islamic.di.component

import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.di.module.FragmentModule
import com.zues.islamic.di.scope.PerFragment
import com.zues.islamic.ui.MainFragment.MainFragment
import com.zues.islamic.ui.dateTime.DateTimeFragment
import com.zues.islamic.ui.monthlyTiming.MonthlyTimingFragment
import com.zues.islamic.ui.prayerTiming.PrayerTimingFragment
import dagger.Component

/* ---  Created by akhtarz on 6/27/2019. ---*/
@PerFragment
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(dateTimeFragment: DateTimeFragment)

    fun inject(prayerTimingFragment: PrayerTimingFragment)

    fun inject(monthlyTimingFragment: MonthlyTimingFragment)

    @Component.Builder
    interface Builder {

//        fun appModule(appComponent: AppComponent?): Builder

        fun activityModule(activityComponent: ActivityComponent): Builder

        fun build(): FragmentComponent
    }
}
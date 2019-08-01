package com.zues.islamic.di.module

import android.content.Context
import com.raza.android.videocompressor.fragments.BaseFragment
import com.zues.islamic.data.db.DB
import com.zues.islamic.data.network.MyApiInterface
import com.zues.islamic.di.scope.PerFragment
import com.zues.islamic.ui.MainFragment.MainFragmentContract
import com.zues.islamic.ui.MainFragment.MainFragmentPresenter
import com.zues.islamic.ui.dateTime.DateTimeFragmentContract
import com.zues.islamic.ui.dateTime.DateTimeFragmentPresenter
import com.zues.islamic.ui.main.MainActivity
import com.zues.islamic.ui.monthlyTiming.MonthlyTimingFragmentContract
import com.zues.islamic.ui.monthlyTiming.MonthlyTimingPresenter
import com.zues.islamic.ui.prayerTiming.PrayerTimingFragContract
import com.zues.islamic.ui.prayerTiming.PrayerTimingPresenter
import com.zues.islamic.views.LoadingDialog
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @PerFragment
    @Provides
    fun provideMainFragmentPresenter(): MainFragmentContract.Presenter {
        return MainFragmentPresenter()
    }

    @PerFragment
    @Provides
    fun provideDateTimeFragmentPresenter(myDb: DB): DateTimeFragmentContract.Presenter {
        return DateTimeFragmentPresenter(myDb)
    }
    @PerFragment
    @Provides
    fun providePrayerTimingFragmentPresenter(myDb: DB): PrayerTimingFragContract.Presenter {
        return PrayerTimingPresenter(myDb)
    }
    @PerFragment
    @Provides
    fun provideMonthlyTimingFragmentPresenter(myDb: DB): MonthlyTimingFragmentContract.Presenter {
        return MonthlyTimingPresenter(myDb)
    }

    @PerFragment
    @Provides
    fun provideApiService(): MyApiInterface = MyApiInterface.create()

    /*@PerFragment
    @Provides
    fun provideLoadingDialog(baseFragment: BaseFragment) = LoadingDialog(baseFragment.context!!)*/
}

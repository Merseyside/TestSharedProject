package com.merseyside.testsharedproject.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.merseyside.testsharedproject.domain.interactor.GetCoinPairInteractor
import com.merseyside.testsharedproject.presentation.view.activity.mainActivity.model.MainViewModel
import com.upstream.basemvvmimpl.presentation.activity.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: BaseActivity) {

    @Provides
    internal fun coinPairInteractorProvider(): GetCoinPairInteractor {
        return GetCoinPairInteractor()
    }

    @Provides
    internal fun mainViewModelProvider(getCoinPairUseCase: GetCoinPairInteractor): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(getCoinPairUseCase)
    }

    @Provides
    internal fun provideMainViewModel(factory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

    class MainViewModelProviderFactory(
        private val getCoinPairUseCase: GetCoinPairInteractor
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(getCoinPairUseCase) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}
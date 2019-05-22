package com.merseyside.testsharedproject.presentation.view.activity.mainActivity.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import com.merseyside.testsharedproject.TestSharedApplication
import com.merseyside.testsharedproject.domain.interactor.GetCoinPairInteractor
import com.merseyside.testsharedproject.presentation.base.BaseYobitViewModel
import kotlinx.coroutines.cancel

class MainViewModel(private val getCoinPairUseCase: GetCoinPairInteractor) : BaseYobitViewModel() {

    private val TAG = javaClass.simpleName

    val firstCoinFieldObservable = ObservableField<String>("btc")
    val secondCoinFieldObservable = ObservableField<String>("usd")

    override fun dispose() {
        getCoinPairUseCase.cancel()
    }

    override fun updateLanguage(context: Context) {

    }

    fun onGetButtonClick() {
        getCoinPair(firstCoinFieldObservable.get(), secondCoinFieldObservable.get())
    }

    private fun getCoinPair(first: String?, second: String?) {

        if (first.isNullOrEmpty() || second.isNullOrEmpty()) {
            showErrorMsg("Fields cannot be empty!")
            return
        }

        getCoinPairUseCase.execute(
            onComplete = { coinPair ->
                Toast.makeText(TestSharedApplication.getInstance(), "$coinPair", Toast.LENGTH_LONG).show()
            }, onError = { throwable ->
                showErrorMsg("Something went wrong!")
                throwable.printStackTrace()
            },
            params = GetCoinPairInteractor.Params(first, second))

    }

}
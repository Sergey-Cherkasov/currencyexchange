package pt.svcdev.currency.exchange.rate.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import pt.svcdev.currency.exchange.rate.model.AppState
import pt.svcdev.currency.exchange.rate.model.CurrencyExchange
import pt.svcdev.currency.exchange.rate.repository.Repository

class MainViewModel(private val repository: Repository<CurrencyExchange>) : ViewModel() {

    private val mutableLiveData: MutableLiveData<AppState> = MutableLiveData()

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    )

    fun subscribe(): LiveData<AppState> = mutableLiveData

    private fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    fun getData() {
        mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                mutableLiveData.postValue(AppState.Success(repository.getData()))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

}
package ey.samarin.financestocks.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ey.samarin.domain.stocks.GetStockProfileUseCase
import ey.samarin.models.DetailUIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getStockProfile: GetStockProfileUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUIState())
    val uiState = _uiState.asStateFlow()

    fun setArguments(stockSymbol: String?) = viewModelScope.launch {
        stockSymbol?.let {
            _uiState.emit(DetailUIState(title = it))
            obtainStocksProfile(stockSymbol = it)
        }

    }

    private fun obtainStocksProfile(stockSymbol: String) = launchAsync(
        exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _uiState.tryEmit(
                DetailUIState(errorText = "Remote data sheet was changed, try to pick other stock")
            )
        },
    ) {
        val response = getStockProfile(symbol = stockSymbol)
        _uiState.emit(response)
    }

    private fun launchAsync(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        exceptionHandler: CoroutineExceptionHandler? = null,
        block: suspend CoroutineScope.() -> Unit,
    ) {
        val context = (viewModelScope.coroutineContext + dispatcher).let { context ->
            exceptionHandler?.let {
                it + context
            } ?: context
        }

        viewModelScope.launch(context) {
            block()
        }
    }
}
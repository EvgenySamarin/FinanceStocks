package ey.samarin.financestocks.features.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ey.samarin.domain.stocks.GetStocksUseCase
import ey.samarin.models.StockPreview
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState

    fun onScreenLaunch() {
        obtainStocksPreview()
    }

    private fun obtainStocksPreview() {
        launchAsync(exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("POINT", "onScreenLaunch: ", throwable)
        }) {
            val stocksPreview = getStocksUseCase()

            _uiState.emit(
                MainUIState(
                    stocksPreview = stocksPreview,
                )
            )
        }
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

    fun onSearchTextChange(newSearchString: String) = viewModelScope.launch {
        _uiState.emit(uiState.value.copy(searchText = newSearchString))
    }

    fun onRepeatClick() {
        obtainStocksPreview()
    }

}

data class MainUIState(
    val searchText: String = "",
    val stocksPreview: List<StockPreview> = emptyList(),
)
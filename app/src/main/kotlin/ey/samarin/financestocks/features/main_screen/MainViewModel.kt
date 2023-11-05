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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
) : ViewModel() {

    companion object {
        const val DEFAULT_REPEAT_REQUEST_DELAY = 8000L
    }

    private val remotePreviewState = MutableStateFlow<List<StockPreview>>(emptyList())
    private val currentSearchState = MutableStateFlow("")
    private val isCanRepeatRequest = MutableStateFlow(true)
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState

    fun onScreenLaunch() {
        isCanRepeatRequest.value = true
        obtainStocksPreview()
    }

    private fun obtainStocksPreview() = launchAsyncWithRepetition(
        isCanRepeat = { isCanRepeatRequest.value },
        repeatDelay = DEFAULT_REPEAT_REQUEST_DELAY,
        exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            //ere we can handle errors, log for example
            Log.e("MainViewModel", "obtainStocksPreview: ", throwable)
            //no need to repeat broken requests
            isCanRepeatRequest.value = false
        },
    ) {
        remotePreviewState.emit(getStocksUseCase())
        updateUiState()
    }

    private fun updateUiState() = viewModelScope.launch {
        val searchText = currentSearchState.value

        _uiState.emit(
            MainUIState(
                searchText = searchText,
                stocksPreview = remotePreviewState.value.filter { preview ->
                    setOf(preview.symbol, preview.longName).any { field ->
                        field.contains(searchText, ignoreCase = true)
                    }
                }
            )
        )
    }

    private fun launchAsyncWithRepetition(
        isCanRepeat: () -> Boolean,
        repeatDelay: Long,
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
            while (isCanRepeat()) {
                val timeBeforeRequest = System.currentTimeMillis()
                block()
                val timeOfRequest = System.currentTimeMillis() - timeBeforeRequest
                delay(repeatDelay - timeOfRequest)
            }
        }
    }

    fun onSearchTextChange(newSearchString: String) = viewModelScope.launch {
        currentSearchState.emit(newSearchString)
        updateUiState()
    }

    fun onStockPreviewTap() = viewModelScope.launch {
        isCanRepeatRequest.emit(false)
    }

}

data class MainUIState(
    val searchText: String = "",
    val stocksPreview: List<StockPreview> = emptyList(),
)
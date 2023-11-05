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
    private val remotePreviewState = MutableStateFlow<List<StockPreview>>(emptyList())
    private val currentSearchState = MutableStateFlow("")
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState

    fun onScreenLaunch() {
        obtainStocksPreview()
    }

    private fun obtainStocksPreview() {
        launchAsync(exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            // TODO [202311309]: here we can handle errors
            Log.e("POINT", "onScreenLaunch: ", throwable)
        }) {
            remotePreviewState.emit(getStocksUseCase())
            updateUiState()
        }
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
        currentSearchState.emit(newSearchString)
        updateUiState()
    }

}

data class MainUIState(
    val searchText: String = "",
    val stocksPreview: List<StockPreview> = emptyList(),
)
package ey.samarin.financestocks.features.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ey.samarin.domain.stocks.GetStocksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUIState.EMPTY)
    val uiState: StateFlow<MainUIState> = _uiState

    fun onScreenLaunch() {
        viewModelScope.launch {
            val stocksData = getStocksUseCase()

            _uiState.emit(MainUIState(title = stocksData))
        }
    }
}

data class MainUIState(
    val title: String
) {
    companion object {
        val EMPTY = MainUIState(title = "STUB")
    }
}
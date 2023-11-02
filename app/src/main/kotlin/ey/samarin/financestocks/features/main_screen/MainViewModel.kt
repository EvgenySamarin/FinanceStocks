package ey.samarin.financestocks.features.main_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MainUIState.EMPTY)
    val uiState: StateFlow<MainUIState> = _uiState


}

data class MainUIState(
    val title: String
) {
    companion object {
        val EMPTY = MainUIState(title = "STUB")
    }
}
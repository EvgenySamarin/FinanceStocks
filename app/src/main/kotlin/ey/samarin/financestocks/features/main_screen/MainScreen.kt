package ey.samarin.financestocks.features.main_screen

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_COMPACT
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_EXPANDED
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_MEDIUM

class MainUIStateProvider : PreviewParameterProvider<MainUIState> {
    override val values = sequenceOf(
        MainUIState(title = "Test")
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun MainScreenTabletPreview() {
    MainScreen(
        state = MainUIState(title = "Test"),
        windowSize = WINDOWS_SIZE_EXPANDED
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun MainScreenPhonePreview() {
    MainScreen(
        state = MainUIState(title = "Test"),
        windowSize = WINDOWS_SIZE_MEDIUM
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.WEAR_OS_RECT)
@Composable
fun MainScreenWatchPreview() {
    MainScreen(
        state = MainUIState(title = "Test"),
        windowSize = WINDOWS_SIZE_COMPACT
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun MainScreen(
    @PreviewParameter(MainUIStateProvider::class) state: MainUIState,
    windowSize: WindowSizeClass = WINDOWS_SIZE_EXPANDED,
    onScreenLaunch: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        onScreenLaunch()
    }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Text(text = state.title)
        }

        WindowWidthSizeClass.Medium -> {
            Text(text = state.title)
        }

        WindowWidthSizeClass.Expanded -> {
            Text(text = state.title)
        }
    }
}
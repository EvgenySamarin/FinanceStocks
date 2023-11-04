package ey.samarin.financestocks.features.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ey.samarin.financestocks.R
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_COMPACT
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_EXPANDED
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_MEDIUM
import ey.samarin.models.StockPreview

class MainUIStateProvider : PreviewParameterProvider<MainUIState> {
    override val values = sequenceOf(
        MainUIState(
            stocksPreview = listOf(
                StockPreview(symbol = "AAPL"),
                StockPreview(symbol = "TSLA"),
                StockPreview(symbol = "GOOG"),
                StockPreview(symbol = "AMZN"),
                StockPreview(symbol = "MSFT"),
                StockPreview(symbol = "FB"),
                StockPreview(symbol = "NVDA"),
                StockPreview(symbol = "PYPL"),
                StockPreview(symbol = "INTC"),
            )
        )
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun MainScreenTabletPreview() {
    MainScreen(
        state = MainUIState(),
        windowSize = WINDOWS_SIZE_EXPANDED
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun MainScreenPhonePreview() {
    MainScreen(
        state = MainUIState(),
        windowSize = WINDOWS_SIZE_MEDIUM
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.WEAR_OS_RECT)
@Composable
fun MainScreenWatchPreview() {
    MainScreen(
        state = MainUIState(),
        windowSize = WINDOWS_SIZE_COMPACT
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun MainScreen(
    @PreviewParameter(MainUIStateProvider::class) state: MainUIState,
    windowSize: WindowSizeClass = WINDOWS_SIZE_MEDIUM,
    onScreenLaunch: () -> Unit = {},
    onSearchTextChange: (String) -> Unit = {},
    onRepeatClick: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        onScreenLaunch()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.main_screen_title)) },
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (windowSize.widthSizeClass) {
                    WindowWidthSizeClass.Compact,
                    WindowWidthSizeClass.Medium -> Column(modifier = Modifier.padding(16.dp)) {
                        TextField(
                            value = state.searchText,
                            onValueChange = { onSearchTextChange(it) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                            ),
                        )
                        Button(
                            onClick = { onRepeatClick() },
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(text = "Repeat")
                        }
                        StocksListContent(state.stocksPreview)
                    }

                    WindowWidthSizeClass.Expanded -> Row {
                        Column {
                            TextField(
                                value = state.searchText,
                                onValueChange = { },
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                ),
                            )
                        }
                        StocksListContent(state.stocksPreview)
                    }
                }
            }
        }
    )
}

@Composable
private fun StocksListContent(stocksPreview: List<StockPreview>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            count = stocksPreview.size,
            key = { index ->
                stocksPreview[index].symbol.hashCode()
            }
        ) { index ->
            stocksPreview[index].let { item ->
                Text(item.symbol)
            }
        }
    }
}


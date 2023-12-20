package ey.samarin.financestocks.screens.main_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ey.samarin.financestocks.R
import ey.samarin.financestocks.ui.theme.getAdaptiveBodyByHeight
import ey.samarin.financestocks.ui.theme.getAdaptiveLabelByHeight
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_COMPACT
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_EXPANDED
import ey.samarin.financestocks.utils.PreviewUtils.WINDOWS_SIZE_MEDIUM
import ey.samarin.models.STUB_StockPreview
import ey.samarin.models.StockPreview

class MainUIStateProvider : PreviewParameterProvider<MainUIState> {
    override val values = sequenceOf(
        MainUIState(stocksPreview = STUB_StockPreview)
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun MainScreenTabletPreview() {
    MainScreen(
        state = MainUIState(stocksPreview = STUB_StockPreview),
        windowSize = WINDOWS_SIZE_EXPANDED
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun MainScreenPhonePreview() {
    MainScreen(
        state = MainUIState(stocksPreview = STUB_StockPreview),
        windowSize = WINDOWS_SIZE_MEDIUM
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.WEAR_OS_RECT)
@Composable
fun MainScreenWatchPreview() {
    MainScreen(
        state = MainUIState(stocksPreview = STUB_StockPreview),
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
    onStockPreviewTap: (StockPreview) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        onScreenLaunch()
    }

    Scaffold(
        topBar = {
            if (windowSize.heightSizeClass != WindowHeightSizeClass.Compact) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.main_screen_title)) },
                )
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                when (windowSize.widthSizeClass) {
                    WindowWidthSizeClass.Compact,
                    WindowWidthSizeClass.Medium -> Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight()
                    ) {
                        SearchString(
                            text = state.searchText,
                            onTextChange = onSearchTextChange,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        StocksPreviewListContent(
                            stocksPreview = state.stocksPreview,
                            windowSize = windowSize,
                            modifier = Modifier.fillMaxWidth(),
                            onItemTap = onStockPreviewTap,
                        )
                    }

                    WindowWidthSizeClass.Expanded -> Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            SearchString(
                                text = state.searchText,
                                onTextChange = onSearchTextChange,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                        StocksPreviewListContent(
                            stocksPreview = state.stocksPreview,
                            windowSize = windowSize,
                            modifier = Modifier.weight(1f),
                            onItemTap = onStockPreviewTap,
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun SearchString(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    )
}

@Composable
private fun StocksPreviewListContent(
    stocksPreview: List<StockPreview>,
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    onItemTap: (StockPreview) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.animateContentSize(),
        contentPadding = PaddingValues(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = stocksPreview.size,
            key = { index ->
                stocksPreview[index].symbol.hashCode()
            }
        ) { index ->
            ItemContent(
                ui = stocksPreview[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                windowSize = windowSize,
                onTap = onItemTap,
            )
        }
    }
}

@Composable
private fun ItemContent(
    ui: StockPreview,
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    onTap: (StockPreview) -> Unit = {},
) {
    Column(
        modifier = modifier
            .heightIn(64.dp)
            .clickable {
                onTap(ui)
            },
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = ui.symbol,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = getAdaptiveLabelByHeight(windowSize.heightSizeClass),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = ui.longName,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = getAdaptiveBodyByHeight(windowSize.heightSizeClass),
        )
    }
}

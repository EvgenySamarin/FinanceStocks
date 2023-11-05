package ey.samarin.financestocks

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ey.samarin.financestocks.Routes.Detail.stockSymbol
import ey.samarin.financestocks.databinding.FragmentDetailsContainerBinding
import ey.samarin.financestocks.features.detail_screen.DetailsFragment
import ey.samarin.financestocks.features.main_screen.MainScreen
import ey.samarin.financestocks.features.main_screen.MainViewModel

/**
 * Here you can define different flags based on windowsSize or just push it further to concrete screen
 *
 * WindowsSize class is no single way to support adaptivity, you also can use the DisplayFeatures
 * from accompanist library
 */
@Composable
fun FinanceStocksApp(windowSize: WindowSizeClass) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(
                state = uiState,
                windowSize = windowSize,
                onScreenLaunch = mainViewModel::onScreenLaunch,
                onSearchTextChange = mainViewModel::onSearchTextChange,
                onStockPreviewTap = {
                    mainViewModel.onStockPreviewTap()
                    navController.navigate(Routes.Detail.route.replace("{$stockSymbol}", it.symbol))
                },
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument(stockSymbol) { type = NavType.StringType })
        ) {
            FragmentInComposeExample()
        }
    }
}

@Composable
fun FragmentInComposeExample() {
    AndroidViewBinding(factory = FragmentDetailsContainerBinding::inflate) {
        val fragment = fragmentContainerView.getFragment<DetailsFragment>()
    }
}

sealed class Routes(val route: String) {
    data object Main : Routes(route = "Main")
    data object Detail : Routes(route = "Detail/{stockSymbol}") {
        val stockSymbol: String = "stockSymbol"
    }
}

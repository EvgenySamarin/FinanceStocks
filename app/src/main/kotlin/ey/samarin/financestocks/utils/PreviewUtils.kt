package ey.samarin.financestocks.utils

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
object PreviewUtils {
    /**
     *  - width < 600.dp
     *  - height < 480.dp
     */
    val WINDOWS_SIZE_COMPACT = WindowSizeClass.calculateFromSize(
        DpSize(width = 300.dp, height = 400.dp)
    )

    /**
     * - width < 840.dp
     * - height < 900.dp
     */
    val WINDOWS_SIZE_MEDIUM = WindowSizeClass.calculateFromSize(
        DpSize(width = 700.dp, height = 800.dp)
    )

    /**
     * - width >= 840.dp
     * - height >= 900.dp
     */
    val WINDOWS_SIZE_EXPANDED = WindowSizeClass.calculateFromSize(
        DpSize(width = 900.dp, height = 1000.dp)
    )
}
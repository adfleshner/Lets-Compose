package io.flesh.letscompose.utils.previewutils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

sealed class WindowWidthSizeClassPreview(val value: WindowWidthSizeClass) {
    data object Compact : WindowWidthSizeClassPreview(WindowWidthSizeClass.Compact)
    data object Medium : WindowWidthSizeClassPreview(WindowWidthSizeClass.Medium)
    data object Expanded : WindowWidthSizeClassPreview(WindowWidthSizeClass.Expanded)
}

class WindowWidthSizePreviewParameterProvider :
    PreviewParameterProvider<WindowWidthSizeClassPreview> {
    override val values: Sequence<WindowWidthSizeClassPreview> =
        sequenceOf(
            WindowWidthSizeClassPreview.Expanded,
            WindowWidthSizeClassPreview.Compact,
            WindowWidthSizeClassPreview.Medium,
        )
}
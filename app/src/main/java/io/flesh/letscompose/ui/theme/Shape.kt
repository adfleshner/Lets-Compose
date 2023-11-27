package io.flesh.letscompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    // Shapes None and Full are omitted as None is a RectangleShape and Full is a CircleShape.
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(12.dp),
)
package com.example.lemonadeapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Thiết lập hệ màu sáng
val lightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary
    // Thêm các màu khác nếu cần
)

@Composable
fun LemonadeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColors,
        typography = Typography, // Đảm bảo Typography và Shapes được khai báo
        shapes = Shapes,
        content = content
    )
}

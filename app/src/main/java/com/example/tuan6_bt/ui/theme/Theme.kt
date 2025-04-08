package com.example.tuan6_bt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    primaryContainer = Color(0xFF4F378B),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF7D5260),
    error = Color(0xFFB00020) // Màu lỗi
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    primaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF7D5260),
    error = Color(0xFFB00020) // Màu lỗi
)

// Định nghĩa Typography tùy chỉnh cho material3
private val AppTypography = Typography(
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black
    )
    // Bạn có thể thêm các kiểu khác như bodyMedium, bodyLarge, headline, etc.
)

@Composable
fun TaskAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, // Sử dụng Typography tùy chỉnh
        shapes = MaterialTheme.shapes,
        content = content
    )
}
package com.example.equipodefutbolapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Verde = Color(0xFF00FF87)
val VerdeOscuro = Color(0xFF00C96B)
val Fondo = Color(0xFF0D0D0D)
val Superficie = Color(0xFF1A1A1A)
val SuperficieAlta = Color(0xFF252525)
val TextoPrimario = Color(0xFFFFFFFF)
val TextoSecundario = Color(0xFF9E9E9E)

private val DarkColorScheme = darkColorScheme(
    primary = Verde,
    onPrimary = Color.Black,
    background = Fondo,
    onBackground = TextoPrimario,
    surface = Superficie,
    onSurface = TextoPrimario,
    surfaceVariant = SuperficieAlta,
    onSurfaceVariant = TextoSecundario,
    secondary = VerdeOscuro,
    onSecondary = Color.Black
)

@Composable
fun EquipoDeFutbolAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
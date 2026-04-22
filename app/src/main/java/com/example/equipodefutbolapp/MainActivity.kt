package com.example.equipodefutbolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.equipodefutbolapp.Screen.*
import com.example.equipodefutbolapp.ui.theme.EquipoDeFutbolAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EquipoDeFutbolAppTheme {
                AppNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        Triple("equipos",     "Equipos",      Icons.Default.Groups),
        Triple("jugadores",   "Jugadores",    Icons.Default.Person),
        Triple("entrenadores","Entrenadores", Icons.Default.SupervisorAccount),
        Triple("partidos",    "Partidos",     Icons.Default.SportsSoccer),
        Triple("estadisticas","Estadísticas", Icons.Default.BarChart)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚽ Equipo de Fútbol") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { (route, label, icon) ->
                    NavigationBarItem(
                        selected = currentRoute == route,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "equipos",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("equipos")      { EquipoScreen() }
            composable("jugadores")    { JugadorScreen() }
            composable("entrenadores") { EntrenadorScreen() }
            composable("partidos")     { PartidoScreen() }
            composable("estadisticas") { EstadisticaScreen() }
        }
    }
}
package com.example.equipodefutbolapp.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.equipodefutbolapp.ViewModel.JugadorViewModel
import com.example.equipodefutbolapp.ui.theme.AppColors

@Composable
fun JugadorScreen(vm: JugadorViewModel = viewModel()) {

    val jugadores by vm.jugadores.collectAsState()
    val mensaje by vm.mensaje.collectAsState()

    var buscarIdEquipo by remember { mutableStateOf("") }
    var buscarGoles by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { vm.getJugadores() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.fondo)
            .padding(16.dp)
    ) {
        Text("🏃 Jugadores", color = AppColors.textoPrimario,
            fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Filtros
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = AppColors.tarjeta),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Filtros de búsqueda", color = AppColors.verde,
                    fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))

                // Buscar por equipo
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = buscarIdEquipo,
                        onValueChange = { buscarIdEquipo = it },
                        label = { Text("ID Equipo", color = AppColors.textoSecundario) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AppColors.verde,
                            unfocusedBorderColor = AppColors.borde,
                            focusedTextColor = AppColors.textoPrimario,
                            unfocusedTextColor = AppColors.textoPrimario,
                            cursorColor = AppColors.verde
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Button(
                        onClick = {
                            val id = buscarIdEquipo.toIntOrNull()
                            if (id != null) vm.jugadoresPorEquipo(id)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColors.verde),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text("Equipo", color = AppColors.fondo, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(8.dp))

                // Buscar por goles
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = buscarGoles,
                        onValueChange = { buscarGoles = it },
                        label = { Text("Mín. Goles", color = AppColors.textoSecundario) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AppColors.verde,
                            unfocusedBorderColor = AppColors.borde,
                            focusedTextColor = AppColors.textoPrimario,
                            unfocusedTextColor = AppColors.textoPrimario,
                            cursorColor = AppColors.verde
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Button(
                        onClick = {
                            val g = buscarGoles.toIntOrNull()
                            if (g != null) vm.jugadoresConMasDeXGoles(g)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColors.verde),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text("Goles", color = AppColors.fondo, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { vm.getJugadores() },
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.tarjetaAlta),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Ver Todos", color = AppColors.textoPrimario, fontWeight = FontWeight.Bold)
                }
            }
        }

        mensaje?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = if (it.startsWith("Error")) AppColors.rojo else AppColors.verde,
                fontSize = 13.sp)
        }

        Spacer(Modifier.height(16.dp))
        Text("${jugadores.size} jugadores", color = AppColors.textoSecundario, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(jugadores) { jugador ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = AppColors.tarjeta),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(AppColors.tarjetaAlta),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${jugador.dorsal}", color = AppColors.verde,
                                fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(jugador.nombre, color = AppColors.textoPrimario,
                                fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("${jugador.posicion} · ${jugador.nacionalidad}",
                                color = AppColors.textoSecundario, fontSize = 13.sp)
                            // null-safe
                            Text(jugador.equipo?.nombre ?: "Sin equipo",
                                color = AppColors.verde, fontSize = 12.sp)
                        }
                        IconButton(onClick = { vm.eliminarJugador(jugador.idJugador) }) {
                            Text("🗑️", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}
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
import com.example.equipodefutbolapp.ViewModel.EstadisticaViewModel
import com.example.equipodefutbolapp.ui.theme.AppColors

@Composable
fun EstadisticaScreen(vm: EstadisticaViewModel = viewModel()) {

    val estadisticas by vm.estadisticas.collectAsState()
    val mensaje by vm.mensaje.collectAsState()

    var buscarIdEquipo by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { vm.getEstadisticas() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.fondo)
            .padding(16.dp)
    ) {
        Text("📊 Estadísticas", color = AppColors.textoPrimario,
            fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Consulta total goles por equipo
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = AppColors.tarjeta),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Goles por Equipo", color = AppColors.verde,
                    fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.height(8.dp))
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
                            if (id != null) vm.totalGolesEquipo(id)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColors.verde),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text("Buscar", color = AppColors.fondo, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        mensaje?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = if (it.startsWith("Error")) AppColors.rojo else AppColors.verde,
                fontSize = 13.sp)
        }

        Spacer(Modifier.height(16.dp))
        Text("${estadisticas.size} estadísticas", color = AppColors.textoSecundario, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(estadisticas) { est ->
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
                            Text("📊", fontSize = 22.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            // null-safe en jugador
                            Text(
                                est.jugador?.nombre ?: "Jugador desconocido",
                                color = AppColors.textoPrimario,
                                fontWeight = FontWeight.Bold, fontSize = 16.sp
                            )
                            // null-safe en partido y equipos
                            val local = est.partido?.equipoLocal?.nombre ?: "?"
                            val visita = est.partido?.equipoVisita?.nombre ?: "?"
                            Text("$local vs $visita",
                                color = AppColors.textoSecundario, fontSize = 13.sp)
                            Spacer(Modifier.height(4.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text("⚽ ${est.goles}", color = AppColors.verde, fontSize = 13.sp)
                                Text("🅰️ ${est.asistencias}", color = AppColors.textoSecundario, fontSize = 13.sp)
                                Text("⏱ ${est.minutosJugados}'", color = AppColors.textoSecundario, fontSize = 13.sp)
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text("🟨 ${est.tarjetasAmarillas}", fontSize = 13.sp, color = AppColors.textoSecundario)
                                Text("🟥 ${est.tarjetasRojas}", fontSize = 13.sp, color = AppColors.textoSecundario)
                            }
                        }
                        IconButton(onClick = { vm.eliminarEstadistica(est.idEstadistica) }) {
                            Text("🗑️", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}
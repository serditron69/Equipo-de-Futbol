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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.equipodefutbolapp.ViewModel.PartidoViewModel
import com.example.equipodefutbolapp.ui.theme.AppColors

@Composable
fun PartidoScreen(vm: PartidoViewModel = viewModel()) {

    val partidos by vm.partidos.collectAsState()
    val mensaje by vm.mensaje.collectAsState()

    LaunchedEffect(Unit) { vm.getPartidos() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.fondo)
            .padding(16.dp)
    ) {
        Text("🏟️ Partidos", color = AppColors.textoPrimario,
            fontSize = 26.sp, fontWeight = FontWeight.Bold)

        mensaje?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = if (it.startsWith("Error")) AppColors.rojo else AppColors.verde,
                fontSize = 13.sp)
        }

        Spacer(Modifier.height(16.dp))
        Text("${partidos.size} partidos", color = AppColors.textoSecundario, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(partidos) { partido ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = AppColors.tarjeta),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Marcador estilo OneFootball
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Equipo local
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(AppColors.tarjetaAlta),
                                    contentAlignment = Alignment.Center
                                ) { Text("⚽", fontSize = 20.sp) }
                                Spacer(Modifier.height(4.dp))
                                // null-safe
                                Text(
                                    partido.equipoLocal?.nombre ?: "Local",
                                    color = AppColors.textoPrimario,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                            }

                            // Marcador
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                Text(
                                    "${partido.golesLocal} - ${partido.golesVisita}",
                                    color = AppColors.verde,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }

                            // Equipo visita
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(AppColors.tarjetaAlta),
                                    contentAlignment = Alignment.Center
                                ) { Text("⚽", fontSize = 20.sp) }
                                Spacer(Modifier.height(4.dp))
                                // null-safe
                                Text(
                                    partido.equipoVisita?.nombre ?: "Visita",
                                    color = AppColors.textoPrimario,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        HorizontalDivider(color = AppColors.borde)
                        Spacer(Modifier.height(8.dp))

                        // Info partido
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("📅 ${partido.fecha}",
                                    color = AppColors.textoSecundario, fontSize = 12.sp)
                                Text("🏟️ ${partido.estadio}",
                                    color = AppColors.textoSecundario, fontSize = 12.sp)
                            }
                            IconButton(onClick = { vm.eliminarPartido(partido.idPartido) }) {
                                Text("🗑️", fontSize = 18.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
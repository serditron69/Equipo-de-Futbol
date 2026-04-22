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
import com.example.equipodefutbolapp.ViewModel.EquipoViewModel
import com.example.equipodefutbolapp.ui.theme.AppColors

@Composable
fun EquipoScreen(vm: EquipoViewModel = viewModel()) {

    val equipos by vm.equipos.collectAsState()
    val mensaje by vm.mensaje.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var fundacion by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { vm.getEquipos() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.fondo)
            .padding(16.dp)
    ) {
        Text(
            "⚽ Equipos",
            color = AppColors.textoPrimario,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = AppColors.tarjeta),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nuevo Equipo", color = AppColors.verde,
                    fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))

                listOf(
                    Triple(nombre, { v: String -> nombre = v }, "Nombre del equipo"),
                    Triple(ciudad, { v: String -> ciudad = v }, "Ciudad"),
                    Triple(fundacion, { v: String -> fundacion = v }, "Fundación (yyyy-MM-dd)")
                ).forEach { (value, onChange, label) ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = onChange,
                        label = { Text(label, color = AppColors.textoSecundario) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AppColors.verde,
                            unfocusedBorderColor = AppColors.borde,
                            focusedTextColor = AppColors.textoPrimario,
                            unfocusedTextColor = AppColors.textoPrimario,
                            cursorColor = AppColors.verde
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        vm.crearEquipo(nombre, ciudad, fundacion)
                        nombre = ""; ciudad = ""; fundacion = ""
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.verde),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Crear Equipo", color = AppColors.fondo, fontWeight = FontWeight.Bold)
                }
            }
        }

        mensaje?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = if (it.startsWith("Error")) AppColors.rojo else AppColors.verde,
                fontSize = 13.sp)
        }

        Spacer(Modifier.height(16.dp))
        Text("${equipos.size} equipos", color = AppColors.textoSecundario, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(equipos) { equipo ->
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
                            Text("⚽", fontSize = 22.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(equipo.nombre, color = AppColors.textoPrimario,
                                fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("${equipo.ciudad} · ${equipo.fundacion}",
                                color = AppColors.textoSecundario, fontSize = 13.sp)
                        }
                        IconButton(onClick = { vm.eliminarEquipo(equipo.idEquipo) }) {
                            Text("🗑️", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}
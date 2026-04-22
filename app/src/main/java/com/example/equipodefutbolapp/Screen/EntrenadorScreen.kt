package com.example.equipodefutbolapp.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.equipodefutbolapp.ViewModel.EntrenadorViewModel

@Composable
fun EntrenadorScreen(vm: EntrenadorViewModel = viewModel()) {

    val entrenadores by vm.entrenadores.collectAsState()
    val mensaje by vm.mensaje.collectAsState()

    LaunchedEffect(Unit) { vm.getEntrenadores() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Entrenadores", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        mensaje?.let {
            Text(it, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        LazyColumn {
            items(entrenadores) { entrenador ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(entrenador.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Especialidad: ${entrenador.especialidad}")
                        Text("Equipo: ${entrenador.equipo.nombre}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Button(onClick = { vm.eliminarEntrenador(entrenador.idEntrenador) }) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
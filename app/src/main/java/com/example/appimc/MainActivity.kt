package com.example.appimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5)
                ) {
                    CalculadoraImcScreen()
                }
            }
        }
    }
}

@Composable
fun CalculadoraImcScreen() {
    var alturaInput by remember { mutableStateOf("") }
    var pesoInput by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<ImcResultado?>(null) }
    var mensagemErro by remember { mutableStateOf("") }

    val azulCabecalho = Color(0xFF4FC3F7)
    val azulBotao = Color(0xFF29B6F6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(azulCabecalho)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Calculadora IMC",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Seus dados",
                    color = azulCabecalho,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = alturaInput,
                    onValueChange = { alturaInput = it },
                    label = { Text("Altura (cm)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = pesoInput,
                    onValueChange = { pesoInput = it },
                    label = { Text("Peso (kg)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val peso = pesoInput.replace(",", ".").toDoubleOrNull()
                        val altura = alturaInput.replace(",", ".").toDoubleOrNull()

                        val calculado =
                            if (peso != null && altura != null) calcularImc(peso, altura) else null

                        if (calculado != null) {
                            resultado = calculado
                            mensagemErro = ""
                        } else {
                            mensagemErro = "Por favor, informe valores válidos."
                            resultado = null
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = azulBotao),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "CALCULAR",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                TextButton(
                    onClick = {
                        alturaInput = ""
                        pesoInput = ""
                        resultado = null
                        mensagemErro = ""
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Limpar dados",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (mensagemErro.isNotEmpty()) {
            Text(
                text = mensagemErro,
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        resultado?.let { res ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(64.dp),
                colors = CardDefaults.cardColors(containerColor = res.cor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Locale.US garante ponto decimal independente do idioma do aparelho
                    val imcFormatado = String.format(Locale.US, "%.1f", res.imc)

                    Text(
                        text = imcFormatado,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = res.classificacao,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
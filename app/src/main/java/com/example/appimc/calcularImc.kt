package com.example.appimc

import androidx.compose.ui.graphics.Color
import kotlin.math.round

data class ImcResultado(
    val imc: Double,
    val classificacao: String,
    val cor: Color
)

fun calcularImc(pesoKg: Double, alturaCm: Double): ImcResultado? {
    if (pesoKg <= 0 || alturaCm <= 0) return null

    val alturaMetros = alturaCm / 100
    val imcBruto = pesoKg / (alturaMetros * alturaMetros)

    // Arredonda para 1 casa, igual ao que é exibido na tela
    val imc = round(imcBruto * 10) / 10

    val (classificacao, cor) = when {
        imc < 18.5 -> "Abaixo do peso" to Color(0xFF1E88E5)
        imc < 25.0 -> "Peso normal" to Color(0xFF2E7D32)
        imc < 30.0 -> "Sobrepeso" to Color(0xFFFB8C00)
        imc < 35.0 -> "Obesidade grau I" to Color(0xFFF4511E)
        imc < 40.0 -> "Obesidade grau II" to Color(0xFFE53935)
        else -> "Obesidade grau III" to Color(0xFFB71C1C)
    }

    return ImcResultado(imc, classificacao, cor)
}
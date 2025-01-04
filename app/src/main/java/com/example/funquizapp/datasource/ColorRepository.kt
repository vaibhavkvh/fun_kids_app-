package com.example.funquizapp.datasource

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ColorRepository(private val context: Context) {

    fun getColors(): List<com.example.funquizapp.data.Color> {
        return try {
            val jsonString = context.assets.open("colors.json").bufferedReader().use { it.readText() }
            val typeToken = object : TypeToken<List<com.example.funquizapp.data.Color>>() {}.type
            Gson().fromJson(jsonString, typeToken)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getRandomColor(): Color {
        val colors = getColors()


        val randomIndex = (0 until colors.size).random()
        val randomColorData = colors[randomIndex]
        return parseColor(randomColorData.hex)
    }

    private fun parseColor(hex: String): Color {
        val color = if (hex.startsWith("#")) {
            hex.substring(1)
        } else {
            hex
        }
        return try {
            Color(android.graphics.Color.parseColor("#$color"))
        } catch (e: Exception) {
            Color.Gray
        }
    }
}
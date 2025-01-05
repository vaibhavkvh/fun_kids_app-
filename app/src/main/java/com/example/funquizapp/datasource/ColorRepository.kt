package com.example.funquizapp.datasource

import android.content.Context
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class ColorRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private var colorList: MutableList<com.example.funquizapp.data.Color> = mutableListOf()
    private var currentIndex = 0
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

    fun getRandomColor(): com.example.funquizapp.data.Color {
        if (colorList.isEmpty()) {
            colorList.addAll(getColors())
            colorList.shuffle()
        }

        val color = colorList[currentIndex]
        currentIndex = (currentIndex + 1) % colorList.size
        return color
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
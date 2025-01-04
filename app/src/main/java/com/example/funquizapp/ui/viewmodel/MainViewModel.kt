package com.example.funquizapp.ui.viewmodel

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.funquizapp.datasource.ColorRepository

class MainViewModel(context: Context) : ViewModel() {
    private val colorRepository = ColorRepository(context)

    fun getRandomColor(): Color {
        return colorRepository.getRandomColor()
    }
}
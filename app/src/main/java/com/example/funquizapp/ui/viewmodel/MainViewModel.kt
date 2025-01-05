package com.example.funquizapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.funquizapp.data.Color
import com.example.funquizapp.data.Shape
import com.example.funquizapp.datasource.ColorRepository
import com.example.funquizapp.datasource.NumberRepository
import com.example.funquizapp.datasource.ShapeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    private val colorRepository = ColorRepository(context)
    private val numberRepository = NumberRepository()
    private val shapeRepository = ShapeRepository(context)

    fun getRandomColor(): Color {
        return colorRepository.getRandomColor()
    }

    fun getNextNumber():Int{
        return numberRepository.getNextNumber()
    }

    fun getRandomShape() : Shape{
        return shapeRepository.getNextShape()

    }

}
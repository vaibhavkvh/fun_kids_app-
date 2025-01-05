package com.example.funquizapp.datasource

import android.content.Context
import com.example.funquizapp.data.Shape
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class ShapeRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private var shapeList: MutableList<Shape> = kotlin.collections.mutableListOf()
    private var currentIndex = 0

    init {
        resetShapeList()
    }

    private fun getShapes(): List<Shape> {
        return try {
            val jsonString =
                context.assets.open("shapes.json").bufferedReader().use { it.readText() }
            val typeToken = object : TypeToken<List<Shape>>() {}.type
            Gson().fromJson(jsonString, typeToken)
        } catch (e: IOException) {
            e.printStackTrace()
            kotlin.collections.emptyList()
        }
    }

    private fun resetShapeList() {
        shapeList.clear()
        shapeList.addAll(getShapes())
        shapeList.shuffle()
    }

    fun getNextShape(): Shape {
        if (shapeList.isEmpty()) {
            resetShapeList()
        }
        val shape = shapeList[currentIndex]
        currentIndex = (currentIndex + 1) % shapeList.size
        return shape
    }
}
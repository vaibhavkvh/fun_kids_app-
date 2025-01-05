package com.example.funquizapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.funquizapp.data.Color
import com.example.funquizapp.data.CommunityHelper
import com.example.funquizapp.data.Shape
import com.example.funquizapp.datasource.ColorRepository
import com.example.funquizapp.datasource.CommunityHelperRepository
import com.example.funquizapp.datasource.LetterRepository
import com.example.funquizapp.datasource.NumberRepository
import com.example.funquizapp.datasource.PersonalitiesHelperRepository
import com.example.funquizapp.datasource.ShapeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext var context: Context) : ViewModel() {
    private val colorRepository = ColorRepository(context)
    private val numberRepository = NumberRepository()
    private val letterRepository = LetterRepository()
    private val shapeRepository = ShapeRepository(context)
    private val communityHelperRepository = CommunityHelperRepository(context)
    private val personalityRepository = PersonalitiesHelperRepository(context)

    fun getRandomColor(): Color {
        return colorRepository.getRandomColor()
    }

    fun getNextNumber():Int{
        return numberRepository.getNextNumber()
    }

    fun getRandomShape() : Shape{
        return shapeRepository.getNextShape()

    }
    fun getNextLetter(): Char { // Add a function to get a letter
        return letterRepository.getNextLetter()
    }

    fun getRandomCommunityHelper(): CommunityHelper {
        return  communityHelperRepository.getNextCommunityHelper()
    }
    fun getPersonality(): CommunityHelper {
        return  personalityRepository.getNextPersonalities()
    }

    fun getImageResourceId(imageName: String): Int {
        return context.resources.getIdentifier(
            imageName.substringBeforeLast("."),
            "drawable",
            context.packageName
        )
    }
}
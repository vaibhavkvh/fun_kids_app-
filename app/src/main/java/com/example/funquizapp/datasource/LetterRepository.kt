package com.example.funquizapp.datasource

import javax.inject.Inject

class LetterRepository @Inject constructor() {

    private val uppercaseLetters = ('A'..'Z').toList()
    private val lowercaseLetters = ('a'..'z').toList()
    private var letterList: MutableList<Char> = mutableListOf()
    private var currentIndex = 0
    private var currentCase: LetterCase = LetterCase.UPPERCASE // Start with uppercase

    enum class LetterCase {
        UPPERCASE, LOWERCASE
    }

    init {
        resetLetterList()
    }

    private fun resetLetterList() {
        letterList.clear()
        letterList.addAll(uppercaseLetters)
        letterList.addAll(lowercaseLetters)
        letterList.shuffle()
        currentIndex = 0 // Reset the index when the list is reset
        currentCase = if (currentCase == LetterCase.UPPERCASE) LetterCase.LOWERCASE else LetterCase.UPPERCASE
    }
    fun getNextLetter(): Char {
        if (letterList.isEmpty()) {
            resetLetterList()
        }
        val letter = letterList[currentIndex]
        currentIndex = (currentIndex + 1) % letterList.size
        return letter
    }
}
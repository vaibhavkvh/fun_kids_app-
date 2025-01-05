package com.example.funquizapp.datasource

import javax.inject.Inject

class NumberRepository @Inject constructor() {

    private var numberList: MutableList<Int> = mutableListOf()
    private var currentIndex = 0
    private val range = 1..50 // The range of numbers you want (1 to 50)

    init {
        // Initialize the numberList with numbers from 1 to 50
        resetNumberList()
    }

    private fun resetNumberList() {
        numberList.clear()
        numberList.addAll(range.toList()) // Add numbers from the range
        numberList.shuffle() // Shuffle for a random order
    }

    fun getNextNumber(): Int {
        // If the list is empty, reset it
        if (numberList.isEmpty()) {
            resetNumberList()
        }

        // Get the number at the current index
        val number = numberList[currentIndex]

        // Increment the index, wrapping around if necessary
        currentIndex = (currentIndex + 1) % numberList.size

        return number
    }
}
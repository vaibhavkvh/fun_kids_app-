package com.example.funquizapp.datasource


import android.content.Context
import com.example.funquizapp.data.CommunityHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class PersonalitiesHelperRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val communityHelpers: List<CommunityHelper>
    private var helperList: MutableList<CommunityHelper> = mutableListOf()
    private var currentIndex = 0

    init {
        communityHelpers = loadPersonalities()
        resetist()
    }

    private fun loadPersonalities(): List<CommunityHelper> {
        val jsonString = try {
            context.assets.open("famous_personalities.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        val listCommunityHelperType = object : TypeToken<List<CommunityHelper>>() {}.type
        return Gson().fromJson(jsonString, listCommunityHelperType)
    }

    private fun resetist() {
        helperList.clear()
        helperList.addAll(communityHelpers)
        helperList.shuffle()
        currentIndex = 0 // Reset the index when the list is reset
    }

    fun getNextPersonalities(): CommunityHelper {
        if (helperList.isEmpty()) {
            resetist()
        }
        val communityHelper = helperList[currentIndex]
        currentIndex = (currentIndex + 1) % helperList.size
        return communityHelper
    }
    fun getImageResourceId(imageName: String): Int {
        return context.resources.getIdentifier(
            imageName.substringBeforeLast("."),
            "drawable",
            context.packageName
        )
    }
}
package com.example.funquizapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.funquizapp.data.Color
import com.example.funquizapp.ui.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController?, myArg: String?
) {
    val myViewModel: MainViewModel = hiltViewModel()
    var randomColor by remember { mutableStateOf(Color("", "")) }
    var number by remember { mutableStateOf(0) }

    if (myArg.equals("Color", true)) {

        randomColor = myViewModel.getRandomColor()
    }

    if (myArg.equals("Number", true)) {
        number = myViewModel.getNextNumber()
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(myArg!!) }, navigationIcon = {
            if (navController?.previousBackStackEntry != null) {
                IconButton(onClick = { navController?.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        })
    }) { innderPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (myArg.equals("Color", true)) {
                Box(
                    modifier = Modifier
                        .padding(innderPadding)
                        .border(width = 2.dp, color = androidx.compose.ui.graphics.Color.Black)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f) // 70% of the available height
                        .background(
                            color = androidx.compose.ui.graphics.Color("${randomColor?.hex}".toColorInt())
                        )
                ) {
                    randomColor?.name?.let {
                        Text(
                            it,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = androidx.compose.ui.graphics.Color.DarkGray,
                                    offset = Offset(2f, 2f),
                                    blurRadius = 1f
                                ), color = androidx.compose.ui.graphics.Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)

                        )
                    }
                }
            } else if (myArg.equals("Number", true)) {
                Box(
                    modifier = Modifier
                        .padding(innderPadding)
                        .border(width = 2.dp, color = androidx.compose.ui.graphics.Color.Black)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f) // 70% of the available height
                ) {
                    Text(
                        number.toString(),
                        style = TextStyle(
                            fontSize = 50.sp, shadow = Shadow(
                                color = androidx.compose.ui.graphics.Color.Gray,
                                offset = Offset(2f, 2f),
                                blurRadius = 1f
                            )
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)

                    )
                }
            }


            // Button
            OutlinedButton(
                onClick = {

                    if (myArg.equals("Color", true)) {
                        randomColor = myViewModel.getRandomColor()
                    } else if (myArg.equals("Number", true)) {
                        number = myViewModel.getNextNumber()
                    }

                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Next")
            }
        }
    }
}



package com.example.funquizapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.funquizapp.ui.theme.FunQuizAppTheme
import com.example.funquizapp.ui.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController?, myArg: String?,     myViewModel: MainViewModel
) {
    var randomColor by remember { mutableStateOf(Color.Unspecified) }

    if (myArg.equals("Color", true)) {
        randomColor = myViewModel.getRandomColor()
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
            // Box (70% height, full width)
            Box(
                modifier = Modifier
                    .padding(innderPadding)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f) // 70% of the available height
                    .background(randomColor)
            ) {
                Text("70% Height Box", modifier = Modifier.align(Alignment.Center))
            }

            // Button
            OutlinedButton(
                onClick = { randomColor = myViewModel.getRandomColor() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Next")
            }
        }
    }

}



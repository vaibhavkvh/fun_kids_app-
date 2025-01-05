package com.example.funquizapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.funquizapp.data.Color
import com.example.funquizapp.data.CommunityHelper
import com.example.funquizapp.data.Shape
import com.example.funquizapp.ui.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController?, myArg: String?
) {
    val myViewModel: MainViewModel = hiltViewModel()
    var randomColor by remember { mutableStateOf(Color("", "")) }
    var randomShape by remember { mutableStateOf(Shape("", "")) }
    var randomCommunityHelper by remember { mutableStateOf(CommunityHelper("", "", "")) }
    var randomPersonalities by remember { mutableStateOf(CommunityHelper("", "", "")) }
    var number by remember { mutableStateOf(0) }
    var letter by remember { mutableStateOf(' ') }

    if (myArg.equals("Color", true)) {
        randomColor = myViewModel.getRandomColor()
    } else if (myArg.equals("Number", true)) {
        number = myViewModel.getNextNumber()
    } else if (myArg.equals("Letter", true)) {
        letter = myViewModel.getNextLetter()
    } else if (myArg.equals("Community Helper", true)) {
        randomCommunityHelper = myViewModel.getRandomCommunityHelper()
    }  else if (myArg.equals("UnsungHero", true)) {
        randomPersonalities = myViewModel.getPersonality()
    } else {
        randomShape = myViewModel.getRandomShape()
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
                ColorWidget(innderPadding, randomColor)
            } else if (myArg.equals("Number", true)) {
                NumberComposable(innderPadding, number.toString())
            } else if (myArg.equals("Letter", true)) {
                LetterComposable(innderPadding, letter.toString())
            } else if (myArg.equals("Community Helper", true) ) {
                CommunityHelperComposable(myViewModel, randomCommunityHelper)
            } else if(myArg.equals("UnsungHero", true)){
                CommunityHelperComposable(myViewModel, randomPersonalities)
            }else {
                DrawShape(shape = randomShape, modifier = Modifier)
            }


            // Button
            OutlinedButton(
                onClick = {

                    if (myArg.equals("Color", true)) {
                        randomColor = myViewModel.getRandomColor()
                    } else if (myArg.equals("Number", true)) {
                        number = myViewModel.getNextNumber()
                    } else if (myArg.equals("Letter", true)) {
                        letter = myViewModel.getNextLetter()
                    } else if (myArg.equals("Community Helper", true)) {
                        randomCommunityHelper = myViewModel.getRandomCommunityHelper()
                    }  else if (myArg.equals("UnsungHero", true)) {
                        randomPersonalities = myViewModel.getPersonality()
                    } else {
                        randomShape = myViewModel.getRandomShape()
                    }

                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun CommunityHelperComposable(myViewModel: MainViewModel, helper: CommunityHelper) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .fillMaxHeight(0.8f)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Log.i("LOGVBV","helper.imageName ${helper.imageName}")
        Log.i("LOGVBV","helper.name ${helper.name}")
        val imageResourceId = myViewModel.getImageResourceId(helper.imageName)

        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.8f)

        )

        Text(
            helper.name,
            style = TextStyle(
                fontSize = 50.sp, shadow = Shadow(
                    color = androidx.compose.ui.graphics.Color.Gray,
                    offset = Offset(2f, 2f),
                    blurRadius = 1f
                )
            ),
            modifier = Modifier
        )
    }
}

@Composable
fun LetterComposable(innderPadding: PaddingValues, letter: String) {
    Box(
        modifier = Modifier
            .padding(innderPadding)
            .border(width = 2.dp, color = androidx.compose.ui.graphics.Color.Black)
            .fillMaxWidth()
            .fillMaxHeight(0.8f) // 70% of the available height
    ) {
        Text(
            letter,
            style = TextStyle(
                fontSize = 100.sp, shadow = Shadow(
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

@Composable
fun NumberComposable(innderPadding: PaddingValues, number: String) {
    Box(
        modifier = Modifier
            .padding(innderPadding)
            .border(width = 2.dp, color = androidx.compose.ui.graphics.Color.Black)
            .fillMaxWidth()
            .background(color = _root_ide_package_.androidx.compose.ui.graphics.Color.Green)
            .fillMaxHeight(0.8f) // 70% of the available height
    ) {
        Text(
            number,
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

@Composable
fun ColorWidget(innderPadding: PaddingValues, randomColor: Color) {
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
}


@Composable
fun DrawShape(shape: Shape, modifier: Modifier = Modifier) {
    val color: androidx.compose.ui.graphics.Color =
        androidx.compose.ui.graphics.Color.Cyan // Customize color if needed
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .fillMaxHeight(0.8f)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxSize(0.8f)

        ) {
            Canvas(
                modifier = modifier
                    .fillMaxSize(0.8f)
                    .padding(12.dp)
                    .padding(12.dp)
                    .align(alignment = Alignment.Center)
            ) {
                when (shape.name.lowercase()) {
                    "circle" -> drawCircle(color = color, radius = size.minDimension / 2)
                    "square" -> drawRect(
                        color = color,
                        size = Size(size.width, size.height / 2)
                    )

                    "rectangle" -> drawRect(color = color, size = size)

                    "triangle" -> drawTriangle(color = color, size = size)
                    "oval" -> drawOval(color = color, size = size)
                    "star" -> drawStar(color = color, size = size)
                    "heart" -> drawHeart(color = color, size = size)
                    "diamond" -> drawDiamond(color = color, size = size)
                    "pentagon" -> drawPentagon(color = color, size = size)
                    "hexagon" -> drawHexagon(color = color, size = size)

                    else -> drawRect(color = color, size = size) // Default to rectangle
                }
            }
        }

        Text(shape.name, style = TextStyle(fontSize = 20.sp))

    }

}

fun DrawScope.drawTriangle(color: androidx.compose.ui.graphics.Color, size: Size) {
    val path = Path().apply {
        moveTo(size.width / 2f, 0f) // Top point
        lineTo(size.width, size.height) // Bottom-right point
        lineTo(0f, size.height) // Bottom-left point
        close()
    }
    drawPath(path = path, color = color)
}

fun DrawScope.drawStar(color: androidx.compose.ui.graphics.Color, size: Size) {
    val numPoints = 5
    val outerRadius = size.minDimension / 2f
    val innerRadius = outerRadius / 2.5f
    val centerX = size.width / 2f
    val centerY = size.height / 2f

    val path = Path().apply {
        val angleIncrement = Math.PI.toFloat() * 2 / numPoints
        var currentAngle = -Math.PI.toFloat() / 2 // Start at top

        moveTo(
            centerX + outerRadius * kotlin.math.cos(currentAngle),
            centerY + outerRadius * kotlin.math.sin(currentAngle)
        )

        for (i in 1..numPoints) {
            currentAngle += angleIncrement / 2
            lineTo(
                centerX + innerRadius * kotlin.math.cos(currentAngle),
                centerY + innerRadius * kotlin.math.sin(currentAngle)
            )
            currentAngle += angleIncrement / 2
            lineTo(
                centerX + outerRadius * kotlin.math.cos(currentAngle),
                centerY + outerRadius * kotlin.math.sin(currentAngle)
            )
        }
        close()
    }
    drawPath(path = path, color = color)
}

fun DrawScope.drawHeart(color: androidx.compose.ui.graphics.Color, size: Size) {
    val path = Path().apply {
        val x = size.width / 2f
        val y = size.height / 4f

        moveTo(x, y)

        cubicTo(
            x - size.width / 2f, y - size.height / 4f,
            x - size.width / 2f, y + size.height / 4f,
            x, size.height
        )
        moveTo(x, y)
        cubicTo(
            x + size.width / 2f, y - size.height / 4f,
            x + size.width / 2f, y + size.height / 4f,
            x, size.height
        )
        close()
    }
    drawPath(path = path, color = color)
}


fun DrawScope.drawDiamond(color: androidx.compose.ui.graphics.Color, size: Size) {
    val path = Path().apply {
        moveTo(size.width / 2f, 0f) // Top point
        lineTo(size.width, size.height / 2f) // Right point
        lineTo(size.width / 2f, size.height) // Bottom point
        lineTo(0f, size.height / 2f) // Left point
        close()
    }
    drawPath(path = path, color = color)
}

fun DrawScope.drawPentagon(color: androidx.compose.ui.graphics.Color, size: Size) {
    val numPoints = 5
    val radius = size.minDimension / 2f
    val centerX = size.width / 2f
    val centerY = size.height / 2f
    val angleIncrement = 2 * Math.PI.toFloat() / numPoints
    var currentAngle = -Math.PI.toFloat() / 2 // Start at top

    val path = Path().apply {
        moveTo(
            centerX + radius * kotlin.math.cos(currentAngle),
            centerY + radius * kotlin.math.sin(currentAngle)
        )

        for (i in 1..numPoints) {
            currentAngle += angleIncrement
            lineTo(
                centerX + radius * kotlin.math.cos(currentAngle),
                centerY + radius * kotlin.math.sin(currentAngle)
            )
        }
        close()
    }
    drawPath(path = path, color = color)
}

fun DrawScope.drawHexagon(color: androidx.compose.ui.graphics.Color, size: Size) {
    val numPoints = 6
    val radius = size.minDimension / 2f
    val centerX = size.width / 2f
    val centerY = size.height / 2f
    val angleIncrement = 2 * Math.PI.toFloat() / numPoints
    var currentAngle = -Math.PI.toFloat() / 2 // Start at top

    val path = Path().apply {
        moveTo(
            centerX + radius * kotlin.math.cos(currentAngle),
            centerY + radius * kotlin.math.sin(currentAngle)
        )

        for (i in 1..numPoints) {
            currentAngle += angleIncrement
            lineTo(
                centerX + radius * kotlin.math.cos(currentAngle),
                centerY + radius * kotlin.math.sin(currentAngle)
            )
        }
        close()
    }
    drawPath(path = path, color = color)
}


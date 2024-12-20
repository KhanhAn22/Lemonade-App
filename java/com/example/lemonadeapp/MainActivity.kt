package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Lemonade", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.lemon_tree_tap,
                        drawableResourceId = R.drawable.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.lemon_squeeze,
                        drawableResourceId = R.drawable.lemon_squeeze,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) currentStep = 3
                        }
                    )
                }
                3 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.lemonade_drink,
                        drawableResourceId = R.drawable.lemon_drink,
                        onImageClick = { currentStep = 4 }
                    )
                }
                4 -> {
                    LemonTextAndImage(
                        textLabelResourceId = R.string.empty_glass_start,
                        drawableResourceId = R.drawable.lemon_restart,
                        onImageClick = { currentStep = 1 }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp) // Padding xung quanh để tránh bị sát mép
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(200.dp) // Đặt kích thước cho nút
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize() // Ảnh chiếm toàn bộ kích thước của nút
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp) // Padding cho text
            )
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}

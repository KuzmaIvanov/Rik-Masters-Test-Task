package com.example.rikmasterstesttask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rikmasterstesttask.presentation.ui.components.TestTaskTopAppBar
import com.example.rikmasterstesttask.presentation.navigation.TestTaskNavHost
import com.example.rikmasterstesttask.presentation.navigation.navigateSingleTopTo
import com.example.rikmasterstesttask.presentation.ui.theme.RikMastersTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestTaskApp() {
    RikMastersTestTaskTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TestTaskTopAppBar { route ->
                        navController.navigateSingleTopTo(route)
                    }
                }
            ) { innerPadding ->
                TestTaskNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
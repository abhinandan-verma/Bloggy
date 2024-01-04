package com.abhicoding.bloggy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.abhicoding.bloggy.ui.theme.BloggyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val theme = remember { mutableStateOf(false) }
    BloggyTheme(theme.value) {
        Column (
            Modifier.background(MaterialTheme.colorScheme.background)
        ){
            Text(
                text = "Abhinandan",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "Change Theme")
            }
        }
    }
}
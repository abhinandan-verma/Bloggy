package com.abhicoding.bloggy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Counter()
        }
    }
}

@Composable
fun Counter() {
    val count = remember {
        mutableIntStateOf(0)
    }
    var key = count.intValue % 3 == 0
    LaunchedEffect(key1 = key){
        Log.i("TAG", "Counter value: ${count.intValue}")
    }
    Button(
        onClick = { count.intValue++ },
    ) {
        Text(
            text = "Increment Count",
            Modifier.background(
                Brush.linearGradient(
                    listOf(Color.Cyan, Color.Magenta, Color.Yellow)
                )
            )
        )
    }
}
/*
@Composable
fun ListComposable(){
    val categoryState = remember { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(key1 = Unit){
        categoryState.value = fetchCategories()
    }
    LazyColumn{
        items(categoryState.value){item ->
            Text(text = item)
        }
    }
}
fun fetchCategories(): List<String> {
    return listOf("One", "Two","Three")
}
*/
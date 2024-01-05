package com.abhicoding.bloggy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineScopeComposable()
        }
    }
}

@Composable
fun LaunchEffectComposable() {
    val counter = remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        Log.i("LaunchedEffectComposable", "Started...")
        try {
            for (i in 1..10) {
                counter.intValue++
                Log.i("Tag", "Counter increased to ${counter.intValue}")
                delay(1000)
            }
        } catch (e: Exception) {
            Log.e("LaunchedEffectComposable", "Exception: ${e.message.toString()}")
        }
    }
    var text = "Counter is running ${counter.intValue}"
    if (counter.intValue == 10) {
        text = "Counter Stopped"
    }
    Text(text = text)
}

@Composable
fun CoroutineScopeComposable() {
    val counter = remember {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()
    var text = "Counter is running ${counter.intValue}"
    if (counter.intValue == 10) {
        text = "Counter Stopped"
    }
    Column {
        Text(text = text)
        Button(onClick = { scope.launch {
            Log.i("CoroutineScopeComposable", "Started")
            try {
                for (i in 1..10){
                    counter.intValue++
                    delay(1000)
                }
            }catch (e: Exception){
                Log.d("CoroutineScopeComposable","Exception: ${e.message.toString()}")
            }
        } }) {
            Text(text = "Start")
        }
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
package com.abhicoding.bloggy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun App3() {
    val state = remember {
        mutableStateOf(false)
    }
    DisposableEffect(key1 = state){
        onDispose {  }
    }
}
fun a(){ Log.d("A","I am A from App")}
fun b(){ Log.d("B","I am B from App")}
@Composable
fun App2() {
    val state = remember { mutableStateOf(::a) }
    Button(onClick = { state.value = ::b }) {
        Text(text = "Click to change State")
    }
    LandingScreen(state.value)
}

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
val currentTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(key1 = true){
        delay(5000)
        currentTimeout()
    }
}

@Composable
fun App() {
    val counter = remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = Unit){
        delay(2000)
        counter.intValue = 20
    }
    Counter(counter.intValue)
}

@Composable
fun Counter(counter: Int) {
    LaunchedEffect(key1 = counter){
        delay(5000)
        Log.i("ABHI",counter.toString())
    }
Text(text = counter.toString())
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

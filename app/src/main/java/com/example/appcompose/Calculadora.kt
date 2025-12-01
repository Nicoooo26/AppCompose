package com.example.appcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcompose.ui.theme.AppComposeTheme

class Calculadora : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            app2()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun app2() {
    var radio by remember { mutableStateOf("") }
    var perimetro by remember { mutableStateOf(0.0) }

    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            OutlinedTextField(
                value = radio,
                onValueChange = { radio = it },
                label = { Text("Radio: ") },
                singleLine = true
            )
        }
        item {
            Button(onClick = { perimetro = 2 * 3.14 * radio.toDouble() }) {
                Text("Calcular perimetro")
            }
        }
        item {
            Text(perimetro.toString(), fontSize = 20.sp)
        }
    }
}
package com.example.appcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.appcompose.ui.theme.AppComposeTheme
import org.intellij.lang.annotations.JdkConstants
import kotlin.inc

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWrapper()
            /*Scaffold(
                modifier = Modifier
                    .background(Color(R.color.background)),
                topBar = {
                    topBar()
                },
                content = {
                    content()
                }
            )*/
        }
    }
}

@Preview
@Composable
fun topBar() {
    LazyRow {
        item {
            Text(
                text = "App Profile"
            )
        }
    }
}

@SuppressLint("ResourceAsColor")
@Preview
@Composable
fun content() {
    var counter_likes by rememberSaveable { mutableStateOf(0) }
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp),

        ) {
        item {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                Text(
                    text = "Fernando Profile",
                    color = Color(R.color.white),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                        .wrapContentSize(Alignment.Center)
                )
                Image(
                    painterResource(R.drawable.heart),
                    contentDescription = "likes",
                    Modifier.clickable {
                        counter_likes++
                    }
                )
                Text(
                    text = counter_likes.toString(),
                    )
            }
        }
    }

}
@Composable
fun app() {

    var name by remember { mutableStateOf("") }
    var txtName by remember { mutableStateOf("") }
    val context = LocalContext.current

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        item {
            Image(painterResource(R.drawable.andr), "Android", Modifier.fillMaxWidth())
            OutlinedTextField(
                value = txtName,
                onValueChange = { txtName = it },
                label = { Text("Nombre") },
                singleLine = true
            )
            Text("hola mundo", fontSize = 23.sp, modifier = Modifier.padding(12.dp))
            Text("hola clase", fontSize = 23.sp, modifier = Modifier.padding(12.dp))
            Text("hola a todos", fontSize = 23.sp, modifier = Modifier.padding(18.dp))
            Text(name)
            Button(onClick = { name = txtName }) {
                Text("Pulsar")
            }
            Button(onClick = {
                val intent = Intent(context, Calculadora::class.java)
                context.startActivity(intent)
            }) {
                Text("ir a la calculadora")
            }
        }

    }
}

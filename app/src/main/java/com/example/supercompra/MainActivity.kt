package com.example.supercompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.supercompra.ui.theme.SuperCompraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperCompraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  ImagemTopo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// Componente
@Composable
fun Titulo(modifier: Modifier = Modifier) {
    Text(text = "Lista de Compras",modifier = modifier)

}

@Preview
@Composable
private fun TituloPreview() {
    Titulo()
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ImagemTopo(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.logo_top_supercompra), contentDescription = "Logo",modifier = modifier)
}

@Preview
@Composable
private fun ImagemTopoPreview() {
   SuperCompraTheme {
       ImagemTopo()
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperCompraTheme {
        Greeting("Android")
    }
}
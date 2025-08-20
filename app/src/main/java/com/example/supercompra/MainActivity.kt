package com.example.supercompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supercompra.ui.theme.SuperCompraTheme
import com.example.supercompra.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperCompraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  //ImagemTopo(modifier = Modifier.padding(innerPadding))
                 // Icone(Icons.Default.Add,modifier = Modifier.padding(innerPadding))
                  Titulo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// Componente
@Composable
fun Titulo(modifier: Modifier = Modifier) {
    Text(
        text = "Lista de Compras",
        modifier = modifier,
        style = Typography.headlineLarge
    )

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
    Image(
        painter = painterResource(
            R.drawable.logo_top_supercompra),
        contentDescription = "Logo",
        modifier = modifier.size(160.dp))
}

@Preview
@Composable
private fun ImagemTopoPreview() {
   SuperCompraTheme {
       ImagemTopo()
   }
}

@Composable
fun Icone(icone: ImageVector,modifier: Modifier = Modifier) {
    Icon(icone, contentDescription = "Editar",modifier = modifier)
}

@Preview
@Composable
private fun IconeEditePreview() {
    Icone(Icons.Default.Edit)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperCompraTheme {
        Greeting("Android")
    }
}
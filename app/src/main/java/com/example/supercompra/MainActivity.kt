package com.example.supercompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supercompra.ui.theme.Marinho
import com.example.supercompra.ui.theme.SuperCompraTheme
import com.example.supercompra.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperCompraTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
                { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.padding(innerPadding)

                    )
                    {
                        ImagemTopo()
                        AdicionarItem()
                        Titulo(texto = "Lista de Compras")
                        ItemDaLista()
                        Titulo(texto = "Comprados")
                    }


                }
            }
        }
    }
}

// Componente
@Composable
fun Titulo(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        style = Typography.headlineLarge,
        modifier = modifier,
    )

}

@Preview
@Composable
private fun TituloPreview() {
    Titulo(texto = "Lista de Compras")
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
            R.drawable.logo_top_supercompra
        ),
        contentDescription = "Logo",
        modifier = modifier.size(160.dp)
    )
}

@Preview
@Composable
private fun ImagemTopoPreview() {
    SuperCompraTheme {
        ImagemTopo()
    }
}

@Composable
fun Icone(icone: ImageVector, modifier: Modifier = Modifier) {
    Icon(icone, contentDescription = "Editar", modifier = modifier, tint = Marinho)
}

@Preview
@Composable
private fun IconeEditePreview() {
    Icone(Icons.Default.Edit)
}

@Composable
fun ItemDaLista(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            )
        {
            Checkbox(
                checked = false,
                onCheckedChange = {},
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
            Text(
                text = "Suco",
                style = Typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Icone(
                Icons.Default.Delete,
                Modifier
                    .padding(end = 8.dp)
                    .requiredSize(16.dp),


                )
            Icone(
                Icons.Default.Edit,
                Modifier
                    .size(16.dp),
            )

        }
        Text(
            text = "Segunda-feira (31/10/2025 as 08:30)",
            Modifier.padding(top = 8.dp),
            style = Typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun ItemDaListaPreview() {
    SuperCompraTheme {
        ItemDaLista()
    }
}


@Composable
fun AdicionarItem(modifier: Modifier = Modifier) {
    var texto = rememberSaveable() { mutableStateOf("") }

    OutlinedTextField(
        value = texto.value,
        onValueChange = { texto.value = it },
        placeholder =
            {
                Text(
                    "Digite o item que deseja adicionar",
                    color = Color.Gray,
                    style = Typography.bodyMedium
                )
            },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true,
        shape = RoundedCornerShape(24.dp)
    )
}

@Preview
@Composable
private fun AdicionarItemPreview() {
    SuperCompraTheme {
        AdicionarItem()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperCompraTheme {
        Greeting("Android")
    }
}
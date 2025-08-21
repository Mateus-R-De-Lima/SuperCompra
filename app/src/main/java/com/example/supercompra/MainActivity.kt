package com.example.supercompra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supercompra.ui.theme.Marinho
import com.example.supercompra.ui.theme.SuperCompraTheme
import com.example.supercompra.ui.theme.Typography
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperCompraTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
                { innerPadding ->
                    ListaDeCompras(Modifier.padding(innerPadding))


                }
            }
        }
    }
}

// Componentes

@Composable
fun ListaDeCompras(modifier: Modifier = Modifier) {
    var listaDeItens by rememberSaveable { mutableStateOf(listOf<ItemCompra>()) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier

    )
    {
        ImagemTopo()
        AdicionarItem(aoSalvarItem = { novoItem ->
            listaDeItens = listaDeItens + novoItem
        })
        Spacer(modifier = Modifier.height(48.dp))
        Titulo(texto = "Lista de Compras")

        ListaDeItems(
            lista = listaDeItens.filter { !it.foiComprado },
            aoMudarStatus = { itemSelecionado ->
                listaDeItens = listaDeItens.map { itemMap ->
                    if (itemSelecionado == itemMap) {
                        itemSelecionado.copy(foiComprado = !itemSelecionado.foiComprado)
                    } else {
                        itemMap
                    }
                }
            },
            aoRemoverItem = { itemRemovido ->
                listaDeItens = listaDeItens - itemRemovido
            },
            aoEditarItem = { itemEditado,textoNovo ->
                listaDeItens = listaDeItens.map { itemAtual ->
                    if (itemAtual == itemEditado) {
                        itemAtual.copy(texto = textoNovo)
                    } else {
                        itemAtual
                    }
                }
            },
        )

        Titulo(texto = "Comprados")

        if (listaDeItens.any { it.foiComprado }) {
            ListaDeItems(
                lista = listaDeItens.filter { it.foiComprado },
                aoMudarStatus = { itemSelecionado ->
                    listaDeItens = listaDeItens.map { itemMap ->
                        if (itemSelecionado == itemMap) {
                            itemSelecionado.copy(foiComprado = !itemSelecionado.foiComprado)
                        } else {
                            itemMap
                        }
                    }
                },
                aoRemoverItem = { itemRemovido ->
                    listaDeItens = listaDeItens - itemRemovido
                },
                aoEditarItem = { itemEditado, textoNovo ->
                    listaDeItens = listaDeItens.map { itemAtual ->
                        if (itemAtual == itemEditado) {
                            itemAtual.copy(texto = textoNovo)
                        } else {
                            itemAtual
                        }
                    }
                },
            )
        }


    }
}

@Composable
fun ListaDeItems(
    lista: List<ItemCompra>,
    aoMudarStatus: (item: ItemCompra) -> Unit = {},
    aoRemoverItem: (item: ItemCompra) -> Unit = {},
    aoEditarItem: (item: ItemCompra, textoEditado : String) -> Unit = {_,_ ->},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        lista.forEach { item ->
            ItemDaLista(
                item,
                aoMudarStatus = aoMudarStatus,
                aoRemoverItem = aoRemoverItem,
                aoEditarItem = aoEditarItem,
            )
        }

    }
}

data class ItemCompra(
    val texto: String,
    var foiComprado: Boolean = false,
    val dataHora: String
)

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
fun ItemDaLista(
    itemCompra: ItemCompra,
    aoMudarStatus: (item: ItemCompra) -> Unit = {},
    aoRemoverItem: (item: ItemCompra) -> Unit = {},
    aoEditarItem: (item: ItemCompra, textoEditado : String) -> Unit = {_,_ ->},
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    )
    {
        var textoEditado by rememberSaveable { mutableStateOf(itemCompra.texto) }
        var edicao by rememberSaveable { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            )
        {
            Checkbox(
                checked = itemCompra.foiComprado,
                onCheckedChange = {
                    aoMudarStatus(itemCompra)
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )

            if (edicao) {
                OutlinedTextField(
                    value = textoEditado,
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    onValueChange = {
                        textoEditado = it
                    },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        aoEditarItem(itemCompra, textoEditado)
                        edicao = false

                    },
                ) {
                    Icone(
                        Icons.Default.Done,
                        Modifier
                            .size(16.dp),
                    )
                }
            } else {
                Text(
                    text = itemCompra.texto,
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
            }

            IconButton(
                onClick = { aoRemoverItem(itemCompra) },
                modifier = Modifier.padding(end = 8.dp)

            ) {
                Icone(
                    Icons.Default.Delete,
                    Modifier
                        .size(16.dp),
                )
            }

            IconButton(
                onClick = {
                    edicao = true
                },
            ) {
                Icone(
                    Icons.Default.Edit,
                    Modifier
                        .size(16.dp),
                )
            }


        }
        Text(
            text = itemCompra.dataHora,
            Modifier.padding(top = 8.dp),
            style = Typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun ItemDaListaPreview() {
    SuperCompraTheme {
        ItemDaLista(ItemCompra("Suco",false,"Segunda-Feira"))
    }
}


@Composable
fun AdicionarItem(aoSalvarItem: (item: ItemCompra) -> Unit, modifier: Modifier = Modifier) {
     var texto by rememberSaveable() { mutableStateOf("") }

    OutlinedTextField(
        value = texto,
        onValueChange = { texto = it },
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
    Button(
        {
            aoSalvarItem(ItemCompra(texto,false,getDataHora()))
            texto = ""
        },
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = "Salvar Item",
            color = Color.White,
            style = Typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)

        )
    }
}

fun getDataHora(): String{
    val dataHora = System.currentTimeMillis()
    val dataHoraFormatado = SimpleDateFormat("EEEE (dd/MM/yyyy) 'às' HH:mm",
        java.util.Locale("pt", "BR")
    )
    return  dataHoraFormatado.format(dataHora);
}

@Preview
@Composable
private fun AdicionarItemPreview() {
    SuperCompraTheme {
        AdicionarItem(aoSalvarItem = {})
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperCompraTheme {
        Greeting("Android")
    }
}
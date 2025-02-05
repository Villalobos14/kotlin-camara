package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.example.myapplication.data.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGridScreen(products: List<Product>, onEditProduct: (Product) -> Unit, onDeleteProduct: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "BUTY SPORTOWE", // 📌 Simulación del título original en mayúsculas
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { /* Acción de filtrar */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Filtrar", tint = Color.Black)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(products) { product ->
                ProductCard(product, onEditProduct, onDeleteProduct)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onEditProduct: (Product) -> Unit, onDeleteProduct: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp), // Sombreado sutil
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 📌 Imagen del producto
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = product.image,
                    contentDescription = "Imagen de ${product.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                // 📌 Ícono de favorito
                IconButton(
                    onClick = { /* Acción de favorito */ },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorito", tint = Color.Gray)
                }
            }

            // 📌 Información del producto
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "PRECIO: $${product.price}", // 📌 Texto en mayúsculas
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.name.uppercase(), // 📌 Estilo Adidas en mayúsculas
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 📌 Botones de acción estilo Adidas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // 🔳 Botón Editar: Blanco con borde negro y texto negro
                    OutlinedButton(
                        onClick = { onEditProduct(product) },
                        enabled = true,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(2.dp, Color.Black), // 🔹 Bordes negros gruesos como Adidas
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                    ) {
                        Text("EDITAR", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // 🔳 Botón Eliminar: Fondo negro con texto blanco
                    Button(
                        onClick = { onDeleteProduct(product.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("ELIMINAR", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}


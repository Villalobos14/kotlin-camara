package com.example.myapplication.ui.screens

import AddProductDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.Product
import com.example.myapplication.viewmodel.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(viewModel: ProductListViewModel = viewModel()) {
    val products by viewModel.products.collectAsState()
    val context = LocalContext.current
    var showEditDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getProducts() // ⚠️ Cargar productos al inicio
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tenis", // 📌 Cambio del título
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { viewModel.getProducts() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Actualizar lista", tint = Color.Black)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Color.Black, // 📌 Color negro similar al de Adidas
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar producto")
            }
        }
    ) { padding ->
        if (products.isEmpty()) {
            // 📌 Mensaje cuando no hay productos
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay productos disponibles", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onEditProduct = {
                            selectedProduct = it
                            showEditDialog = true
                        },
                        onDeleteProduct = { productId ->
                            viewModel.deleteProduct(productId) // ⚠️ Borrar producto y actualizar lista
                        }
                    )
                }
            }
        }
    }

    // 📌 Dialogo para editar producto
    if (showEditDialog && selectedProduct != null) {
        EditProductDialog(
            product = selectedProduct!!,
            onDismiss = { showEditDialog = false },
            onUpdateProduct = { newName ->
                selectedProduct?.let {
                    viewModel.updateProduct(it.id, newName)
                }
            }
        )
    }

    // 📌 Dialogo para agregar producto
    if (showAddDialog) {
        AddProductDialog(
            onDismiss = { showAddDialog = false },
            onAddProduct = { name, description, price, imageUrl ->
                viewModel.addProduct(name, description, price, imageUrl)
            }
        )
    }
}



package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.data.model.Product


@Composable
fun EditProductDialog(
    product: Product,
    onDismiss:()->Unit,
    onUpdateProduct:(String)->Unit
){
    var newName by remember { mutableStateOf(product.name) }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
        ) {
          Column (modifier = Modifier.padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)){
              Text("Editar Porducto", style = MaterialTheme.typography.titleLarge)
              TextField(
                  value = newName,
                  onValueChange = {newName=it},
                  label = { Text("Nuevo nombre del producto") }
              )
              Row (
                  horizontalArrangement = Arrangement.SpaceBetween,
                  modifier = Modifier.fillMaxWidth()
              ){
                  Button(onClick = onDismiss, colors = ButtonDefaults.buttonColors(Color.Red )) {
                      Text("Cancelar")
                  }
                  Button( onClick = {
                      if(newName.isNotEmpty()){
                          onUpdateProduct(newName)
                          onDismiss()

                      }
                  }, colors = ButtonDefaults.buttonColors(
                      containerColor = Color(169, 196, 108), // Azul (RGB)
                      contentColor = Color(0,0,0)
                  )) {
                      Text(("Guardar"))

                  }

              }
          }
        }
    }
}
package id.ac.unpas.showroommobile8.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import id.ac.unpas.showroommobile8.model.DataMobil

@Composable
fun PengelolaanDataMobil(){
    val _list = remember { MutableStateFlow(listOf<DataMobil>()) }
    val list by remember { _list }.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanDataMobil { item ->
            val newList = java.util.ArrayList(list)
            newList.add(item)
            _list.value = newList
        }

        LazyColumn(modifier = Modifier.fillMaxWidth() ) {
            items(items = list, itemContent = { item ->

                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "merk", fontSize = 14.sp)
                        Text(
                            text = item.merk, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "model", fontSize = 14.sp)
                        Text(
                            text = item.model, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "bahanBakar", fontSize = 14.sp)
                        Text(
                            text = item.bahanBakar, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "dijual", fontSize = 14.sp)
                        Text(
                            text = "${item.dijual} Kg", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "deskripsi", fontSize = 14.sp)
                        Text(
                            text = item.deskripsi, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }

                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}
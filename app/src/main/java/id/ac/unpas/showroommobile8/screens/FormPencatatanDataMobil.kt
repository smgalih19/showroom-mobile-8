package id.ac.unpas.showroommobile8.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.showroommobile8.model.DataMobil
import id.ac.unpas.showroommobile8.ui.theme.Purple700
import id.ac.unpas.showroommobile8.ui.theme.Teal200

@Composable
fun FormPencatatanDataMobil(onSimpan: (DataMobil) -> Unit){
    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val bahanBakar = remember { mutableStateOf(TextFieldValue("")) }
    val dijual = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Merk")},
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Masukan Merk Mobil")}
            )

        OutlinedTextField(
            label = { Text(text = "Model")},
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Tipe Model Mobil")}
            )

        OutlinedTextField(
            label = { Text(text = "bahanBakar")},
            value = bahanBakar.value,
            onValueChange = {
                bahanBakar.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Tipe Bahan Bakar")}
        )

        OutlinedTextField(
            label = { Text(text = "Dijual")},
            value = dijual.value,
            onValueChange = {
                dijual.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
KeyboardType.Decimal),
            placeholder = { Text(text = "2")}
        )

        OutlinedTextField(
            label = { Text(text = "Deskripsi")},
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Deskripsikan Mobil")}
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val item = DataMobil(merk.value.text, model.value.text, bahanBakar.value.text,
                    dijual.value.text, deskripsi.value.text)
                onSimpan(item)
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                bahanBakar.value = TextFieldValue("")
                dijual.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                bahanBakar.value = TextFieldValue("")
                dijual.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
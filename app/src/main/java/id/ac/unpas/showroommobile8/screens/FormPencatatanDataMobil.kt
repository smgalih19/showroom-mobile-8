package id.ac.unpas.showroommobile8.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.showroommobile8.ui.theme.Purple700
import id.ac.unpas.showroommobile8.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanDataMobil(navController: NavHostController, id: String? = null, modifier: Modifier) {
    val viewModel = hiltViewModel<PengelolaanDataMobilViewModel>()

    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if(isLoading.value) "Mohon tunggu..." else "Simpan"
    var expandDropdown by remember { mutableStateOf(false) }
    var expandDropdownDijual by remember { mutableStateOf(false) }

    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val model = remember { mutableStateOf(TextFieldValue("")) }

    val bahanBakarOptions = listOf("--Pilih Bahan Bakar--","Bensin","Solar","Listrik")
    val (bahan_bakar, setBahanBakar) = remember { mutableStateOf(bahanBakarOptions[0]) }

    val dijualOptions = listOf("--Apakah Dijual--","Ya","Tidak")
    val (dijual, setDijual) = remember {
        mutableStateOf(dijualOptions[0])
    }

    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }


    val icon = if (expandDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val iconDijual = if (expandDropdownDijual)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val scope = rememberCoroutineScope()

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

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = bahan_bakar,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdown = !expandDropdown },
                trailingIcon = {
                    Icon(icon, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )
            DropdownMenu(
                expanded = expandDropdown,
                onDismissRequest = { expandDropdown = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                bahanBakarOptions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            setBahanBakar(label)
                            expandDropdown = false
                        },
                        enabled = label != bahanBakarOptions[0])
                    {
                        Text(text = label)
                    }
                }
            }
        }

        Box(
            modifier = Modifier.padding(top = 8.dp)
        ){
            OutlinedTextField(
                onValueChange = {},
                enabled = false,
                value = dijual,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clickable { expandDropdownDijual = !expandDropdownDijual },
                trailingIcon = {
                    Icon(iconDijual, "dropdown icon")
                },
                textStyle = TextStyle(color = Color.Black)
            )
            DropdownMenu(
                expanded = expandDropdownDijual,
                onDismissRequest = { expandDropdownDijual = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                dijualOptions.forEach { labelDijual ->
                    DropdownMenuItem(
                        onClick = {
                            setDijual(if (labelDijual == "Ya") "1" else  "0")
                            expandDropdownDijual = false
                        },
                        enabled = labelDijual != dijualOptions[0])
                    {
                        Text(text = labelDijual)
                    }
                }
            }
        }


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
                if(id == null) {
                    scope.launch {
                        viewModel.insert(
                            merk.value.text, model.value.text, bahan_bakar,
                            dijual, deskripsi.value.text
                        )
                    }
                }else{
                    scope.launch {
                        viewModel.update(
                            id, merk.value.text, model.value.text, bahan_bakar,
                            dijual, deskripsi.value.text
                        )
                    }
                }
                navController.navigate("pengelolaan-mobil")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                setBahanBakar(bahanBakarOptions[0])
                setDijual(dijualOptions[0])
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
    viewModel.isLoading.observe(LocalLifecycleOwner.current){
        isLoading.value = it
    }
}

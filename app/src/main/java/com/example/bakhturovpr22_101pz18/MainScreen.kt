package com.example.bakhturovpr22_101pz18

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.material3.ButtonDefaults

@Composable
fun MainCard(
    currentDay: MutableState<WeatherModel>,
    onClickSearch: () -> Unit,
    dialogState: MutableState<Boolean>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = currentDay.value.time,
                    modifier = Modifier.padding(8.dp),
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
                AsyncImage(
                    model = "https:${currentDay.value.icon}",
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
            Text(
                text = currentDay.value.city,
                style = TextStyle(fontSize = 24.sp, color = Color.White)
            )
            Text(
                text = "${currentDay.value.currentTemp}ºC",
                style = TextStyle(fontSize = 60.sp, color = Color.White)
            )
            Text(
                text = currentDay.value.condition,
                style = TextStyle(fontSize = 16.sp, color = Color.White)
            )

            Button(
                onClick = { dialogState.value = true },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5))
            ) {
                Text(text = "Выбрать город", color = Color.White)
            }
        }
    }
}

@Composable
fun TabLayout(daysList: MutableState<List<WeatherModel>>, currentDay: MutableState<WeatherModel>, isShowingHours: MutableState<Boolean>) {
    LazyColumn {
        if (isShowingHours.value) {
            itemsIndexed(daysList.value.take(1)) { _, day ->
                ListItemForHours(day, currentDay)
            }
        } else {
            itemsIndexed(daysList.value) { _, day ->
                ListItemForDays(day, currentDay)
            }
        }
    }
}

@Composable
fun ListItemForHours(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { currentDay.value = item },
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = item.time, style = TextStyle(fontSize = 18.sp, color = Color.Black))
                Text(text = item.condition, style = TextStyle(fontSize = 14.sp, color = Color.Gray))
            }
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ListItemForDays(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { currentDay.value = item },
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = item.time, style = TextStyle(fontSize = 18.sp, color = Color.Black))
                Text(text = item.condition, style = TextStyle(fontSize = 14.sp, color = Color.Gray))
            }
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

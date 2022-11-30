package com.jonfhancock.acmeincrouting.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jonfhancock.acmeincrouting.domain.model.Assignment

data class DriverListState(
    val assignments: List<Assignment>, val onDriverSelected: (Assignment) -> Unit
)

@Composable
fun DriverList(state: DriverListState, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(state.assignments) { index, item ->
            Column(Modifier.clickable { state.onDriverSelected(item) }) {
                Text(
                    item.driver.name, fontSize = 24.sp, modifier = Modifier.padding(16.dp)
                )
                if (index != state.assignments.size) {
                    Divider()
                }
            }
        }
    }

}
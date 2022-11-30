package com.jonfhancock.acmeincrouting.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonfhancock.acmeincrouting.domain.model.Assignment

@Composable
fun DriverDetail(selectedAssignment: Assignment, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = selectedAssignment.shipment.displayName,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
    }
}
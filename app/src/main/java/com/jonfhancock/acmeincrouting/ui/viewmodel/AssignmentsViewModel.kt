package com.jonfhancock.acmeincrouting.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jonfhancock.acmeincrouting.domain.ShipmentAndDriverRepository
import com.jonfhancock.acmeincrouting.domain.interactor.CalculateSuitabilityScore
import com.jonfhancock.acmeincrouting.domain.interactor.GetAssignments
import com.jonfhancock.acmeincrouting.domain.model.Assignment
import kotlinx.coroutines.Dispatchers

class AssignmentsViewModel(
    private val getAssignments: GetAssignments
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AssignmentsViewModel(
                    GetAssignments(
                        ShipmentAndDriverRepository(), CalculateSuitabilityScore()
                    )
                )
            }
        }
    }

    val assignments: LiveData<List<Assignment>> = liveData(Dispatchers.IO) {
        emit(getAssignments())
    }

    val selectedAssignment = mutableStateOf<Assignment?>(null)
}
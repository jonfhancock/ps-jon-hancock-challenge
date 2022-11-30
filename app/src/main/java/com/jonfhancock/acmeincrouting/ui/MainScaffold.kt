package com.jonfhancock.acmeincrouting.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.jonfhancock.acmeincrouting.domain.model.Assignment

@Stable
interface MainScaffoldState {
    val assignments: List<Assignment>
    var selectedAssignment: Assignment?
}

private class DefaultMainScaffoldState(
    assignmentsState: State<List<Assignment>>,
    selectedAssignmentState: MutableState<Assignment?>,
) : MainScaffoldState {
    override var selectedAssignment: Assignment? by selectedAssignmentState
    override val assignments: List<Assignment> by assignmentsState
}

@Composable
fun rememberMainScaffoldState(
    assignments: State<List<Assignment>>, selectedAssignment: MutableState<Assignment?>
): MainScaffoldState {
    return remember {
        DefaultMainScaffoldState(
            assignments, selectedAssignment
        )
    }
}

@Composable
fun MainScaffold(state: MainScaffoldState, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(state.selectedAssignment?.driver?.name ?: "Drivers")

        }, navigationIcon = state.selectedAssignment?.let {
            {
                IconButton(onClick = { state.selectedAssignment = null }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        })
    }) { paddingValues ->
        AnimatedContent(targetState = state.selectedAssignment,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            transitionSpec = {
                ContentTransform(
                    slideIntoContainer(targetState?.let { Start } ?: End, animationSpec = spring(
                        Spring.DampingRatioNoBouncy, Spring.StiffnessMediumLow
                    )),
                    slideOutOfContainer(targetState?.let { Start } ?: End, animationSpec = spring(
                        Spring.DampingRatioNoBouncy, Spring.StiffnessMediumLow
                    )),
                )
            }) {
            if (it == null) {
                DriverList(
                    state = DriverListState(assignments = state.assignments,
                        onDriverSelected = { element -> state.selectedAssignment = element }),
                    Modifier.fillMaxSize()
                )
            } else {
                DriverDetail(it, Modifier.fillMaxSize())
                BackHandler() {
                    state.selectedAssignment = null
                }
            }
        }
    }
}
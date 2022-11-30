@file:OptIn(ExperimentalAnimationApi::class)

package com.jonfhancock.acmeincrouting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import com.jonfhancock.acmeincrouting.ui.*
import com.jonfhancock.acmeincrouting.ui.theme.AcmeIncRoutingTheme
import com.jonfhancock.acmeincrouting.ui.viewmodel.AssignmentsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AssignmentsViewModel by viewModels { AssignmentsViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainScaffoldState = rememberMainScaffoldState(
                viewModel.assignments.observeAsState(emptyList()),
                viewModel.selectedAssignment
            )
            AcmeIncRoutingTheme { MainScaffold(mainScaffoldState) }
        }
    }


}
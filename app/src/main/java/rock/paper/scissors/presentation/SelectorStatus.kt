package rock.paper.scissors.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SelectorStatus (
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when (val result = state.value.status) {
        ApplicationStatus.Loading -> {
            LoadingScreen()
        }
        ApplicationStatus.Mock -> {
            Content(
                scoreMen = state.value.scoreMen,
                scoreComp = state.value.scoreComp,
                imageMen = state.value.imageMen,
                imageComp = state.value.imageComp,
                message = state.value.message,
                onClick = viewModel::click
            )
        }
        is ApplicationStatus.Succsess -> {
            WebScreen(
                url = result.url
            )
        }

        is ApplicationStatus.Error -> {
            ErrorScreen(error = result.error)
        }
    }
}
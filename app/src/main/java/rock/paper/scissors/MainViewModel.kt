package rock.paper.scissors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun click(idMen: Int, imageMen: Int) {
        viewModelScope.launch {
            _state.value.copy(
                imageMen = imageMen
            )
                .updateStateUI()
            delay(300)
            val variantComp = variandts.random()
            _state.value.copy(
                imageComp = variantComp.image
            )
                .updateStateUI()
            checkWin(
                idMen = idMen,
                idComp = variantComp.id
            )
        }
    }

    private fun checkWin(
        idMen: Int,
        idComp: Int
    ) {
        if (idMen==idComp) {
            _state.value.copy(
                message = "Nothing win"
            )
                .updateStateUI()
        } else {
            if (idMen==1&&idComp==2) {
                val currentMenScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You win",
                    scoreMen = currentMenScore+1
                )
                    .updateStateUI()
            }
            if (idMen==1&&idComp==3) {
                val currentCompScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You lose",
                    scoreComp = currentCompScore+1
                )
                    .updateStateUI()
            }
            if (idMen==2&&idComp==3) {
                val currentMenScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You win",
                    scoreMen = currentMenScore+1
                )
                    .updateStateUI()
            }
            if (idMen==2&&idComp==1) {
                val currentCompScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You lose",
                    scoreComp = currentCompScore+1
                )
                    .updateStateUI()
            }
            if (idMen==3&&idComp==1) {
                val currentMenScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You win",
                    scoreMen = currentMenScore+1
                )
                    .updateStateUI()
            }
            if (idMen==3&&idComp==2) {
                val currentCompScore = _state.value.scoreMen
                _state.value.copy(
                    message = "You lose",
                    scoreComp = currentCompScore+1
                )
                    .updateStateUI()
            }
        }
    }

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}
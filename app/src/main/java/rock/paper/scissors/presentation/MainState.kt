package rock.paper.scissors.presentation

import rock.paper.scissors.data.variants

data class MainState (
    val status: ApplicationStatus = ApplicationStatus.Loading,
    val scoreMen: Int = 0,
    val scoreComp: Int = 0,
    val message: String = "",
    val imageMen: Int = variants.first().image,
    val imageComp: Int = variants.first().image,
)
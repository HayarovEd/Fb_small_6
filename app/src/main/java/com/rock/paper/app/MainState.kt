package com.rock.paper.app

data class MainState (
    val scoreMen: Int = 0,
    val scoreComp: Int = 0,
    val message: String = "",
    val imageMen: Int = variandts.first().image,
    val imageComp: Int = variandts.first().image,
)
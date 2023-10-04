package rock.paper.scissors.data

import rock.paper.scissors.R

data class Variant(
    val id: Int,
    val image: Int
)

val variants = listOf(
    Variant(
        id = 1,
        image = R.drawable.rock
    ),
    Variant(
        id = 2,
        image = R.drawable.scissors
    ),
    Variant(
        id = 3,
        image = R.drawable.paper
    ),
)

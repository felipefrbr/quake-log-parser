package model

data class Game(
    private val hostname: String,
    var isRunning: Boolean,
    private val players: MutableList<Player> = mutableListOf(),
)

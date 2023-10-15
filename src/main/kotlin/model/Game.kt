package model

data class Game(
    private val hostname: String,
    private var running: Boolean,
    private val world: World,
    private val players: MutableMap<Int, Player> = mutableMapOf(),
) {

    fun isRunning(): Boolean {
        return running
    }
    fun shutdown() {
        running = false
    }
    fun newPlayer(id: Int) {
        players[id] = Player(id = id)
    }

    fun getPlayer(id: Int): Player? {
        return players[id]
    }

    fun getPlayers(): List<Player> {
        return players.values.toList()
    }

    fun getWorld(): World {
        return world
    }
}

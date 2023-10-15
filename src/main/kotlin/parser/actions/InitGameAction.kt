package parser.actions

import model.Game
import model.World

class InitGameAction : GameAction {

    companion object {
        private const val ACTION = "InitGame"
        private val regex = Regex("sv_hostname\\\\([^\\\\]+)")
        private var gameCount = 0L
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game {
        val matchResult = regex.find(line)
        val hostname = matchResult?.groups?.get(1)?.value ?: ""
        val world = World(id = 1022, name = "<world>")
        return Game(id = ++gameCount, hostname = hostname, world = world, running = true)
    }
}

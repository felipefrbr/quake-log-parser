package actions

import model.Game

class InitGameAction : GameAction {

    companion object {
        private const val ACTION = "InitGame"
        private val regex = Regex("sv_hostname\\\\([^\\\\]+)")
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game {
        val matchResult = regex.find(line)
        val hostname = matchResult?.groups?.get(1)?.value ?: "localhost"
        return Game(hostname = hostname, isRunning = true)
    }
}

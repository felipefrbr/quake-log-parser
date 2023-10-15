package actions

import model.Game

class ClientConnectAction : GameAction {

    companion object {
        private const val ACTION = "ClientConnect"
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game? {
        currentGame?.newPlayer(line.toInt())
        return currentGame
    }
}

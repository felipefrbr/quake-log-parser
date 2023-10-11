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
        println("vai adicionar um novo jogador: $line!")
        currentGame?.newPlayer(line.toInt())
        println(currentGame)
        return currentGame
    }
}

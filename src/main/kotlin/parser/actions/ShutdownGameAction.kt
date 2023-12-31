package parser.actions

import model.Game

class ShutdownGameAction : GameAction {

    companion object {
        const val ACTION = "ShutdownGame"
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game? {
        currentGame?.shutdown()
        return currentGame
    }
}

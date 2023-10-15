package parser.actions

import model.Game
import model.Weapon

class KillAction : GameAction {

    companion object {
        private const val ACTION = "Kill"
        private val regex = Regex("^(\\d+) (\\d+) (\\d+): (.+)$")
    }

    override fun match(action: String): Boolean {
        return ACTION == action
    }

    override fun process(line: String, currentGame: Game?): Game? {
        if (currentGame == null) {
            return null
        }

        val matchResult = regex.find(line)

        if (matchResult != null) {
            val (killerId, killedId, weaponId, description) = matchResult.destructured

            val killer = currentGame.getPlayer(killerId.toInt()) ?: currentGame.getWorld().takeIf { it.id == killerId.toInt() }

            val weapon = Weapon.entries[weaponId.toInt()]

            val killed = currentGame.getPlayer(killedId.toInt())

            if (killer != null && killed != null) {
                killer.kill(killed, weapon)
            }
        }
        return currentGame
    }
}

package report

import model.Game

class GameInfoReport {

    fun process(game: Game) {
        println("Game: ${game.id}")

        val playerKills = game.getPlayers()
            .flatMap { it.getKills() }
            .count()

        val worldKills = game.getWorld().getKills().count()

        val totalKills = playerKills + worldKills

        println("Total kills: $totalKills")

        println("Players: ${game.getPlayers().joinToString(", ") { it.name }}")

        // TODO - Manter informacoes basicas do relatorio em um objeto
        // TODO - Criar decorator para o "kills by player"
        // TODO - Criar decorator para o "kills by means"

        println("Kills by Players: $playerKills")
        game.getPlayers().forEach { player ->
            println("  ${player.name}: ${player.getKills().count()}")
        }

        println("Kills by means:")
        val killers = game.getPlayers() + game.getWorld()

        killers
            .flatMap { it.getKills() }
            .groupBy { it.weapon }
            .mapValues { it.value.count() }
            .forEach { (weapon, kills) ->
                println("  $weapon: $kills")
            }

        println()
        println("=================================")
    }
}

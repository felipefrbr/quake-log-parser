import parser.QuakeLogParser

fun main() {
    val games = QuakeLogParser().execute()

    games.forEachIndexed { index, game ->
        println("Game: ${index + 1}")

        val playerKills = game.getPlayers()
            .flatMap { it.getKills() }
            .count()

        val worldKills = game.getWorld().getKills().count()

        val totalKills = playerKills + worldKills

        println("Total kills: $totalKills")

        println("Players: ${game.getPlayers().joinToString(", ") { it.name }}")

        println("Kills by Players: $playerKills")
        game.getPlayers().forEach { player ->
            println("  ${player.name}: ${player.getKills().count()}")
        }

        println()
        println("=================================")
    }
}

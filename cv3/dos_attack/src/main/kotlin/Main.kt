package org.fpeterek.dos


fun main(args: Array<String>) {
    println("Starting attack...")

    if (args.isEmpty()) {
        DosAttack.start()
    } else {
        val threads = args.first().toInt()
        println("Running with $threads thread${ if (threads > 1) "s" else "" }")
        DosAttack.start(threads)
    }

    println("Attack ongoing. Press enter to stop...")
    readLine()
    println("Stopping...")
    DosAttack.stop()
    println("Stopped.")
}

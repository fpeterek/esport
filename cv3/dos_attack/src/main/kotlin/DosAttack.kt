package org.fpeterek.dos

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

object DosAttack {

    private val isRunningValue = AtomicBoolean(false)

    private var isRunning
        get() = isRunningValue.get()
        set(value) = isRunningValue.set(value)

    private var threads = listOf<Thread>()

    private val callerThread
        get() = thread {
            while (isRunning) {
                try {
                    HttpClient.get("127.0.0.1", 8080)
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

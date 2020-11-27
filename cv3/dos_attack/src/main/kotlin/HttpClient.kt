package org.fpeterek.dos

import io.github.rybalkinsd.kohttp.dsl.httpGet


object HttpClient {
    fun get(client: String, targetPort: Int) = httpGet {
        scheme = "http"
        host = client
        port = targetPort
    }
}

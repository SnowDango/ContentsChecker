package cc.snowdango

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.css.*
import kotlinx.html.*

fun main() {
    val server = embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CallLogging)
        install(DefaultHeaders)
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title { +"Hello World" }
                        link(rel = "stylesheet", href = "/index.css", type = "text/css")
                    }
                    body {
                        h1 { +"Hello World" }
                    }
                }
            }
            get("/index.css") {
                call.respondCss {
                    body {
                        backgroundColor = Color.darkBlue
                        margin(0.px)
                    }
                    h1 {
                        color = Color.aqua
                    }
                }
            }
        }
    }
    server.start(wait = true)
}

private suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
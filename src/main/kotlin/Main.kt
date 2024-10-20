package org.example


suspend fun main() {
    val config = loadConfig("/home/user/ProjectIdea/ParsingBook/config.json")
    connectToDatabase(config)
    val client = createHttpClient()
    val url = "https://www.litres.ru/popular/"
    val html = htmlContent(client, url)
    parsingBook(html)
    println("Данные о книгах успешно записаны в базу данных")
}
package org.example

val configAddres = System.getenv("CONFIG_ADDRES")

suspend fun main() {
    val config = loadConfig(configAddres)
    connectToDatabase(config)
    val client = createHttpClient()
    val url = "https://www.litres.ru/popular/"
    val html = htmlContent(client, url)
    val books = parsingBook(html)
    val author = "Виктор Пелевин"
    val filteredBooks = filterBooksByAuthor(books, author)
    val sortedBooks = filterBooksByRating(filteredBooks, 4.0)
    println("Данные о книгах успешно записаны в базу данных")
}
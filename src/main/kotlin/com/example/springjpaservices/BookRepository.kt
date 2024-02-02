package com.example.springjpaservices

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book?, Long?> {
    fun findByBook(book: String): List<Book?>
}
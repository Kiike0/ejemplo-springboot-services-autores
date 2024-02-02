package com.example.springjpaservices

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Services (
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
) {

    @Transactional
    fun addNewBook(idAutor: Long, bookDescription: String): String {
        val autorOptional = authorRepository.findById(idAutor.toInt())

        if (!autorOptional.isPresent) {
            return "Author not found"
        }

        val autor = autorOptional.get()

        val newBook = Book()
        newBook.author = autor
        newBook.book = bookDescription
        bookRepository.save(newBook)

        return "Book added to author with ID: $idAutor"
    }
}
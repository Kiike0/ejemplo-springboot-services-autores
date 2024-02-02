package com.example.springjpaservices

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path=["/demo"])
class MainController {

    @Autowired
    private val bookRepository: BookRepository? = null

    @Autowired
    private val authorRepository: AuthorRepository? = null

    @Transactional
    @PostMapping(path=["/add"])
    @ResponseBody
    fun addNewAuthor(
        @RequestParam name: String?, @RequestParam country: String?
    ): String {
        val person = Author(name = name, country = country!!)
        authorRepository!!.save(person)
        return "Saved"
    }

    @Transactional
    @PostMapping("/addBook")
    @ResponseBody
    fun addNewBook(
        @RequestParam idAutor: Long,
        @RequestParam bookDescription: String
    ): String {
        val autorOptional = authorRepository!!.findById(idAutor.toInt())

        if (!autorOptional.isPresent) {
            return "Author not found"
        }

        val autor = autorOptional.get()

        val newBook = Book()
        newBook.author = autor
        newBook.book = bookDescription
        bookRepository!!.save(newBook)

        return "Book added to author with ID: $idAutor"
    }

    @GetMapping("/all")
    @ResponseBody
    fun getAllUsers(): Iterable<Author?> {
        return authorRepository!!.findAll()
    }

    @GetMapping("/findByname")
    @ResponseBody
    fun getUsersByName(@RequestParam name: String?): Iterable<Author?> {
        return if (name.isNullOrEmpty()) {
            authorRepository!!.findAll()
        } else {
            authorRepository!!.findByName(name)
        }
    }

    /**
     * Devuelve todos los autores nacidos en el pais que le pasamos
     */
    @GetMapping("/findBycountry")
    @ResponseBody
    fun getUsersByCountry(@RequestParam country: String?): Iterable<Author?> {
        return if (country.isNullOrEmpty()) {
            authorRepository!!.findAll()
        } else {
            authorRepository!!.findByCountry(country)
        }
    }

    @Transactional
    @PutMapping("/update")
    @ResponseBody
    fun updateById(
        @RequestParam id: Long?,
        @RequestParam name: String?
    ): String {
        if (id == null) {
            return "Id not valid"
        }

        val autorOptional = authorRepository!!.findById(id.toInt())

        if (!autorOptional.isPresent) {
            return "Author not found"
        }

        val autor = autorOptional.get()
        if (name != null) {
            autor.name = name
        }
        authorRepository.save(autor)
        return "Author updated"
    }

    @Transactional
    @DeleteMapping("/delete")
    @ResponseBody
    fun deleteAuthorByName(@RequestParam name: String?): String {
        if (name.isNullOrEmpty()) {
            authorRepository?.findAll()
            return "Author not found"
        }

        val id = authorRepository?.findByName(name)?.get(0)?.id
        authorRepository?.deleteById(id!!.toInt())
        return "Deleted"

    }
}
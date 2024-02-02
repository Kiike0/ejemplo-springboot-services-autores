package com.example.springjpaservices

import jakarta.persistence.*

@Entity
data class Author (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    var name: String? = null,
    val country: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    val books: MutableList<Book> = mutableListOf()
)
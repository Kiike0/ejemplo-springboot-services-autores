package com.example.springjpaservices

import jakarta.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "autor_id")
    var author: Author? = null

    @Column(name = "book_description")
    var book: String? = null
}
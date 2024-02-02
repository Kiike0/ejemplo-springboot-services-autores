package com.example.springjpaservices

import org.springframework.data.repository.CrudRepository


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
interface AuthorRepository : CrudRepository<Author?, Int?> {
    fun findByName(name: String): List<Author?>
    fun findByCountry(country: String): List<Author?>

}
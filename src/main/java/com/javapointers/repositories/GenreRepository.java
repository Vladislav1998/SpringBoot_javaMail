package com.javapointers.repositories;

import com.javapointers.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre,Integer> {
}

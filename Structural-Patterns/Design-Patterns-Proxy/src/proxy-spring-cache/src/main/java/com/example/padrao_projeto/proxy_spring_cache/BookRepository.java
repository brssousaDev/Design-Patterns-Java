package com.example.padrao_projeto.proxy_spring_cache;


import org.springframework.cache.annotation.Cacheable;

public interface BookRepository {
    @Cacheable("books")
    Book findBookByIsbn(String isbn);
}

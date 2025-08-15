package com.example.padrao_projeto.proxy_spring_cache;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository{
    @Override
    public Book findBookByIsbn(String isbn) {
        System.out.println("\nFetching book with ISBN: " + isbn);
        simulateSlowService();
        return booksMap.get(isbn);
    }

    Map<String, Book> booksMap = Map.of(
            "1", new Book("978-0134685991", "Effective Java", "Joshua Bloch"),
            "2", new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson"),
            "3", new Book("978-1617294945", "Spring in Action", "Craig Walls")
    );

    private void simulateSlowService() {
        try {
            System.out.println("Simulating a slow service...\n");
            Thread.sleep(1600L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}

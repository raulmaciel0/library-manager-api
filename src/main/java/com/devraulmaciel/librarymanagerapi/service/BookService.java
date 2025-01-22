package com.devraulmaciel.librarymanagerapi.service;

import com.devraulmaciel.librarymanagerapi.model.Book;
import com.devraulmaciel.librarymanagerapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

     public BookService(BookRepository bookRepository){
         this.bookRepository = bookRepository;
     }

     public Book addBook(Book book){
         return bookRepository.save(book);
     }

     public List<Book> getAlBooks(){
         return bookRepository.findAll();
     }

     public Optional<Book> findBookById(Long id){
         return bookRepository.findById(id);
     }

     public Book updateBook(Long id, Book updatedBook){
         return bookRepository.findById(id)
                 .map(existingBook -> {
                     existingBook.setTitle(updatedBook.getTitle());
                     existingBook.setAuthor(updatedBook.getAuthor());
                     existingBook.setIsbn(updatedBook.getIsbn());
                     existingBook.setPublishedDate(updatedBook.getPublishedDate());
                     return bookRepository.save(existingBook);
                 }).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
     }

     public void deleteBooK(Long id){
         bookRepository.deleteById(id);
     }
}

package com.getir.readingisgood.application.service;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;
import com.getir.readingisgood.adapters.postgre.repository.BookRepository;
import com.getir.readingisgood.domain.book.AddBookRequest;
import com.getir.readingisgood.domain.book.UpdateStockRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(AddBookRequest addBookRequest) {
        bookRepository.findBookEntitiesByName(addBookRequest.getBookName())
                .ifPresentOrElse(existingBook -> {
                            throw new RuntimeException("book is already exists");
                        },
                        () -> {
                            BookEntity newBook = BookEntity.builder()
                                    .name(addBookRequest.getBookName())
                                    .author(addBookRequest.getAuthor())
                                    .price(addBookRequest.getPrice())
                                    .stock(addBookRequest.getStock())
                                    .build();

                            bookRepository.save(newBook);
                        });
    }


    public void updateStock(UpdateStockRequest updateStockRequest) {

        bookRepository.findBookEntitiesByName(updateStockRequest.getBookName()
        ).ifPresentOrElse(bookEntity -> {
            bookEntity.setStock(updateStockRequest.getQuantity());
            bookRepository.save(bookEntity);
        }, () -> {
            throw new RuntimeException("there is no book");
        });
    }

}

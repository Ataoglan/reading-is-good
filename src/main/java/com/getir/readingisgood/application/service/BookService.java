package com.getir.readingisgood.application.service;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;
import com.getir.readingisgood.adapters.postgre.enums.UpdateStockEnum;
import com.getir.readingisgood.adapters.postgre.repository.BookRepository;
import com.getir.readingisgood.domain.book.AddBookRequest;
import com.getir.readingisgood.domain.book.AddBookResponse;
import com.getir.readingisgood.domain.book.UpdateStockRequest;
import com.getir.readingisgood.domain.book.UpdateStockResponse;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public AddBookResponse addBook(AddBookRequest addBookRequest){
        bookRepository.findBookEntitiesByName(addBookRequest.getBookName()
        ).map(bookEntity -> {
                    throw new RuntimeException("book is already exists");
        });

        BookEntity newBook = BookEntity.builder()
                .name(addBookRequest.getBookName())
                .author(addBookRequest.getAuthor())
                .price(addBookRequest.getPrice())
                .build();

        bookRepository.save(newBook);

        return new AddBookResponse(true);
    }

   // @Retryable(value = SQLException.class)
    public UpdateStockResponse updateStock(UpdateStockRequest updateStockRequest){
        if (updateStockRequest.getStockType().equals(UpdateStockEnum.INCREASE)){
            bookRepository.findBookEntitiesByName(updateStockRequest.getBookName()
            ).map(bookEntity -> {
                        int stock = bookEntity.getStock();
                        stock = stock+ updateStockRequest.getQuantity();
                        bookEntity.setStock(stock);

                        bookRepository.save(bookEntity);

                        return new UpdateStockResponse(true);
            });
        }else if (updateStockRequest.getStockType().equals(UpdateStockEnum.DECREASE)){
            return null;
        }
        return new UpdateStockResponse(false);
    }


}

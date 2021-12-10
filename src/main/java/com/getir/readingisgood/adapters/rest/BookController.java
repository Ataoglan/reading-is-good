package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.BookService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.book.AddBookRequest;
import com.getir.readingisgood.domain.book.UpdateStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure/book/")
@RequiredArgsConstructor
public class BookController {
    final private BookService bookService;

    @PostMapping("v1/addBook")
    private void addBook(@RequestBody @Validated AddBookRequest addBookRequest) {
        bookService.addBook(addBookRequest);
    }

    @PostMapping("v1/updateStock")
    private void updateStock(@RequestBody @Validated UpdateStockRequest updateStockRequest){
        bookService.updateStock(updateStockRequest);
    }

}

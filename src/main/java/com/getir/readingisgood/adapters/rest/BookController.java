package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.BookService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.book.AddBookRequest;
import com.getir.readingisgood.domain.book.UpdateStockRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure/book/")
public class BookController {
    final private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("v1/addBook")
    private ApiResponse addBook(@RequestBody @Validated AddBookRequest addBookRequest) {
        return ApiResponse.success(bookService.addBook(addBookRequest));
    }

    @PostMapping("v1/updateStock")
    private ApiResponse updateStock(@RequestBody @Validated UpdateStockRequest updateStockRequest){
        return ApiResponse.success(bookService.updateStock(updateStockRequest));
    }

}

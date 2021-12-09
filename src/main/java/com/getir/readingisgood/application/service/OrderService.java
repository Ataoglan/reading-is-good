package com.getir.readingisgood.application.service;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;
import com.getir.readingisgood.adapters.postgre.entity.OrderEntity;
import com.getir.readingisgood.adapters.postgre.enums.UpdateStockEnum;
import com.getir.readingisgood.adapters.postgre.repository.BookRepository;
import com.getir.readingisgood.adapters.postgre.repository.OrderRepository;
import com.getir.readingisgood.adapters.postgre.repository.UserRepository;
import com.getir.readingisgood.domain.book.UpdateStockRequest;
import com.getir.readingisgood.domain.order.CreateOrderRequest;
import com.getir.readingisgood.domain.order.CreateOrderResponse;
import com.getir.readingisgood.domain.order.GetOrderByDateRequest;
import com.getir.readingisgood.domain.order.GetOrderRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, UserService userService, BookRepository bookRepository, BookService bookService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;

    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest){
        userRepository.findById(createOrderRequest.getUserId()).map(userEntity -> {

            Map<String, Long> groupingByName = createOrderRequest.getBook().stream().collect(
                    Collectors.groupingBy(BookEntity::getName, Collectors.counting())
            );

            for (Map.Entry<String, Long> item:groupingByName.entrySet()
                 ) {
                bookRepository.findBookEntitiesByName(item.getKey()).ifPresent(book->{
                    UpdateStockRequest request = new UpdateStockRequest();
                    request.setAuthor(book.getAuthor());
                    request.setBookName(book.getName());
                    request.setQuantity(Math.toIntExact(item.getValue()));
                    request.setStockType(UpdateStockEnum.DECREASE);

                    bookService.updateStock(request);

                });
            }

            OrderEntity newOrder = OrderEntity.builder()
                    .user(userEntity)
                    .book(createOrderRequest.getBook())
                    .totalPrice(createOrderRequest.getTotalPrice())
                    .quantity(createOrderRequest.getTotalAmount())
                    .build();

            orderRepository.save(newOrder);

            return new CreateOrderResponse(true);

        }).orElseThrow(RuntimeException::new);

        return new CreateOrderResponse(false);
    }


    public OrderEntity getOrder(GetOrderRequest getOrderRequest) {
        return orderRepository.findById(getOrderRequest.getOrderId()).orElse(null);
    }

}

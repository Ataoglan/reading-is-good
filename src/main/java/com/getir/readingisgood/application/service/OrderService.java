package com.getir.readingisgood.application.service;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;
import com.getir.readingisgood.adapters.postgre.entity.OrderDetailsEntity;
import com.getir.readingisgood.adapters.postgre.entity.OrderEntity;
import com.getir.readingisgood.adapters.postgre.repository.BookRepository;
import com.getir.readingisgood.adapters.postgre.repository.OrderDetailsRepository;
import com.getir.readingisgood.adapters.postgre.repository.OrderRepository;
import com.getir.readingisgood.adapters.postgre.repository.UserRepository;
import com.getir.readingisgood.application.utils.context.ContextManager;
import com.getir.readingisgood.domain.order.CreateOrderRequest;
import com.getir.readingisgood.domain.order.GetOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Transactional
    @Retryable(value = {ObjectOptimisticLockingFailureException.class, OptimisticLockException.class},
            maxAttempts = 6, backoff = @Backoff(maxDelay = 600))
    public void createOrder(CreateOrderRequest createOrderRequest) {

        userRepository.findByEmail(ContextManager.get().getEmail()).ifPresent(customer -> {
            List<OrderDetailsEntity> detailsEntityList = new ArrayList<>();
            List<BookEntity> books = new ArrayList<>();

            createOrderRequest.getBookAndQuantity()
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        Long bookId = entry.getKey();
                        Integer quantity = entry.getValue();

                        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow();
                        if (bookEntity.getStock() >= quantity) {
                            OrderDetailsEntity orderDetails = OrderDetailsEntity.builder()
                                    .unitPrice(bookEntity.getPrice())
                                    .quantity(quantity)
                                    .bookId(bookEntity.getId())
                                    .totalPrice(bookEntity.getPrice() * quantity)
                                    .build();
                            bookEntity.setStock(bookEntity.getStock()-quantity);
                            detailsEntityList.add(orderDetails);
                            books.add(bookEntity);

                        }else {
                            throw new RuntimeException("out of order");
                        }

                        double totalPrice = detailsEntityList.stream().mapToDouble(OrderDetailsEntity::getTotalPrice)
                                .sum();
                        int totalQuantity = detailsEntityList.stream().mapToInt(OrderDetailsEntity::getQuantity).sum();

                        OrderEntity newOrder = OrderEntity.builder()
                                .user(customer)
                                .totalPrice(totalPrice)
                                .totalQuantity(totalQuantity)
                                .build();

                        OrderEntity order = orderRepository.save(newOrder);

                        detailsEntityList.forEach(detail->detail.setOrderId(order));

                        orderDetailsRepository.saveAll(detailsEntityList);
                        bookRepository.saveAll(books);

                        return true;
                    });

        });
    }

    public OrderEntity getOrder(GetOrderRequest getOrderRequest) {
        return orderRepository.findById(getOrderRequest.getOrderId()).orElse(null);
    }

}

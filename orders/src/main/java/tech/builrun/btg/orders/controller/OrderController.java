package tech.builrun.btg.orders.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.builrun.btg.orders.dto.ApiResponse;
import tech.builrun.btg.orders.dto.OrderResponse;
import tech.builrun.btg.orders.dto.PaginationResponse;
import tech.builrun.btg.orders.service.OrderService;

import java.util.Map;

@RestController()
@RequestMapping("/customer")
@Tag(name = "Order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }


    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@RequestParam(name = "page",defaultValue = "0") Integer page, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                                                 @PathVariable("customerId") Long customerId){

        var body=this.orderService.FindAllByCustomerId(customerId, PageRequest.of(page,pageSize));
        var totalOnOrders=this.orderService.FindTotalOnOrderByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(Map.of("totalOnOrders",totalOnOrders),body.getContent(), PaginationResponse.fromPage(body)));

    }

}

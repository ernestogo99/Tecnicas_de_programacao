package tech.builrun.btg.orders.mapper;



import org.springframework.stereotype.Component;
import tech.builrun.btg.orders.dto.OrderCreatedEvent;
import tech.builrun.btg.orders.dto.OrderItemEvent;
import tech.builrun.btg.orders.dto.OrderResponse;
import tech.builrun.btg.orders.entity.OrderEntity;
import tech.builrun.btg.orders.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;



@Component
public class OrderEntityMapper {

    public OrderEntity toEntity(OrderCreatedEvent event){
        return OrderEntity.builder().orderId(event.codigoPedido()).customerId(event.codigoCliente()).items(this.toOrderItem(event.itens())).build();
    }




    private List<OrderItemEvent> toOrderItemEvent(List<OrderItem> items){
        return items.stream()
                .map(item -> new OrderItemEvent(
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());
    }

    private List<OrderItem> toOrderItem(List<OrderItemEvent> events){
        return events.stream()
                .map(event -> {
                    OrderItem item = new OrderItem();
                    item.setProduct(event.produto());
                    item.setQuantity(event.quantidade());
                    item.setPrice(event.preco());
                    return item;
                })
                .collect(Collectors.toList());
    }


}

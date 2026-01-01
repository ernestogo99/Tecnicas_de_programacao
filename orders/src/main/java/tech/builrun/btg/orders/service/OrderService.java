package tech.builrun.btg.orders.service;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import tech.builrun.btg.orders.dto.OrderCreatedEvent;
import tech.builrun.btg.orders.dto.OrderResponse;
import tech.builrun.btg.orders.entity.OrderEntity;
import tech.builrun.btg.orders.mapper.OrderEntityMapper;
import tech.builrun.btg.orders.repository.OrderRepository;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderEntityMapper mapper;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository repository, OrderEntityMapper mapper,MongoTemplate mongoTemplate){
        this.repository=repository;
        this.mapper=mapper;
        this.mongoTemplate=mongoTemplate;
    }

    public void saveOrderEvent(OrderCreatedEvent event){
        OrderEntity orderEntity=this.mapper.toEntity(event);
        orderEntity.setTotal(this.getTotal(event));
        this.repository.save(orderEntity);

    }

    public Page<OrderResponse> FindAllByCustomerId(Long customerId,PageRequest pageRequest){
        var orderEntity= this.repository.findAllByCustomerId(customerId, pageRequest);

        return orderEntity.map(OrderResponse::toOrderResponse);
    }

    public BigDecimal FindTotalOnOrderByCustomerId(Long customerId){
        var aggregations= newAggregation(
                match(Criteria.where("customerId").is(customerId)),
        group().sum("total").as("total")
        );

        var response= mongoTemplate.aggregate(aggregations,"db_orders", Document.class);
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    private BigDecimal getTotal(OrderCreatedEvent event){
        return  event.itens().stream().map(i->i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}

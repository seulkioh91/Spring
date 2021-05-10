package taco.data;

import taco.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}

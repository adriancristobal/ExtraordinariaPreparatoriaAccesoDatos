package org.example.domain.service.txt;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.example.domain.dao.txt.OrderDao;
import org.example.model.txt.OrderItemTXT;
import org.example.model.txt.OrderTXT;

import java.util.List;

public class OrderService {

    private final OrderDao dao;

    @Inject
    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public List<OrderTXT> getAll() {
        return dao.getAll();
    }

    public List<OrderTXT> getAll(int idCustomer) {
        return dao.getAll(idCustomer);
    }

    public Either<String, String> add(OrderTXT order, List<OrderItemTXT> items) {
        Either<Integer, String> result = dao.add(order, items);
        if (result.isLeft()) {
            switch (result.getLeft()) {
                case 1:
                    return Either.left("Customer with ID " + order.getCustomer_id() + " does not exist.");
                case 2:
                    return Either.left("Menu item with ID " + items.get(0).getMenu_item_id() + " does not exist.");
                default:
                    return Either.left("Unknown error.");
            }
        }
        return Either.right("Order added successfully.");
    }
}

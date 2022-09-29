package com.training.springstart.service.order;

import com.training.springstart.model.Order;
import com.training.springstart.util.PagingEntity.PagingEntity;
import java.util.List;

public interface OrdersTableService {

    List<Order> getPageAllNotDeleted(PagingEntity pagingEntity);

}

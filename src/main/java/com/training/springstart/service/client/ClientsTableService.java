package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.util.PagingEntity.PagingEntity;

import java.util.List;

public interface ClientsTableService {

    List<Client> getPageAllNotDeleted(PagingEntity pagingEntity);

}

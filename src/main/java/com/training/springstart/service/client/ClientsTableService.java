package com.training.springstart.service.client;

import com.training.springstart.model.Client;
import com.training.springstart.util.PagingEntity.PagingEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientsTableService {

    Page<Client> getPageAllNotDeleted(Integer page, Integer size, String sort);

}

package com.training.springstart.util.config;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ClientMapper extends CustomMapper<Client, ClientAreaViewDTO> {

    @Override
    public void mapBtoA(ClientAreaViewDTO clientAreaViewDTO, Client client, MappingContext context) {
        super.mapBtoA(clientAreaViewDTO, client, context);
    }

    @Override
    public void mapAtoB(Client client, ClientAreaViewDTO clientAreaViewDTO, MappingContext context) {
        super.mapAtoB(client, clientAreaViewDTO, context);
    }
}

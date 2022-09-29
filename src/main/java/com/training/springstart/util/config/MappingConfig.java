package com.training.springstart.util.config;

import com.training.springstart.model.Client;
import com.training.springstart.model.dto.*;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.jetbrains.annotations.NotNull;

public class MappingConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(@NotNull MapperFactory MapperFactory) {

        MapperFactory
                .classMap(Client.class, ClientAdminDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientAreaViewDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientChangePassDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientDatePromoDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientDateDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientLoginDTO.class)
                .byDefault()
                .register();

        MapperFactory
                .classMap(Client.class, ClientAreaViewDTO.class)
                .customize(new ClientMapper())
                .byDefault()
                .register();

    }
}

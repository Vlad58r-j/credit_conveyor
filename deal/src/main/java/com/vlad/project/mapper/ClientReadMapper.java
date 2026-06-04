package com.vlad.project.mapper;

import com.vlad.project.database.entity.Client;
import com.vlad.project.dto.ClientReadDto;
import org.springframework.stereotype.Component;

@Component
public class ClientReadMapper implements Mapper<Client, ClientReadDto> {

    @Override
    public ClientReadDto map(Client client) {

        return new ClientReadDto(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getMiddleName(),
                client.getEmail(),
                client.getBirthDate());
    }
}

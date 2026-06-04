package com.vlad.project.mapper;

import com.vlad.project.database.entity.Client;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.enumStatus.Gender;
import org.springframework.stereotype.Component;

@Component
public class ClientEditMapper implements Mapper<LoanApplicationRequestDto, Client>{

    @Override
    public Client map(LoanApplicationRequestDto dto) {
        Client client = new Client();
        copy(dto, client);

        return client;
    }

    private void copy(LoanApplicationRequestDto dto, Client client) {
        client.setLastname(dto.getLastName());
        client.setFirstname(dto.getFirstName());
        client.setMiddleName(dto.getMiddleName());
        client.setBirthDate(dto.getBirthdate());
        client.setEmail(dto.getEmail());
        client.setGender(Gender.NON_BINARY);
    }


}

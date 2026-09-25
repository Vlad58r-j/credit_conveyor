package com.vlad.project.mapper;

import com.vlad.project.database.entity.Application;
import com.vlad.project.dto.ApplicationReadDto;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadMapper implements Mapper<Application, ApplicationReadDto> {

    @Override
    public ApplicationReadDto map(Application dto) {
        return new ApplicationReadDto(
                dto.getId(),
                dto.getStatus(),
                dto.getCreationDate(),
                dto.getClient());
    }
}

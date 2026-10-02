package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {

    private String address;
    private Theme theme;
    private Long applicationId;
}

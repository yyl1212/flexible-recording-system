package com.wiw.pinyee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DataShowDTO {

    @NotNull
    private Long tableId;

    private Long id;
}

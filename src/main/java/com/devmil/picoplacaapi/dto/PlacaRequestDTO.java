package com.devmil.picoplacaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlacaRequestDTO {
    private String placa;
    private LocalDateTime fechaHora;
}

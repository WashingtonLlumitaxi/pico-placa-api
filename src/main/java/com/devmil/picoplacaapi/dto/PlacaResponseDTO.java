package com.devmil.picoplacaapi.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacaResponseDTO {
    private String placa;
    private boolean circulacion; //verificar si es necesaria
    private String mensaje;
}

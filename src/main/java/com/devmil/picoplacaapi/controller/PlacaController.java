package com.devmil.picoplacaapi.controller;

import com.devmil.picoplacaapi.dto.PlacaRequestDTO;
import com.devmil.picoplacaapi.dto.PlacaResponseDTO;
import com.devmil.picoplacaapi.service.PlacaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin //Intercambio de datos con otros origenes
@AllArgsConstructor
@RequestMapping("/api/v1/placas")
@RestController
public class PlacaController {
    //Inyeccion de dependencias
    private final PlacaService placaService;

    @PostMapping("/validar")
    public ResponseEntity<PlacaResponseDTO> validar(@RequestBody PlacaRequestDTO placaRequest) {
        PlacaResponseDTO placaResponse = placaService.validarCirculacion(placaRequest.getPlaca(), placaRequest.getFechaHora());
        return ResponseEntity.ok(placaResponse);
    }
}

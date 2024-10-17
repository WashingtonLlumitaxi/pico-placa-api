package com.devmil.picoplacaapi.service;

import com.devmil.picoplacaapi.dto.PlacaResponseDTO;
import com.devmil.picoplacaapi.exceptions.PlacaException;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PlacaService {

    public PlacaResponseDTO validarCirculacion(String placa, LocalDateTime fechaHora) {
        //Validar placa vacia
        if (placa == null || placa.isEmpty()) {
            //return crearRespuesta(placa, false, "La placa no puede ir vacia ");
            throw new PlacaException("La placa no puede ir vacia");
        }

        //Validar formato
        if (!formatoPlaca(placa)) {
            //return crearRespuesta(placa, false, "Formato de la placa invalido");
            throw new PlacaException("El fórmato de la placa es inválido");
        }

        //Validar fecha actual
        if (fechaHora.isBefore(LocalDateTime.now())) {
            //return crearRespuesta(placa, false, "La fecha y hora no pueden ser anteriores a la actual");
            throw new PlacaException("La fecha y hora no pueden ser anteriores a la actual");
        }


        //Restricciones pico y placa
        boolean puedeCircular = validarRestriccionPP(placa, fechaHora);
        String mensaje = puedeCircular ? "Puede Circular" : "No puede circular";

        return crearRespuesta(placa, puedeCircular, mensaje);
    }


    //Validar formato de la placa
    private boolean formatoPlaca(String placa) {
        //AAA1324 - Patrón de la placa
        return placa.matches("[A-Z]{3}[0-9]{4}");
    }

    //Validar dias de pico y placa
    private boolean validarRestriccionPP(String placa, LocalDateTime fechaHora) {
        int ultimoDigito = Character.getNumericValue(placa.charAt(placa.length() - 1));
        DayOfWeek diaSemana = fechaHora.getDayOfWeek();
        LocalTime hora = fechaHora.toLocalTime();

        switch (diaSemana) {
            case MONDAY:
                return !((ultimoDigito == 1 || ultimoDigito == 2) && horaRestriccion(hora));
            case TUESDAY:
                return !((ultimoDigito == 3 || ultimoDigito == 4) && horaRestriccion(hora));
            case WEDNESDAY:
                return !((ultimoDigito == 5 || ultimoDigito == 6) && horaRestriccion(hora));
            case THURSDAY:
                return !((ultimoDigito == 7 || ultimoDigito == 8) && horaRestriccion(hora));
            case FRIDAY:
                return !((ultimoDigito == 9 || ultimoDigito == 0) && horaRestriccion(hora));
            default:
                return true;
        }
    }

    //Verficar horario de restricción
    private boolean horaRestriccion(LocalTime hora) {
        LocalTime inicioManana = LocalTime.of(6, 0);
        LocalTime finMañana = LocalTime.of(9, 30);
        LocalTime inicioTarde = LocalTime.of(16, 0);
        LocalTime finTarde = LocalTime.of(20, 0);

        return (hora.isAfter(inicioManana.minusSeconds(1)) && hora.isBefore(finMañana)) || (hora.isAfter(inicioTarde.minusSeconds(1)) && hora.isBefore(finTarde));
    }

    //  Respuesta de validación
    private PlacaResponseDTO crearRespuesta(String placa, boolean puedeCircular, String mensaje) {
        PlacaResponseDTO response = new PlacaResponseDTO();
        response.setPlaca(placa);
        response.setCirculacion(puedeCircular);
        response.setMensaje(mensaje);
        return response;
    }
}

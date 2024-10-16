package com.devmil.picoplacaapi.service;

import com.devmil.picoplacaapi.dto.PlacaResponseDTO;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PlacaService {

    public PlacaResponseDTO validarCirculacion(String placa, LocalDateTime fechaHora) {

//        PlacaResponseDTO placaValidacionResponseDTO = new PlacaResponseDTO();
//        placaValidacionResponseDTO.setPlaca(placa);

        //Placa vacia
        if(placa == null || placa.isEmpty()) {
            return  crearRespuesta(placa, false, "La placa no puede ir vacia ");
            //return false;
        }

        //Validar formato
        if(!formatoPlaca(placa)) {  //ok
//            placaValidacionResponseDTO.setMensaje("Formato de la placa inválido");
//            return placaValidacionResponseDTO;
            return crearRespuesta(placa,false,"Formato de la placa invalido");
        }

        //Validar fecha actual
        if(fechaHora.isBefore(LocalDateTime.now())){
//            placaValidacionResponseDTO.setMensaje("La fecha y hora no pueden ser anteriores a la actual");
//            return placaValidacionResponseDTO;

            return  crearRespuesta(placa,false,"La fecha y hora no pueden ser anteriores a la actual");
        }


        //Restricciones pico y placa
        boolean puedeCircular = validarRestriccionPP(placa, fechaHora);
        String mensaje = puedeCircular ? "Puede Circular" : "No puede circular";

        return crearRespuesta(placa,puedeCircular,mensaje);
    }


    //Verificar formato de la placa
    private boolean formatoPlaca(String placa){
        //AAA1324 - Patrón de la placa
        return placa.matches("[A-Z]{3}[0-9]{4}");
    }

    //Validar resticciones de pico y placa
    private boolean validarRestriccionPP(String placa, LocalDateTime fechaHora) {
        int ultimoDigito = Character.getNumericValue(placa.charAt(placa.length() -1));
        DayOfWeek diaSemana = fechaHora.getDayOfWeek();
        LocalTime hora = fechaHora.toLocalTime();

        switch (diaSemana) {
            case MONDAY :
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

    //Verficar Hora de restricción
    private boolean horaRestriccion(LocalTime hora) {
        LocalTime inicioManana = LocalTime.of(6, 0);
        LocalTime finMañana = LocalTime.of(9, 30);
        LocalTime inicioTarde = LocalTime.of(16,0);
        LocalTime finTarde = LocalTime.of(20, 0);

        return (hora.isAfter(inicioManana.minusSeconds(1)) && hora.isBefore(finMañana)) || (hora.isAfter(inicioTarde.minusSeconds(1)) && hora.isBefore(finTarde));
    }

    //  Crear una respuesta de validación
    private PlacaResponseDTO crearRespuesta(String placa, boolean puedeCircular, String mensaje) {
        PlacaResponseDTO response = new PlacaResponseDTO();
        response.setPlaca(placa);
        response.setCirculacion(puedeCircular);
        response.setMensaje(mensaje);
        return response;
    }

    private boolean validarPicoPlaca(String placa, LocalDateTime fechaHora) {
        //Placa vacia
        if(placa == null || placa.isEmpty()) {
            return false;
        }

        //Obtener Ultimo digito de la placa
        char utimoCaracter = placa.charAt(placa.length() -1);
        if(!Character.isDigit(utimoCaracter)){
            return false;
        }
        int ultimoDigito = Character.getNumericValue(utimoCaracter);

        // Obtener el día de la semana
        DayOfWeek diaDeLaSemana = fechaHora.getDayOfWeek();

        // Obtener la hora actual
        LocalTime horaActual = fechaHora.toLocalTime();

        // Definir los rangos restringidos
        LocalTime inicioMañana = LocalTime.of(6, 0);
        LocalTime finMañana = LocalTime.of(9, 30);
        LocalTime inicioTarde = LocalTime.of(16, 0);
        LocalTime finTarde = LocalTime.of(20, 0);

        // Verificar si la hora actual cae dentro de los rangos restringidos
        boolean enHoraRestringida = (horaActual.isAfter(inicioMañana) && horaActual.isBefore(finMañana)) ||
                (horaActual.isAfter(inicioTarde) && horaActual.isBefore(finTarde));

        if (!enHoraRestringida) {
            return true; // Si no está en una hora restringida, puede circular
        }

        // Verificar las restricciones por día de la semana y último dígito de la placa
        switch (diaDeLaSemana) {
            case MONDAY:
                return ultimoDigito != 1 && ultimoDigito != 2;
            case TUESDAY:
                return ultimoDigito != 3 && ultimoDigito != 4;
            case WEDNESDAY:
                return ultimoDigito != 5 && ultimoDigito != 6;
            case THURSDAY:
                return ultimoDigito != 7 && ultimoDigito != 8;
            case FRIDAY:
                return ultimoDigito != 9 && ultimoDigito != 0;
            default:
                return true;
        }


    }
}

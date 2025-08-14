package br.vianna.trabalho.sigei.model.dto;

import java.util.List;

public class DiaCalendarioDTO {
    private int dia;
    private List<EventoListDTO> eventos;

    public DiaCalendarioDTO(int dia, List<EventoListDTO> eventos) {
        this.dia = dia;
        this.eventos = eventos;
    }

    public int getDia() {
        return dia;
    }

    public List<EventoListDTO> getEventos() {
        return eventos;
    }
}

package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class Actividad {
    private String fechaIngresada;
    private Double tiempoRequerido;
    private String descripcion;
    private Integer idProyecto;
    private String dniEjecutor;
    private String dniPlanificador;
    private String fechaPlanificada;
    private Double tiempoPlanificado;
    private Integer idActividad;
    private Integer planificado;
}

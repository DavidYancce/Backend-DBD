package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class PlanificadoVsRegistrado {
    private String proyecto;
    private String fechaReportada;
    private String fechaPlanificada;
    private String diferencia;
    private String tiempoRegistrado;
    private String tiempoPlanificado;
}

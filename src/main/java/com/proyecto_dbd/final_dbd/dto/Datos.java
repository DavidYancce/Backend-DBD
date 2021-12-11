package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class Datos {
    private Integer idProyecto;
    private Integer idLinea;
    private String estado;
    private String nombreProyecto;
    private String RUC;
    private String fechaInicio;
    private String fechaFin;
    private String dni;
    private String descripcion;
}

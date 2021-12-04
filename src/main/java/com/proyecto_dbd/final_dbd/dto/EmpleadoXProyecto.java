package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class EmpleadoXProyecto {
    private String DNI;
    private Integer idProyecto;
    private String rol;
    private String descripcion;
}

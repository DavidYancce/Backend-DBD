package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class Empleado {
    private String DNI;
    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreCompleto;
    private String correoEmpresarial;
    private String contrasenia;
    private String telefono;
    private String genero;
    private String estado;
    private String direccion;
    private String fechaNacimiento;
    private Integer idCargo;
    private Double sueldo;

}

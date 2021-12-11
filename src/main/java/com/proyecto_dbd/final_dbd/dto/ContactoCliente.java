package com.proyecto_dbd.final_dbd.dto;

import lombok.Data;

@Data
public class ContactoCliente {
    private String ruc;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private String direccion;
}

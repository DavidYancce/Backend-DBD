package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.dto.Empleado;

import java.util.List;

public interface FinalService {
    List<Cliente> obtenerClientes();
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad (Actividad actividad);
}

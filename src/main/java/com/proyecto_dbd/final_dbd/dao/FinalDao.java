package com.proyecto_dbd.final_dbd.dao;

import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Cliente;

import java.util.List;

public interface FinalDao {
    List<Cliente> obtenerClientes();
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad(Actividad actividad);
}

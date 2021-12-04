package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.dto.Empleado;

import java.util.List;

public interface FinalService {
    public abstract List<Cliente> obtenerClientes();
    public abstract Cliente insertarCliente(Cliente cliente);
}

package com.proyecto_dbd.final_dbd.dao;

import com.proyecto_dbd.final_dbd.dto.Cliente;

import java.util.List;

public interface FinalDao {
    public abstract List<Cliente> obtenerClientes();
    public abstract Cliente insertarCliente(Cliente cliente);
}

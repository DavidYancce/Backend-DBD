package com.proyecto_dbd.final_dbd.dao;

import com.proyecto_dbd.final_dbd.dto.*;

import java.util.List;

public interface FinalDao {
    List<Cliente> obtenerClientes();
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad(Actividad actividad);
    EmpleadoXProyecto insertarEmpleadoXProyecto (EmpleadoXProyecto empleadoXProyecto);
    List<LineaNegocio> obtenerLineasNegocio();
    List<DashboardHoraXLinea> horaPorLinea();
    DashboardHoraXLinea horaPorLineaEspecifica(LineaNegocio lineaNegocio);
}

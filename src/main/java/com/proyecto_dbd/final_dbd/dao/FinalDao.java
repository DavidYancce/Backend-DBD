package com.proyecto_dbd.final_dbd.dao;
import com.proyecto_dbd.final_dbd.dto.*;

import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.dto.Proyecto;


import java.util.List;

public interface FinalDao {
    List<Cliente> obtenerClientes();
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad(Actividad actividad);
    EmpleadoXProyecto insertarEmpleadoXProyecto (EmpleadoXProyecto empleadoXProyecto);
    List<LineaNegocio> obtenerLineasNegocio();
    List<DashboardHoraXLinea> horaPorLinea();
    DashboardHoraXLinea horaPorLineaEspecifica(LineaNegocio lineaNegocio);
    List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(Proyecto proyecto);
    List<PlanificadoVsRegistrado> obtenerPlanificadoVsRegistrado();
    List<PlanificadoVsRegistrado> obtenerProyectoPlanificadoVsRegistrado(Proyecto proyecto);
    List<Proyecto> obtenerProyectoFull();
    ContactoCliente insertarContactoCliente (ContactoCliente contactocliente);
}

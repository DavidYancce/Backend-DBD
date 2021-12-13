package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dto.*;

import java.util.Date;
import java.util.List;

public interface FinalService {
    List<Cliente> obtenerClientes();
    List<Cargo> obtenerCargo();
    List<Empleado> obtenerEmpleado();
    Empleado login(Empleado empleado);
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad (Actividad actividad);
    List<Proyecto> obtenerProyectos();
    List<Actividad> obtenerActividades();
    List<Empleado> obtenerJefesProyecto();
    List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(RangoFechas Fecha);
    List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(Proyecto proyecto);
    List<PlanificadoVsRegistrado> obtenerProyectoPlanificadoVsRegistrado(Proyecto proyecto);
    List<Empleado> obtenerColaboradores(Proyecto proyecto);
    List<RegTablaEmp> busquedaEmpleados(FiltrosBE filtro);
    Empleado insertarEmpleado(Empleado empleado);
    EmpleadoXProyecto insertarEmpleadoXProyecto(EmpleadoXProyecto empleadoXProyecto);
    ContactoCliente insertarContactoCliente (ContactoCliente contactocliente);
    List<Proyecto> obtenerProyectoFull();
    Actividad actualizarActividad (Actividad actividad);
    String insertarProyecto(Datos datos);
    List<FiltrosBP> busquedaProyecto(FiltrosBP filtro);
    List<LineaNegocio> obtenerLineasNegocio();
    List<DashboardHoraXLinea> horaPorLinea(RangoFechas Fechas);
    List<Actividad> actividadesXEmpleadoXProyecto(FiltroEmpleadoProyecto filtro);
}

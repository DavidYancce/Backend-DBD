package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dao.FinalDao;
import com.proyecto_dbd.final_dbd.daoDZ.FinalDaoDZ;
import com.proyecto_dbd.final_dbd.daoMC.FinalDaoMC;
import com.proyecto_dbd.final_dbd.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FinalServiceImpl implements FinalService{

    @Autowired
    private FinalDao dao;
    @Autowired
    private FinalDaoDZ daoDZ;
    @Autowired
    private FinalDaoMC daoMC;

    public List<Cargo> obtenerCargo() {return daoMC.obtenerCargo();}

    public List<Empleado> obtenerEmpleado() {return daoMC.obtenerEmpleado();}

    public Empleado login(Empleado empleado) {return daoMC.login(empleado);}

    public List<Cliente> obtenerClientes() {
        return dao.obtenerClientes();
    }

    public Cliente insertarCliente(Cliente cliente) {
        return dao.insertarCliente(cliente);
    }

    public Actividad insertarActividad(Actividad actividad) {
        return dao.insertarActividad(actividad);
    }

    public Empleado insertarEmpleado(Empleado empleado) { return daoMC.insertarEmpleado(empleado); }

    public EmpleadoXProyecto insertarEmpleadoXProyecto(EmpleadoXProyecto empleadoXProyecto) { return dao.insertarEmpleadoXProyecto(empleadoXProyecto); }

    public List<Proyecto> obtenerProyectoFull() { return dao.obtenerProyectoFull(); }

    public Datos insertarProyecto(Datos datos) {
        return daoMC.insertarProyecto(datos);
    }
    public List<FiltrosBP> busquedaProyecto(FiltrosBP filtro) { return daoMC.busquedaProyecto(filtro); }

    public List<LineaNegocio> obtenerLineasNegocio() {
        return dao.obtenerLineasNegocio();
    }

    public List<DashboardHoraXLinea> horaPorLinea(RangoFechas Fechas) {
        return dao.horaPorLinea(Fechas);
    }

    public List<Proyecto> obtenerProyectos() {
        return daoDZ.obtenerProyectos();
    }

    public List<Actividad> obtenerActividades(FiltroEmpleadoProyecto filtro) {
        return daoDZ.obtenerActividades(filtro);
    }

    public List<Empleado> obtenerJefesProyecto() {
        return daoDZ.obtenerJefesProyecto();
    }

    public List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(RangoFechas Fechas) {
        return daoDZ.obtenerHorasRegistradasProyecto(Fechas);
    }

    public List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(Proyecto proyecto) {
        return dao.obtenerEmpleadoXProyecto(proyecto);

    }

    public List<PlanificadoVsRegistrado> obtenerProyectoPlanificadoVsRegistrado(Proyecto proyecto) {
        return dao.obtenerProyectoPlanificadoVsRegistrado(proyecto);
    }

    public List<Empleado> obtenerColaboradores(Proyecto proyecto){
        return daoDZ.obtenerColaboradores(proyecto);
    }

    public List<RegTablaEmp> busquedaEmpleados(FiltrosBE filtro) {
        return daoDZ.busquedaEmpleados(filtro);
    }
    public ContactoCliente insertarContactoCliente (ContactoCliente contactocliente) {
        return dao.insertarContactoCliente(contactocliente);
    }

    public Actividad actualizarActividad (Actividad actividad) {
        return dao.actualizarActividad(actividad);
    }

    public List<RegTablaAct> obtenerRegsActividad(Empleado empleado) {
        return daoDZ.obtenerRegsActividad(empleado);
    }
}

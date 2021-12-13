package com.proyecto_dbd.final_dbd.controlador;

import com.proyecto_dbd.final_dbd.dto.*;
import com.proyecto_dbd.final_dbd.servicio.FinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST}, allowedHeaders="*")

public class FinalControlador {

    @Autowired
    private FinalService service;

    @RequestMapping(
            value = "/validar-login",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Empleado login(@RequestBody Empleado empleado) {return service.login(empleado);}

    @RequestMapping(
            value = "/obtener-cargos",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Cargo> obtenerCargo() {return service.obtenerCargo();}

    @RequestMapping(
            value = "/obtener-empleados",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Empleado> obtenerEmpleado() {return service.obtenerEmpleado();}

    @RequestMapping(
            value = "/obtener-clientes",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Cliente> obtenerClientes(){
        return service.obtenerClientes();
    }

    @RequestMapping(
            value = "/insertar-cliente",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Cliente obtenerNoticiasTendencia(@RequestBody Cliente cliente){
        return service.insertarCliente(cliente);
    }

    @RequestMapping(
            value = "/insertar-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Actividad insertarActividad(@RequestBody Actividad actividad){
        return service.insertarActividad(actividad);
    }

    @RequestMapping(
            value = "/insertar-empleado",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Empleado insertarEmpleado(@RequestBody Empleado empleado){
        return service.insertarEmpleado(empleado);
    }

    @RequestMapping(
            value = "/obtener-proyectos",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public  @ResponseBody List<Proyecto> obtenerProyectos(){
        return service.obtenerProyectos();
    }

    @RequestMapping(
            value = "/obtener-actividades",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public  @ResponseBody List<Actividad> obtenerActividades(){
        return service.obtenerActividades();
    }

    @RequestMapping(
            value = "/obtener-jefes-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public  @ResponseBody List<Empleado> obtenerJefesProyecto(){
        return service.obtenerJefesProyecto();
    }

    @RequestMapping(
            value = "/obtener-horas-registradas-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public  @ResponseBody List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(@RequestBody RangoFechas Fecha){
        return service.obtenerHorasRegistradasProyecto(Fecha);
    }

    @RequestMapping(
            value = "/horas-empleado-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(@RequestBody Proyecto proyecto) {
        return service.obtenerEmpleadoXProyecto(proyecto);
    }

    @RequestMapping(
            value = "/proyecto-registrado-planificado",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<PlanificadoVsRegistrado> obtenerProyectoPlanificadoVsRegistrado(@RequestBody Proyecto proyecto) {
        return service.obtenerProyectoPlanificadoVsRegistrado(proyecto);
    }

    @RequestMapping(
            value = "/obtener-colaboradores",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Empleado> obtenerColaboradores(@RequestBody Proyecto proyecto) {
        return service.obtenerColaboradores(proyecto);
    }

    @RequestMapping(
            value = "/busqueda-empleados",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<RegTablaEmp> busquedaEmpleados(@RequestBody FiltrosBE filtro) {
        return service.busquedaEmpleados(filtro);
    }

    @RequestMapping(
            value = "/busqueda-proyectos",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<FiltrosBP> busquedaProyecto(@RequestBody FiltrosBP filtro) {
        return service.busquedaProyecto(filtro);
    }

    @RequestMapping(
            value = "/insertar-empleados-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody  EmpleadoXProyecto insertarEmpleadoXProyecto (@RequestBody  EmpleadoXProyecto empleadoXProyecto) {
        return service.insertarEmpleadoXProyecto(empleadoXProyecto);
    }

    @RequestMapping(
            value = "/obtener-proyectos-full",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Proyecto> obtenerProyectoFull() {
        return service.obtenerProyectoFull();
    }

    @RequestMapping(
            value = "/insertar-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody String insertarProyecto(@RequestBody Datos datos) {
        return service.insertarProyecto(datos);
    }
    @RequestMapping(
            value = "/insertar-contacto-cliente",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody ContactoCliente insertarContactoCliente(@RequestBody ContactoCliente contactocliente){
        return service.insertarContactoCliente(contactocliente);
    }

    @RequestMapping(
            value = "/actualizar-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Actividad actualizarActividad(@RequestBody Actividad actividad){
        return service.actualizarActividad(actividad);
    }

    @RequestMapping(
            value = "/obtener-lineas",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<LineaNegocio> obtenerLineasNegocio(){
        return service.obtenerLineasNegocio();
    }
    @RequestMapping(
            value = "/tabla-lineas",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<DashboardHoraXLinea> horaPorLinea(@RequestBody RangoFechas Fechas){
        return service.horaPorLinea(Fechas);
    }

    @RequestMapping(
            value = "/obtener-actividades-empleado-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Actividad> actividadesXEmpleadoXProyecto(@RequestBody FiltroEmpleadoProyecto filtro){
        return service.actividadesXEmpleadoXProyecto(filtro);
    }

    @RequestMapping(
                value = "/obtener-registros-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<RegTablaAct> obtenerRegsActividad(@RequestBody Empleado empleado){
        return service.obtenerRegsActividad(empleado);
    }
    
}


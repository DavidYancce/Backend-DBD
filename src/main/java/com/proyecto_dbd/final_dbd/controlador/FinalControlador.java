package com.proyecto_dbd.final_dbd.controlador;
import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.dto.DashboardHoraXLinea;
import com.proyecto_dbd.final_dbd.dto.LineaNegocio;
import com.proyecto_dbd.final_dbd.dto.*;
import com.proyecto_dbd.final_dbd.servicio.FinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST})

public class FinalControlador {

    @Autowired
    private FinalService service;

    @RequestMapping(
            value = "/obtener-clientes",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Cliente> obtenerNoticiasTendencia(){
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

            value = "/obtener-lineas-negocio",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<LineaNegocio> obtenerLineasNegocio(){
        return service.obtenerLineasNegocio();
    }

    @RequestMapping(
            value = "/obtener-horas-linea",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<DashboardHoraXLinea> obtenerHoraLinea(){
        return service.horaPorLinea();
    }

    @RequestMapping(
            value = "/obtener-horas-linea-especifica",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody DashboardHoraXLinea horaPorLineaEspecifica(@RequestBody LineaNegocio lineaNegocio){
        return service.horaPorLineaEspecifica(lineaNegocio);
    };

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
    public  @ResponseBody List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(@RequestBody String FechaA, @RequestBody String FechaB){
        return service.obtenerHorasRegistradasProyecto(FechaA, FechaB);
    }
}

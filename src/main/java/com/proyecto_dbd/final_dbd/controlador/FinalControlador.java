package com.proyecto_dbd.final_dbd.controlador;

import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.servicio.FinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dto.*;

import java.util.Date;
import java.util.List;

public interface FinalService {
    List<Cliente> obtenerClientes();
    Cliente insertarCliente(Cliente cliente);
    Actividad insertarActividad (Actividad actividad);
    List<Proyecto> obtenerProyectos();
    List<Actividad> obtenerActividades();
    List<Empleado> obtenerJefesProyecto();
    List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(String FechaA, String FechaB);
}

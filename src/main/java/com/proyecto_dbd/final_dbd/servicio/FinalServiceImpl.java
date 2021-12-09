package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dao.FinalDao;
import com.proyecto_dbd.final_dbd.dao.dao_hz.FinalDao_hz;
import com.proyecto_dbd.final_dbd.daoDZ.FinalDaoDZ;
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
    private FinalDao_hz dao_hz;

    public List<Cliente> obtenerClientes() {
        return dao.obtenerClientes();
    }

    public Cliente insertarCliente(Cliente cliente) {
        return dao.insertarCliente(cliente);
    }

    public Actividad insertarActividad(Actividad actividad) {
        return dao.insertarActividad(actividad);
    }

    public List<Proyecto> obtenerProyectos() {
        return daoDZ.obtenerProyectos();
    }

    public List<Actividad> obtenerActividades() {
        return daoDZ.obtenerActividades();
    }

    public List<Empleado> obtenerJefesProyecto() {
        return daoDZ.obtenerJefesProyecto();
    }

    public List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(String FechaA, String FechaB) {
        return daoDZ.obtenerHorasRegistradasProyecto(FechaA, FechaB);
    }

    public List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(String nombreProyecto) {
        return dao_hz.obtenerEmpleadoXProyecto(nombreProyecto);
    }
}

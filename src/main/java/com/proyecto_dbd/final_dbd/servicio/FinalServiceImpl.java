package com.proyecto_dbd.final_dbd.servicio;

import com.proyecto_dbd.final_dbd.dao.FinalDao;
import com.proyecto_dbd.final_dbd.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional

public class FinalServiceImpl implements FinalService{

    @Autowired
    private FinalDao dao;

    public List<Cliente> obtenerClientes() {
        return dao.obtenerClientes();
    }

    public Cliente insertarCliente(Cliente cliente) {
        return dao.insertarCliente(cliente);
    }

    public Actividad insertarActividad(Actividad actividad) {
        return dao.insertarActividad(actividad);
    }

    public EmpleadoXProyecto insertarEmpleadoXProyecto(EmpleadoXProyecto empleadoXProyecto) {
        return dao.insertarEmpleadoXProyecto(empleadoXProyecto);
    }

    public List<LineaNegocio> obtenerLineasNegocio() {
        return dao.obtenerLineasNegocio();
    }

    public List<DashboardHoraXLinea> horaPorLinea() {
        return dao.horaPorLinea();
    }

    public DashboardHoraXLinea horaPorLineaEspecifica(LineaNegocio lineaNegocio) {
        return dao.horaPorLineaEspecifica(lineaNegocio);
    }


}

package com.proyecto_dbd.final_dbd.daoMC;
import com.proyecto_dbd.final_dbd.dto.*;

import java.util.List;

public interface FinalDaoMC {
    List<Cargo> obtenerCargo();
    List<Empleado> obtenerEmpleado();
    Empleado login(Empleado empleado);
    Empleado insertarEmpleado(Empleado empleado);
    List<FiltrosBP> busquedaProyecto(FiltrosBP filtro);
    Datos insertarProyecto(Datos datos);
}

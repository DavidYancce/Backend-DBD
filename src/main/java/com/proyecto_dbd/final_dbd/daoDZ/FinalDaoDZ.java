package com.proyecto_dbd.final_dbd.daoDZ;
import com.proyecto_dbd.final_dbd.dto.*;

import java.util.List;

public interface FinalDaoDZ {
    List<Proyecto> obtenerProyectos();
    List<Actividad> obtenerActividades();
    List<Empleado> obtenerJefesProyecto();
    List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(RangoFechas Fechas);
    List<Empleado> obtenerColaboradores(Proyecto proyecto);
    List<RegTablaEmp> busquedaEmpleados(FiltrosBE filtro);
    List<RegTablaAct> obtenerRegsActividad(Empleado empleado);
}

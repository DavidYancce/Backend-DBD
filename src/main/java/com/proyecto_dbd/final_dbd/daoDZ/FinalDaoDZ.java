package com.proyecto_dbd.final_dbd.daoDZ;
import com.proyecto_dbd.final_dbd.dto.Actividad;
import com.proyecto_dbd.final_dbd.dto.Empleado;
import com.proyecto_dbd.final_dbd.dto.HorasRegistradasProyecto;
import com.proyecto_dbd.final_dbd.dto.Proyecto;
import java.util.List;

public interface FinalDaoDZ {
    List<Proyecto> obtenerProyectos();
    List<Actividad> obtenerActividades();
    List<Empleado> obtenerJefesProyecto();
    List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(String FechaA, String FechaB);
}

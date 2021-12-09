package com.proyecto_dbd.final_dbd.daoDZ;

import com.proyecto_dbd.final_dbd.dao.FinalDao;
import com.proyecto_dbd.final_dbd.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinalDaoImplDZ implements FinalDaoDZ {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //===================PROYECTO===============================
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT * FROM Proyecto ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setIdProyecto(rs.getInt("idproyecto"));
                proyecto.setNombreProyecto(rs.getString("nombreproyecto"));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setFechaFin(rs.getString("fechafin"));
                proyecto.setFechaInicio(rs.getString("fechainicio"));
                proyecto.setRUC(rs.getString("ruc"));
                proyecto.setIdLinea(rs.getInt("idlinea"));
                proyectos.add(proyecto);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return proyectos;
    }

    public List<Actividad> obtenerActividades() {
        List<Actividad> actividades = new ArrayList<Actividad>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT * FROM Actividad ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdProyecto(rs.getInt("idproyecto"));
                actividad.setFechaIngresada(rs.getString("fechaingresada"));
                actividad.setTiempoRequerido(rs.getDouble("tiemporequerido"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setDniEjecutor(rs.getString("dni_ejecutor"));
                actividad.setDniPlanificador(rs.getString("dni_planificador"));
                actividad.setFechaPlanificada(rs.getString("fechaplanificada"));
                actividad.setTiempoPlanificado(rs.getDouble("tiempoplanificado"));
                actividad.setPlanificado(rs.getInt("planificado"));
                actividad.setIdActividad(rs.getInt("idactividad"));
                actividades.add(actividad);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actividades;
    }

    public List<Empleado> obtenerJefesProyecto() {
        List<Empleado> jefesproyecto = new ArrayList<Empleado>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT DNI, NombreCompleto FROM Empleado WHERE idcargo=3";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Empleado jefeproyecto = new Empleado();
                jefeproyecto.setDNI(rs.getString("dni"));
                jefeproyecto.setNombreCompleto(rs.getString("nombrecompleto"));
                jefesproyecto.add(jefeproyecto);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jefesproyecto;
    }

    public List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(String FechaA, String FechaB) {
        List<HorasRegistradasProyecto> registros = new ArrayList<HorasRegistradasProyecto>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT pr.nombreProyecto, SUM(ac.tiempoRequerido) AS \"Suma de horas\" " +
                    " FROM actividad as ac " +
                    " LEFT JOIN proyecto as pr " +
                    " ON pr.idproyecto=ac.idproyecto " +
                    " WHERE pr.estado !='FN' " +
                    " AND ac.fechaIngresada BETWEEN ? AND ? " +
                    " GROUP BY (pr.nombreProyecto ";
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, FechaA);
            ps.setString(2, FechaB);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HorasRegistradasProyecto registro = new HorasRegistradasProyecto();
                registro.setNombreProyecto(rs.getString("pr.nombreProyecto"));
                registro.setSumaHoras(rs.getDouble("Suma de horas"));
                registros.add(registro);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return registros;
    }
}
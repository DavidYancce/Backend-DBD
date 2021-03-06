package com.proyecto_dbd.final_dbd.daoDZ;

import com.proyecto_dbd.final_dbd.dao.FinalDao;
import com.proyecto_dbd.final_dbd.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
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
            String sentenciaSQL = " SELECT * FROM Proyecto ORDER BY nombreProyecto";
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

    public List<Actividad> obtenerActividades(FiltroEmpleadoProyecto filtro) {

        List<Actividad> actividades = new ArrayList<>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " select * from actividad where idproyecto=? and planificado=1 and dni_ejecutor=? and fechaingresada is null";
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, filtro.getIdProyecto());
            ps.setString(2, filtro.getDniEmpleado());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setFechaIngresada(rs.getString("fechaingresada"));
                actividad.setTiempoRequerido(rs.getDouble("tiemporequerido"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setIdProyecto(rs.getInt("idproyecto"));
                actividad.setDniEjecutor(rs.getString("dni_ejecutor"));
                actividad.setDniPlanificador(rs.getString("dni_planificador"));
                actividad.setFechaPlanificada(rs.getString("fechaplanificada"));
                actividad.setTiempoPlanificado(rs.getDouble("tiempoplanificado"));
                actividad.setPlanificado(rs.getInt("planificado"));
                actividades.add(actividad);
            }
            rs.close();
            ps.close();
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

    public List<HorasRegistradasProyecto> obtenerHorasRegistradasProyecto(RangoFechas Fechas) {
        List<HorasRegistradasProyecto> registros = new ArrayList<HorasRegistradasProyecto>();
        String sentenciaSQL = " SELECT pr.nombreProyecto, SUM(ac.tiempoRequerido) " +
                " FROM actividad as ac " +
                " LEFT JOIN proyecto as pr " +
                " ON pr.idproyecto=ac.idproyecto " +
                " WHERE pr.estado !='FN' " +
                " AND ac.fechaIngresada BETWEEN ? AND ? " +
                " GROUP BY (pr.nombreProyecto) ";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setDate(1, Date.valueOf(Fechas.getFechaInicio()));
            ps.setDate(2, Date.valueOf(Fechas.getFechaFin()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HorasRegistradasProyecto registro = new HorasRegistradasProyecto();
                registro.setNombreProyecto(rs.getString("nombreproyecto"));
                registro.setSumaHoras(rs.getDouble("SUM"));
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

    public List<Empleado> obtenerColaboradores(Proyecto proyecto) {
        List<Empleado> colaboradores = new ArrayList<Empleado>();
        String sentenciaSQL = " Select E.DNI, E.NombreCompleto " +
                "FROM EmpleadoXProyecto  AS EP " +
                "JOIN Empleado AS E " +
                "ON E.DNI=EP.DNI " +
                "WHERE EP.IdProyecto=? ";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, proyecto.getIdProyecto());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado colaborador = new Empleado();
                colaborador.setDNI(rs.getString("dni"));
                colaborador.setNombreCompleto(rs.getString("nombrecompleto"));
                colaboradores.add(colaborador);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return colaboradores;
    }

    public List<RegTablaEmp> busquedaEmpleados(FiltrosBE filtro) {
        List<RegTablaEmp> registros = new ArrayList<RegTablaEmp>();
        if(filtro.getDNI()==null){
            filtro.setDNI("");
        }
        if(filtro.getNombreCompleto()==null){
            filtro.setNombreCompleto("");
        }
        if(filtro.getApellidoMaterno()==null){
            filtro.setApellidoMaterno("");
        }
        if(filtro.getApellidoPaterno()==null){
            filtro.setApellidoPaterno("");
        }
        if(filtro.getRol()==null){
            filtro.setRol("");
        }
        String sentenciaSQL = " SELECT E.*, P.NombreProyecto, EP.Rol " +
                "FROM Empleado AS E " +
                "JOIN EmpleadoxProyecto AS EP " +
                "ON EP.DNI=E.DNI " +
                "JOIN Proyecto AS P " +
                "ON EP.IdProyecto=P.IdProyecto " +
                "WHERE " +
                "1= CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN E.DNI LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(E.NombreCompleto) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(E.ApellidoPaterno) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(E.ApellidoMaterno) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(EP.Rol) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END ";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, filtro.getDNI());
            ps.setString(2, filtro.getDNI()+"%");
            ps.setString(3, filtro.getNombreCompleto().toUpperCase());
            ps.setString(4, "%"+filtro.getNombreCompleto().toUpperCase()+"%");
            ps.setString(5, filtro.getApellidoPaterno().toUpperCase());
            ps.setString(6, "%"+filtro.getApellidoPaterno().toUpperCase()+"%");
            ps.setString(7, filtro.getApellidoMaterno().toUpperCase());
            ps.setString(8, "%"+filtro.getApellidoMaterno().toUpperCase()+"%");
            ps.setString(9, filtro.getRol().toUpperCase());
            ps.setString(10, "%"+filtro.getRol().toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegTablaEmp registro = new RegTablaEmp();
                registro.setDNI(rs.getString("dni"));
                if(rs.getString("nombre2")==null){
                    registro.setNombre(rs.getString("nombre1"));
                } else{
                    registro.setNombre(rs.getString("nombre1")+" "+rs.getString("nombre2"));
                }
                registro.setCorreoEmpresarial(rs.getString("correoempresarial"));
                registro.setTelefono(rs.getString("telefono"));
                registro.setApellidos(rs.getString("apellidoPaterno")+" "+rs.getString("apellidoMaterno"));
                registro.setNombreProyecto(rs.getString("nombreProyecto"));
                registro.setRol(rs.getString("rol"));
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

    public List<RegTablaAct> obtenerRegsActividad(Empleado empleado) {
        List<RegTablaAct> registros = new ArrayList<RegTablaAct>();
        String sql=" SELECT P.nombreproyecto, A.planificado, A.fechaingresada, A.descripcion, A.tiemporequerido " +
                " FROM Actividad AS A, Proyecto AS P " +
                " WHERE A.idproyecto=P.idproyecto AND A.dni_ejecutor=?";
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,empleado.getDNI());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                RegTablaAct registro = new RegTablaAct();
                registro.setDescripcion(rs.getString("descripcion"));
                registro.setPlanificado(rs.getInt("planificado"));
                registro.setFechaIngresada(rs.getString("fechaingresada"));
                registro.setNombreProyecto(rs.getString("nombreproyecto"));
                registro.setTiempoRequerido(rs.getDouble("tiemporequerido"));
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

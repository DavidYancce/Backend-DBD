package com.proyecto_dbd.final_dbd.dao;

import com.proyecto_dbd.final_dbd.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class FinalDaoImpl implements FinalDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //===================CLIENTES===============================
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT * FROM Cliente ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setPais(rs.getString("pais"));
                cliente.setRUC(rs.getString("RUC"));
                cliente.setRazonSocial(rs.getString("RazonSocial"));
                clientes.add(cliente);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientes;
    }

    public Cliente insertarCliente(Cliente cliente) {
        String SQL=" INSERT INTO Cliente(RUC, razonSocial, Pais, Direccion) " +
                " VALUES(?,?,?,?)";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, cliente.getRUC());
            ps.setString(2, cliente.getRazonSocial());
            ps.setString(3, cliente.getPais());
            ps.setString(4, cliente.getDireccion());

            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cliente;
    }

    public Actividad insertarActividad(Actividad actividad){
        //Dependiendo de si es una actividad planificada o no se ejecuta una sentencia u otra

        LocalDate fechaActual = LocalDate.now(ZoneId.of("GMT-05:00"));
        String SQL_Planificado=" INSERT INTO Actividad( " +
                " descripcion, idproyecto, dni_ejecutor, dni_planificador, fechaplanificada, tiempoplanificado, planificado) " +
                " VALUES (?, ?, ?, ?, ?, ?,?)";

        String SQL_NoPlanificado = "INSERT INTO actividad( " +
                " fechaingresada, tiemporequerido, descripcion, idproyecto, dni_ejecutor, planificado) " +
                " VALUES (?, ?, ?, ?, ?, ?) ";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            if(actividad.getPlanificado()==1){
                PreparedStatement ps = con.prepareStatement(SQL_Planificado);
                ps.setString(1,actividad.getDescripcion());
                ps.setInt(2,actividad.getIdProyecto());
                ps.setString(3,actividad.getDniEjecutor());
                ps.setString(4,actividad.getDniPlanificador());
                ps.setDate(5,Date.valueOf(fechaActual));
                ps.setDouble(6,actividad.getTiempoPlanificado());
                ps.setInt(7,actividad.getPlanificado());
                ps.executeUpdate();
                ps.close();
            }
            else if (actividad.getPlanificado()==0){
                PreparedStatement ps = con.prepareStatement(SQL_NoPlanificado);
                ps.setDate(1,Date.valueOf(fechaActual));
                ps.setDouble(2, actividad.getTiempoRequerido());
                ps.setString(3,actividad.getDescripcion());
                ps.setInt(4,actividad.getIdProyecto());
                ps.setString(5,actividad.getDniEjecutor());
                ps.setInt(6,actividad.getPlanificado());
                ps.executeUpdate();
                ps.close();
            }
            con.commit();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actividad;
    }

    //Testeado
    public EmpleadoXProyecto insertarEmpleadoXProyecto (EmpleadoXProyecto empleadoXProyecto){
        String SQL=" INSERT INTO public.empleadoxproyecto( " +
                " dni, idproyecto, rol, descripcion) " +
                " VALUES (?, ?, ?, ?) ";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, empleadoXProyecto.getDNI());
            ps.setInt(2, empleadoXProyecto.getIdProyecto());
            ps.setString(3, empleadoXProyecto.getRol());
            ps.setString(4, empleadoXProyecto.getDescripcion());

            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleadoXProyecto;
    }
    //testeado
    public List<LineaNegocio> obtenerLineasNegocio(){
        List<LineaNegocio> lineasNegocio = new ArrayList<>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT * FROM LineaNegocio ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while(rs.next()) {
                LineaNegocio lineaNegocio = new LineaNegocio();
                lineaNegocio.setIdLinea(rs.getInt("idlinea"));
                lineaNegocio.setNombreLinea(rs.getString("nombrelinea"));
                lineasNegocio.add(lineaNegocio);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lineasNegocio;
    }
    //Testeado
    public List<DashboardHoraXLinea> horaPorLinea(){
        List<DashboardHoraXLinea> resultados = new ArrayList<>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT LN.nombrelinea, SUM(A.tiemporequerido) " +
                    " FROM actividad A, lineanegocio LN, proyecto P  " +
                    " WHERE A.idproyecto=P.idproyecto  " +
                    " AND P.idlinea=LN.idlinea  " +
                    " GROUP BY LN.nombrelinea; ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while(rs.next()) {
                DashboardHoraXLinea resultado = new DashboardHoraXLinea();
                resultado.setSumaHoras(rs.getDouble("sum"));
                resultado.setNombreLinea(rs.getString("nombreLinea"));
                resultados.add(resultado);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultados;
    }
    //Testeado
    public DashboardHoraXLinea horaPorLineaEspecifica(LineaNegocio lineaNegocio){
        String SQL=" SELECT LN.nombrelinea, SUM(A.tiemporequerido) " +
                " FROM actividad A, lineanegocio LN, proyecto P " +
                " WHERE A.idproyecto=P.idproyecto " +
                " AND P.idlinea=LN.idlinea " +
                " AND LN.idlinea= "  + lineaNegocio.getIdLinea() +
                " GROUP BY LN.nombrelinea ";
        DashboardHoraXLinea resultado =  new DashboardHoraXLinea();;
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                resultado.setNombreLinea(rs.getString("nombrelinea"));
                resultado.setSumaHoras(rs.getDouble("sum"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultado;
    }

    public List<HorasEmpleadoXProyecto> obtenerEmpleadoXProyecto(Proyecto proyecto) {
        List<HorasEmpleadoXProyecto> horaep = new ArrayList<HorasEmpleadoXProyecto>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
<<<<<<< HEAD
            String sentenciaSQL = " SELECT E.nombrecompleto AS Empleado, "+
                    " P.nombreproyecto AS Proyecto, SUM(A.tiemporequerido) "+
                    " FROM actividad A, empleado E, proyecto P "+
                    " WHERE E.dni=A.dni_ejecutor AND A.idproyecto=P.idproyecto AND P.nombreproyecto=? " +
                    " GROUP BY e.nombrecompleto, p.nombreproyecto ";
=======
            String sentenciaSQL = " SELECT E.nombrecompleto AS Empleado,  P.nombreproyecto AS Proyecto, SUM(A.tiemporequerido) " +
                                  "FROM actividad A, empleado E, proyecto P WHERE E.dni=A.dni_ejecutor AND A.idproyecto=P.idproyecto " +
                                  "AND P.idproyecto = ? GROUP BY E.nombrecompleto, P.nombreproyecto ";
>>>>>>> 9156323a5029d474435cac1e3c1e859cd5e605cf
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, proyecto.getIdProyecto());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HorasEmpleadoXProyecto actividad = new HorasEmpleadoXProyecto();
<<<<<<< HEAD
                actividad.setEmpleado(rs.getString("empleado"));
                actividad.setTiempoRequerido(rs.getInt("sum"));
                actividad.setProyecto(rs.getString("proyecto"));
=======
                actividad.setEmpleado(rs.getString("Empleado"));
                actividad.setTiempoRequerido(rs.getDouble("sum"));
                actividad.setProyecto(rs.getString("Proyecto"));
>>>>>>> 9156323a5029d474435cac1e3c1e859cd5e605cf

                horaep.add(actividad);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return horaep;
    }

    public List<PlanificadoVsRegistrado> obtenerPlanificadoVsRegistrado() {
        List<PlanificadoVsRegistrado> versus = new ArrayList<PlanificadoVsRegistrado>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT p.idproyecto, a.fechaingresada, a.fechaplanificada, "+
                    " AGE(fechaingresada, fechaplanificada) AS diferencia, SUM(A.tiemporequerido) AS tiempo, "+
                    " SUM(A.tiempoplanificado) AS tiempoplanificado FROM Empleado e, Actividad a, Proyecto p "+
                    " WHERE a.planificado = 1 AND e.dni=a.dni_ejecutor AND p.idproyecto=a.idproyecto "+
                    " group by p.idproyecto,a.fechaingresada, a.fechaplanificada ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                PlanificadoVsRegistrado data = new PlanificadoVsRegistrado();
                data.setProyecto(rs.getString("idproyecto"));
                data.setFechaReportada(rs.getString("fechaingresada"));
                data.setFechaPlanificada(rs.getString("fechaplanificada"));
                data.setDiferencia(rs.getString("diferencia"));
                data.setTiempoRegistrado(rs.getString("tiempo"));
                data.setTiempoPlanificado(rs.getString("tiempoplanificado"));

                versus.add(data);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return versus;
    }

    public List<PlanificadoVsRegistrado> obtenerProyectoPlanificadoVsRegistrado(String nombreProyecto) {
        List<PlanificadoVsRegistrado> proyecto = new ArrayList<PlanificadoVsRegistrado>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = " SELECT p.idproyecto, a.fechaingresada, a.fechaplanificada, "+
                    " AGE(fechaingresada,fechaplanificada) AS diferencia, "+
                    " SUM(A.tiemporequerido) AS tiempo, SUM(A.tiempoplanificado) AS tiempoplanificado "+
                    " FROM Empleado e, Actividad a, Proyecto p WHERE a.planificado = 1 AND "+
                    " e.dni=a.dni_ejecutor AND p.idproyecto=a.idproyecto AND p.nombreproyecto=? "+
                    " group by p.idproyecto, a.fechaingresada, a.fechaplanificada ";
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, nombreProyecto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlanificadoVsRegistrado actividad = new PlanificadoVsRegistrado();
                actividad.setProyecto(rs.getString("idproyecto"));
                actividad.setFechaReportada(rs.getString("fechaingresada"));
                actividad.setFechaPlanificada(rs.getString("fechaplanificada"));
                actividad.setDiferencia(rs.getString("diferencia"));
                actividad.setTiempoRegistrado(rs.getString("tiempo"));
                actividad.setTiempoPlanificado(rs.getString("tiempoplanificado"));

                proyecto.add(actividad);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return proyecto;
    }
    public List<Proyecto> obtenerProyectoFull(){
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SentenciaSQL= " SELECT estado, nombreproyecto, idproyecto, fechainicio, fechafin, ruc, idlinea " +
                    " FROM proyecto ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SentenciaSQL);
            while (rs.next()) {
                Proyecto proyecto= new Proyecto();
                proyecto.setIdProyecto(rs.getInt("idproyecto"));
                proyecto.setRUC(rs.getString("ruc"));
                proyecto.setNombreProyecto(rs.getString("nombreproyecto"));
                proyecto.setIdLinea(rs.getInt("idlinea"));
                proyecto.setEstado(rs.getString("estado"));
                proyecto.setFechaFin(rs.getString("fechafin"));
                proyecto.setFechaInicio(rs.getString("fechainicio"));
                proyectos.add(proyecto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return proyectos;
    }
}

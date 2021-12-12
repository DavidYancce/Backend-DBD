package com.proyecto_dbd.final_dbd.daoMC;

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
public class FinalDaoImplMC implements FinalDaoMC {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cargo> obtenerCargo() {
        List<Cargo> cargos = new ArrayList<Cargo>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = "SELECT IdCargo, NombreCargo FROM Cargo";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idcargo"));
                cargo.setNombreCargo(rs.getString("nombrecargo"));
                cargos.add(cargo);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cargos;
    }

    public List<Empleado> obtenerEmpleado() {
        List<Empleado> empleados = new ArrayList<Empleado>();
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sentenciaSQL = "SELECT DNI, NombreCompleto FROM Empleado WHERE idCargo=1 ORDER BY NombreCompleto";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setDNI(rs.getString("dni"));
                empleado.setNombreCompleto(rs.getString("nombrecompleto"));
                empleados.add(empleado);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleados;
    }

    public Empleado login(Empleado empleado) {
        String sentenciaSQL = "SELECT * FROM Empleado WHERE CorreoEmpresarial = ? AND contrasenia = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, empleado.getCorreoEmpresarial());
            ps.setString(2, empleado.getContrasenia());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleado = new Empleado();
                empleado.setNombreCompleto(rs.getString("nombrecompleto"));
                empleado.setDNI(rs.getString("dni"));
                empleado.setNombre1(rs.getString("nombre1"));
                empleado.setNombre2(rs.getString("nombre2"));
                empleado.setApellidoPaterno(rs.getString("apellidopaterno"));
                empleado.setApellidoMaterno(rs.getString("apellidomaterno"));
                empleado.setCorreoEmpresarial(rs.getString("correoempresarial"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setGenero(rs.getString("genero"));
                empleado.setSueldo(rs.getDouble("sueldo"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setIdCargo(rs.getInt("idcargo"));
                empleado.setEstado(rs.getString("estado"));
                empleado.setFechaNacimiento(rs.getString("fechanacimiento"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleado;
    }

    public Empleado insertarEmpleado(Empleado empleado) {
        String SQL=" INSERT INTO Empleado(DNI, nombre1, nombre2, apellidoPaterno, apellidoMaterno, nombreCompleto, correoEmpresarial, contrasenia, telefono, genero," +
                " estado, direccion, fechaNacimiento, idCargo, sueldo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, empleado.getDNI());
            ps.setString(2, empleado.getNombre1());
            ps.setString(3, empleado.getNombre2());
            ps.setString(4, empleado.getApellidoPaterno());
            ps.setString(5, empleado.getApellidoMaterno());
            ps.setString(6, empleado.getNombreCompleto());
            ps.setString(7, empleado.getNombre1().toLowerCase()+"."+empleado.getApellidoPaterno().toLowerCase()+"@gestionysistemas.com");
            ps.setString(8, empleado.getDNI());
            ps.setString(9, empleado.getTelefono());
            ps.setString(10, empleado.getGenero());
            ps.setString(11,"Contratado");
            ps.setString(12, empleado.getDireccion());
            ps.setDate(13, Date.valueOf(empleado.getFechaNacimiento()));
            ps.setInt(14, empleado.getIdCargo());
            ps.setDouble(15, empleado.getSueldo());

            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleado;
    }

    public List<FiltrosBP> busquedaProyecto(FiltrosBP filtro) {
        List<FiltrosBP> registros = new ArrayList<FiltrosBP>();
        if(filtro.getNombreJefe()==null){
            filtro.setNombreJefe("");
        }
        if(filtro.getEstadoProyecto()==null){
            filtro.setEstadoProyecto("");
        }
        if(filtro.getLineaNegocio()==null){
            filtro.setLineaNegocio("");
        }
        if(filtro.getRazonSocial()==null){
            filtro.setRazonSocial("");
        }
        if(filtro.getNombreProyecto()==null){
            filtro.setNombreProyecto("");
        }
        if(filtro.getIdProyecto()==null){
            filtro.setIdProyecto(0);
        }
        String sentenciaSQL ="SELECT P.idProyecto, P.NombreProyecto, E.NombreCompleto, C.RazonSocial, LN.NombreLinea, P.Estado, LN.NombreLinea " +
                "FROM Proyecto AS P " +
                "JOIN Cliente AS C " +
                "ON P.Ruc = C.Ruc " +
                "JOIN LineaNegocio AS LN " +
                "ON P.idlinea=LN.idlinea " +
                "JOIN EmpleadoXProyecto AS EP " +
                "ON EP.Rol='Jefe de proyecto' AND P.idproyecto=EP.idproyecto " +
                "JOIN Empleado AS E " +
                "ON E.DNI=EP.DNI " +
                "WHERE " +
                "1= CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(P.NombreProyecto) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(LN.NombreLinea) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN E.NombreCompleto LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(P.Estado) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END " +
                "AND 1 = CASE " +
                "    WHEN ?='' THEN 1 " +
                "    WHEN upper(C.RazonSocial) LIKE ? THEN 1 " +
                "    ELSE 0 " +
                "    END";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, filtro.getNombreProyecto().toUpperCase());
            ps.setString(2, "%"+filtro.getNombreProyecto().toUpperCase()+"%");
            ps.setString(3, filtro.getLineaNegocio());
            ps.setString(4, "%"+filtro.getLineaNegocio()+"%");
            ps.setString(5, filtro.getNombreJefe().toUpperCase());
            ps.setString(6, "%"+filtro.getNombreJefe().toUpperCase()+"%");
            ps.setString(7, filtro.getEstadoProyecto().toUpperCase());
            ps.setString(8, "%"+filtro.getEstadoProyecto().toUpperCase()+"%");
            ps.setString(9, filtro.getRazonSocial().toUpperCase());
            ps.setString(10, "%"+filtro.getRazonSocial().toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FiltrosBP registro = new FiltrosBP();
                registro.setIdProyecto(rs.getInt("idproyecto"));
                registro.setNombreProyecto(rs.getString("nombreproyecto"));
                registro.setNombreJefe(rs.getString("nombrecompleto"));
                registro.setRazonSocial(rs.getString("razonsocial"));
                registro.setLineaNegocio(rs.getString("nombrelinea"));
                registro.setEstadoProyecto(rs.getString("estado"));
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
    public String insertarProyecto(Datos datos) {
        String SQL1=" insert into proyecto(estado,nombreproyecto,idproyecto,fechainicio,fechafin,ruc,idlinea) values (?,?,?,?,?,?,?) ";
        String SQL2=" insert into empleadoxproyecto(dni,idproyecto,rol,descripcion) values (?,?,?,?) ";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(SQL1);
            ps.setString(1, "NI");
            ps.setString(2, datos.getNombreProyecto());
            ps.setInt(3, datos.getIdProyecto());
            ps.setDate(4, Date.valueOf(datos.getFechaInicio()));
            ps.setDate(5, Date.valueOf(datos.getFechaFin()));
            ps.setString(6, datos.getRUC());
            ps.setInt(7,datos.getIdLinea());
            PreparedStatement pst = con.prepareStatement(SQL2);
            pst.setString(1, datos.getDni());
            pst.setInt(2, datos.getIdProyecto());
            pst.setString(3, "Jefe de proyecto");
            pst.setString(4,"Lider "+datos.getNombreProyecto());
            ps.executeUpdate();
            pst.executeUpdate();
            ps.close();
            pst.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Proyecto registrado correctamente";
    }
}

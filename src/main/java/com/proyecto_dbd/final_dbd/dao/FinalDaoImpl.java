package com.proyecto_dbd.final_dbd.dao;

import com.proyecto_dbd.final_dbd.dto.Cliente;
import com.proyecto_dbd.final_dbd.dto.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

}

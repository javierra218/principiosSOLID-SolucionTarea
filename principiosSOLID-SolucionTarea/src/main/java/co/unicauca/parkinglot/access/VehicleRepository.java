/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domain.TypeEnum;
import co.unicauca.parkinglot.domain.Vehicle;
import co.unicauca.parkinglot.domain.service.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rojas
 */
public class VehicleRepository implements IVehicleRepository {
    /*
     * es la conexion con la base de datos sqlite
     */
    private Connection conn;
    
    /*
     * Constructor por defecto
     * este inicializa la base de datos y verifica si esta creada la tabla
     */
    public VehicleRepository() {
        initDatabase();
    }
    /*
     * Guarda un vehiculo en el repositorio
     * @param vehicle recibe el vehiculo
     * @return true o false confirmando el exito de la operacion
     */
    @Override
    public boolean save(Vehicle newVehicle) {
        try {
            if (newVehicle == null || newVehicle.getPlate().isBlank() ||
               newVehicle.getType().name().isBlank()) {
                return false;
            }
            String sql = "INSERT INTO Vehicle ( plate, type ) "
                    + "VALUES ( ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newVehicle.getPlate());
            pstmt.setObject(2, newVehicle.getType());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

 
    /**
     * Lista todos los vehiculos del repositorio
     * @return lista completa de vehiculos del repositorio
     * 
     */

    @Override
    public List<Vehicle> list() {

        List<Vehicle> vehicles = new ArrayList<>();
        try {
            String sql = "SELECT plate,type FROM Vehicle";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.setPlate(rs.getString("plate"));
                newVehicle.setType(TypeEnum.valueOf(rs.getString("type")));

                vehicles.add(newVehicle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;

    }

    /*
     * Inicializa la base de datos o crea la tabla para el trabajo
     */
    private void initDatabase() {

        String sql = "CREATE TABLE IF NOT EXISTS Vehicle (\n"
                + "	plate varchar2 PRIMARY KEY,\n"
                + "	type varchar2\n"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * conecta la base de datos con la api
     */
    public void connect() {

        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /* 
    * Desconecta la base de datos con la api
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

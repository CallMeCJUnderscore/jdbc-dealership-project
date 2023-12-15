package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.*;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)")){
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rows);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("REMOVE FROM inventory WHERE VIN LIKE ?")){
            preparedStatement.setString(1, vin);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rows);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DrugsData;

public class Drug {
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    ObservableList<DrugsData> data = FXCollections.observableArrayList();
    
    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<DrugsData> getDrugsData() throws SQLException {
        query = "SELECT * FROM `medicine`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next()) 
                data.add( new DrugsData.DrugsDataBuiler().setName(rs.getString("Medicine_name")).setCompany(rs.getString("Company"))
                    .setDate(rs.getDate("Received_date").toLocalDate()).setCost(rs.getFloat("Cost")).setQuantity(rs.getInt("MQuantity")).build());

        return data;
    }
   
    private void prepareQuery(String query, DrugsData drugData) throws SQLException {
        ps = getConnection().prepareStatement(query);
        ps.setString(1, drugData.getName());
        ps.setFloat(2, drugData.getCost());
        ps.setString(3, drugData.getCompany());
        ps.setInt(4, drugData.getQuantity());
        ps.setDate(5, Date.valueOf(drugData.getDate()));
        ps.executeUpdate();
    }
    
    public void inserQuery(DrugsData drugData) throws SQLException{
        query = "INSERT INTO `medicine`(`Medicine_name`, `Cost`, `Company`, `MQuantity`, `Received_date`) VALUES (?,?,?,?,?)";
        prepareQuery(query , drugData);
    }
    
    public void updateQuery(DrugsData drugData , String drugName) throws SQLException{
        query = "UPDATE `medicine` SET `Medicine_name`=?, `Cost`=?, `Company`=?, `MQuantity`=?, `Received_date`=? where `Medicine_name`='" + drugName + "'";
        prepareQuery(query , drugData);
    }
    
    public void deleteQuery(DrugsData drugData , String drugName) throws SQLException{
        query = "DELETE FROM `medicine` WHERE `Medicine_name`='" + drugName + "'";
        ps = getConnection().prepareStatement(query);
        ps.executeUpdate();
    }

    public int countDrugs() throws SQLException {
        query = "SELECT COUNT(`Medicine_name`) FROM `medicine`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) 
            count  = rs.getInt("COUNT(`Medicine_name`)");
        
        return count;
    }
}
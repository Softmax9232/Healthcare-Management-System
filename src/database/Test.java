package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.TestData;

public class Test {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    ObservableList<TestData> data = FXCollections.observableArrayList();

    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<TestData> getTestsData() throws SQLException {
        query = "SELECT * FROM `test`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) 
            data.add( new TestData.TestDataBuilder().setName(rs.getString("Test_name")).setCost(rs.getFloat("Tcost")).build());  
        return data;
    }

    private void prepareQuery(String query, TestData test) throws SQLException {
        ps = getConnection().prepareStatement(query);
        ps.setString(1, test.getName());
        ps.setFloat(2, test.getCost());
        ps.executeUpdate();
    }
   
    public void updateTest(TestData test, String testName) throws SQLException {
        query = "UPDATE `test` SET `Test_name`=?,`TCost`=? WHERE `test`.`Test_name`='" + testName + "'";
        prepareQuery(query , test);
    }

    public void insertTest(TestData test) throws SQLException {
        query = "INSERT INTO `test`(`Test_name`, `TCost`) VALUES (?,?)";
        prepareQuery(query, test);
    }

    public void deleteTest(String testName) throws SQLException {
        query = "DELETE FROM `test` WHERE `test`.`Test_name`='" + testName + "'";
        ps = getConnection().prepareStatement(query);
        ps.executeUpdate();
    }
    
    public int countTests() throws SQLException {
        query = "SELECT COUNT(`Test_name`)FROM  `test`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        int count = 0;
        
        while (rs.next()) 
            count = rs.getInt("COUNT(`Test_name`)");
        
        return count;
    }
}

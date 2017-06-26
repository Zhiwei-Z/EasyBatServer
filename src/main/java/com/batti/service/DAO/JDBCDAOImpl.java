package com.batti.service.DAO;
import java.sql.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.batti.service.model.Order;

/**
 * Created by yonzhang on 6/17/17.
 */
public class JDBCDAOImpl implements BattiDAO {
    Logger LOG = LoggerFactory.getLogger(JDBCDAOImpl.class);
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL_FORMAT = "jdbc:mysql://%s:%d/%s";
    String dbUrl;
    //  Database credentials
    String dbUser = "root";
    String dbPassword = "root";
    public JDBCDAOImpl(){
        String dbName = "batti";
        dbUrl = String.format(DB_URL_FORMAT, "localhost", 3306, dbName);
        dbUser = "root";
        dbPassword = "root";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception ex) {
            LOG.error("fail loading JDBC driver", ex);
        }
    }

    public void createOrder(Order order) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "INSERT INTO batti_order_record (order_id, customer_id, pick_status) " +
                    "VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getOrder_id());
            stmt.setString(2, order.getCustomer_id());
            stmt.setInt(3, order.getPick_status());
            stmt.execute();
        } catch (Exception e) {
            LOG.error("fail creating order", e);
            throw e;
        } finally {
            try {
                if (stmt != null)
                    conn.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                LOG.error("fail", se);
            }
        }//end try
        System.out.println("successfully inserted order " + order);
    }

//    public static void main(String[] args){
//        JDBCDAOImpl j = new JDBCDAOImpl();
//        Order o = new Order("42756RobertsAve");
//        try{
//            j.createOrder(o);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }




    public ArrayList<String> retrieveCustomerId() throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ArrayList<String> addr = new ArrayList<String>();
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT customer_id, status FROM customer_info";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                String customer_id = rs.getString("customer_id");
                int status = rs.getInt("status");
                //Retrieve by column name
               if(status == 1){
                   addr.add(customer_id);
               }
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            throw se;
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            throw e;
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return addr;
    }

    public void changeCustomerStatus(String customerId) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "UPDATE customer_info SET status = ? WHERE customer_id = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, 1);
            stmt.setString(2, customerId);
            stmt.execute();
//            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set

//            rs.close();
        }catch (Exception e) {
            LOG.error("fail changing status", e);
            throw e;
        } finally {
            try {
                if (stmt != null)
                    conn.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                LOG.error("Fail", se);
            }
        }//end try
        System.out.println("Goodbye!");
        System.out.println("successfully inserted order " + customerId);
    }
}

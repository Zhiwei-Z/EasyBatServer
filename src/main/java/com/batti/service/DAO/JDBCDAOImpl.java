package com.batti.service.DAO;
import java.sql.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


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

    //*************************************************************************************//
    //********************************CUSTOMER METHODS*************************************//
    //*************************************************************************************//

    /**
     * record an order in th database
     * @param order input order object
     * @throws Exception
     */
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

    /**
     * if the associated customer has already placed order, return false
     * else return true
     */
    public boolean assessRequest(String customer_id) {
        try {
            ArrayList<String> addrs = retrieveOrderedCustomerId();
            return !addrs.contains(customer_id);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Used in sign up
     * if the combined address has never been registered return true
     * else return false
     */
    public boolean assessCustomerAddress(String combinedAddress) {
        try {
            ArrayList<String> chk = checkCustomerAddress(combinedAddress);
            return !(chk.size() > 0);
        } catch (Exception ex) {
            System.out.println("False due to exception in assessCustomerAddress.");
            return false;
        }
    }

    /**
     * nickname of every customer has to be unique
     * return true if the nickname has never been used
     * return false otherwise
     */
    public boolean assessCustomerNickname(String nickName) {
        try {
            ArrayList<String> nkms = retrieveCustomerNicknames();
            return !nkms.contains(nickName);
        } catch (Exception ex) {
            System.out.println("False due to exception in assessCustomerNickName.");
            return false;
        }
    }


    /**
     * retrieve the customerIDs that have already ordered
     * @return and arraylist of customerID
     */
    public ArrayList<String> retrieveOrderedCustomerId() throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ArrayList<String> ids = new ArrayList<String>();
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
                   ids.add(customer_id);
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
        return ids;
    }


    /**
     * return an arraylist of nicknames that have already been registered
     */
    public ArrayList<String> retrieveCustomerNicknames() throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        ArrayList<String> nknm = new ArrayList<String>();
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT nickname FROM customer_info";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                String nickname = rs.getString("nickname");
                //Retrieve by column name
                nknm.add(nickname);
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
        return nknm;
    }

    /**
     * finds the customerID associated with such nickName
     * return the customerID
     */
    public String signInAndReturnCustomerID(String nickName) throws Exception{
        //assuming the nickname is valid
        //returning the customerId
        Connection conn = null;
        PreparedStatement stmt = null;
        String customerID ;
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT customer_id FROM customer_info WHERE nickname=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nickName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customerID = rs.getString("customer_id");
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
        }
        return customerID;
    }

    /**
     * Assuming the address and nicknames are valid
     * Insert the information into the database
     */
    public void customerSignUp(String customerId,
                               String streetNumber,
                               String unitNumber,
                               String streetName,
                               String streetType,
                               String city,
                               String state,
                               String zipCode,
                               String nickname) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        String address = combineAddress(streetNumber,
                unitNumber,
                streetName,
                streetType,
                city,
                state,
                zipCode);
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "INSERT INTO customer_info (customer_id, nickname,street_number, street_name, street_type, unit_number, city, state, zip_code, combined_address, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setString(2, nickname);
            stmt.setString(3, streetNumber);
            stmt.setString(4, streetName);
            stmt.setString(5, streetType);
            stmt.setString(6, unitNumber);
            stmt.setString(7, city);
            stmt.setString(8, state);
            stmt.setString(9, zipCode);
            stmt.setString(10, address);
            stmt.setInt(11, 0);
            stmt.execute();
        } catch (Exception e) {
            LOG.error("fail signing up", e);
            throw e;
        } finally {
            try {
                if (stmt != null)
                    conn.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                LOG.error("fail for some reason during signing ups", se);
            }
        }//end try
        System.out.println("successfully sign up user " + nickname );
    }

    /**
     * Change the customer order status
     */
    public void changeCustomerStatus(String customerId, int status) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "UPDATE customer_info SET status = ? WHERE customer_id = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, status);
            stmt.setString(2, customerId);
            stmt.execute();
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

    /**
     * return the CUSTOMER_IDs of the address if the address has appeared in an arraylist
     * the arraylist should be at most length of 1
     */
    public ArrayList<String> checkCustomerAddress(String combinedAddress) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;
        ArrayList<String> chk = new ArrayList<String>();
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String sql = "SELECT customer_id FROM customer_info WHERE combined_address = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, combinedAddress);

            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                String customer_id = rs.getString("customer_id");
                //Retrieve by column name
                chk.add(customer_id);
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
        return chk;
    }


    //*************************************************************************************//
    //********************************VOLUNTEER METHODS************************************//
    //*************************************************************************************//

    /**
     * return the volunteerIDs of the associated address in an arraylist
     * The length should be ≤ 1
     */
    public ArrayList<String> checkVolunteerAddress(String combinedAddress) throws Exception{

        //return the CUSTOMER_IDs of the address if the address has appeared
        Connection conn = null;
        PreparedStatement stmt = null;
        ArrayList<String> chk = new ArrayList<String>();
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String sql = "SELECT vol_id FROM volunteer_info WHERE combined_address = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, combinedAddress);

            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            while(rs.next()){
                String customer_id = rs.getString("vol_id");
                //Retrieve by column name
                chk.add(customer_id);
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
        return chk;
    }


    /**
     * Assume the address, email, and password are valid
     * Record them into the volunteer_info table
     */
    public void volunteerSignUp(String volId,
                                String streetNumber,
                                String unitNumber,
                                String streetName,
                                String streetType,
                                String city,
                                String state,
                                String zipCode,
                                String username,
                                String email,
                                String password,
                                int idealCoverRange) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        String combinedAddress = combineAddress(streetNumber,
                unitNumber,
                streetName,
                streetType,
                city,
                state,
                zipCode);
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "INSERT INTO volunteer_info (vol_id, street_number, unit_number, street_name, street_type, " +
                    "city, state, zip_code, status, combined_address, username, email, password, ideal_cover_range) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, volId);
            stmt.setString(2, streetNumber);
            stmt.setString(3, unitNumber);
            stmt.setString(4, streetName);
            stmt.setString(5, streetType);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setString(8, zipCode);
            stmt.setInt(9, 0);
            stmt.setString(10, combinedAddress);
            stmt.setString(11, username);
            stmt.setString(12, email);
            stmt.setString(13, password);
            stmt.setInt(14, idealCoverRange);
            stmt.execute();
        } catch (Exception e) {
            LOG.error("fail signing the volunteer up", e);
            throw e;
        } finally {
            try {
                if (stmt != null)
                    conn.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                LOG.error("fail for some reason of connection", se);
            }
        }//end try
        System.out.println("successfully sign up volunteer " + username );

    }


    /**
     * Assume there is only one password associated with the email
     */
    public String returnPassword(String email) throws Exception{
        if(email == null){
            return null;
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        String password;
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String sql = "SELECT password FROM volunteer_info WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            //STEP 5: Extract data from result set
            rs.next();
            password = rs.getString("password");
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
        return password;
    }

    /**
     * return a hashMap of all volunteers
     * key: emails; value: passwords
     */
    public HashMap<String, String> retrieveVolunteerEmailsPasswords() throws Exception{
        Connection conn = null;
        PreparedStatement stmt1 = null;
        ArrayList<String> emails = new ArrayList<String>();
        HashMap<String, String> userPairs = new HashMap<String, String>();
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql1 = "SELECT email FROM volunteer_info";

            stmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = stmt1.executeQuery();
            //STEP 5: Extract data from result set
            while(rs1.next()){
                String username = rs1.getString("email");
                emails.add(username);
            }
            for(String email : emails){
                String password = returnPassword(email);
                userPairs.put(email, password);
            }
            rs1.close();
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
                if(stmt1!=null)
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
        return userPairs;
    }

    /**
     * input: email and its corresponding password
     * return the volunteerID associated with it
     */
    public String signInAndReturnVolunteerID(String email) throws Exception{
        Connection conn = null;
        PreparedStatement stmt = null;
        String volunteerID ;
        try{
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT vol_id FROM volunteer_info WHERE email=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            volunteerID = rs.getString("vol_id");
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
        }
        return volunteerID;
    }

    /**
     * if the address has been registered, return false
     * return true otherwise
     */
    public boolean assessVolunteerAddress(String combinedAddress) {
        try {
            ArrayList<String> chk = checkVolunteerAddress(combinedAddress);
            return ! (chk.size() > 0);
        } catch (Exception ex) {
            System.out.println("False due to exception in assessVolunteerAddress.");
            return false;
        }
    }


    //*************************************************************************************//
    //********************************UTILITY METHODS************************************//
    //*************************************************************************************//

    /**
     * A utility method to combine the address pieces into one single string
     */
    public String combineAddress(String streetNumber,
                                 String unitNumber,
                                 String streetName,
                                 String streetType,
                                 String city,
                                 String state,
                                 String zipCode){
        return streetNumber + " " +
                unitNumber + " " +
                streetName + " " +
                streetType + ", " +
                city + ", " +
                state + ", " +
                zipCode;

    }

    public static void main(String[] args) throws Exception{
        JDBCDAOImpl j = new JDBCDAOImpl();
        j.volunteerSignUp("randomID", "1234567", "ub", "la", "lama",
                "fr", "ca", "112", "zhika", "ggg@", "ggg", 1 );
    }
}

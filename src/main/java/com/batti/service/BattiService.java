package com.batti.service;

import com.batti.service.DAO.JDBCDAOImpl;
import com.batti.service.model.Order;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by yonzhang on 6/15/17.
 */

@Path("/")
public class BattiService {
    private static final Logger LOG = LoggerFactory.getLogger(BattiService.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final ObjectIdGenerators.UUIDGenerator generator = new ObjectIdGenerators.UUIDGenerator();

    public BattiService() {

    }

    //*************************************************************************************//
    //********************************CUSTOMER SERVICES************************************//
    //*************************************************************************************//


    /**
     * @param customer_id the unique id of ever customer
     * @return a orderStatus indicating if the order is success
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/placeOrder")
    public OrderStatus placeOrder(@QueryParam("customer_id") String customer_id) {
        JDBCDAOImpl j = new JDBCDAOImpl();
        OrderStatus ost = new OrderStatus();
        try {
            UUID orderId = generator.generateId(secureRandom);

            Order newOrder = new Order(orderId.toString(), customer_id, 0);
            if (j.assessRequest(customer_id)) {
                try {
                    j.createOrder(newOrder);
                    j.changeCustomerStatus(customer_id, 1);
                    ost.setStatus("success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("already ordered");
                ost.setStatus("fail");
            }
            return ost;
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return new OrderStatus();
    }


    /**
     * @return an arraylist of customerIds which has a active order record
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/orderList")
    public ArrayList<String> listOrders() {

        JDBCDAOImpl j = new JDBCDAOImpl();
        try {
            return j.retrieveOrderedCustomerId();
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here" + e.getMessage());
            return msg;
        }

    }

    /**
     *
     * @param nickname the input of nickname, which is required to be unique
     * @return a customerSignInStatus object, indicating if the signIn is success
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customerSignIn")
    public CustomerSignInStatus customerSignIn(@QueryParam("nickname") String nickname){
        JDBCDAOImpl j = new JDBCDAOImpl();
        CustomerSignInStatus sis = new CustomerSignInStatus();
        try{
            if(j.retrieveCustomerNicknames().contains(nickname)){
                //there is account associated with this account
                sis.setStatus("success");
                String cusId = j.signInAndReturnCustomerID(nickname);
                //customerID is then stored into the JSON object
                sis.setCustomerID(cusId);
                // store the customer status in the JSON result
                ArrayList<CustomerInfoEntry> a = j.getCustomerEntries(new String[]{"WHERE customer_id=" + cusId});
                if(a.size() > 1) {
                    throw new Exception("more than one customer with the customer_id: " + cusId + ", exists");
                }
                sis.setCustomerStatus(a.get(0).getStatus());
                System.out.println("sign in success with nickname: " + nickname + " and customerID: " + cusId);
            }else{
                sis.setStatus("No account associated.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in customerSignUp" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return sis;

    }

    /**
     * Parameters: the address of the customer, in separate pieces; as well as the nickname
     * @return a CustomerSignUpStatus indicating if the signUp is success
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customerSignUp")
    public CustomerSignUpStatus customerSignUp(@QueryParam("street_number") String streetNumber,
                                               @QueryParam("unit_number") String unitNumber,
                                               @QueryParam("street_name") String streetName,
                                               @QueryParam("street_type") String streetType,
                                               @QueryParam("city") String city,
                                               @QueryParam("state") String state,
                                               @QueryParam("zip_code") String zipCode,
                                               @QueryParam("nickname") String nickname) {
        JDBCDAOImpl j = new JDBCDAOImpl();
        CustomerSignUpStatus sus = new CustomerSignUpStatus();
        try {
            //fist check if address is duplicated
            String combinedAddress = j.combineAddress(streetNumber, unitNumber, streetName, streetType, city, state, zipCode);
            if (j.assessCustomerAddress(combinedAddress)) {
                //check if nickname is duplicated
                if (j.assessCustomerNickname(nickname)) {
                    try {
                        UUID customerId = generator.generateId(secureRandom);
                        j.customerSignUp(customerId.toString(), streetNumber, unitNumber, streetName, streetType, city, state, zipCode, nickname);
                        sus.setStatus("success");
                        sus.setSuccessfulCustomerID(customerId.toString());
                    } catch (Exception e) {
                        System.out.println("exception thrown after checking address and nickname");
                        e.printStackTrace();
                        sus.setStatus("Our system error");
                        sus.setSuccessfulCustomerID("");
                    }
                } else {
                    System.out.println("Nickname has been used.");
                    sus.setStatus("Nickname has been used.");
                    sus.setSuccessfulCustomerID("");
                }
            } else {
                System.out.println("Address has already been registered.");
                sus.setStatus("Address has already been registered.");
                sus.setSuccessfulCustomerID("");
            }

            return sus;
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in customerSignUp" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return new CustomerSignUpStatus();


    }

    /**
     * @return a a list of customer nicknames
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nicknameList")
    public ArrayList<String> listNicknames() {

        JDBCDAOImpl j = new JDBCDAOImpl();
        try {
            return j.retrieveCustomerNicknames();
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception in listNicknames" + e.getMessage());
            return msg;
        }

    }




    //**************************************************************************************//
    //********************************VOLUNTEER SERVICES************************************//
    //**************************************************************************************//


    /**
     * return a volunteerSignUpStatus object indicating if successfully signed up
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/volunteerSignUp")
    public VolunteerSignUpStatus volunteerSignUp(@QueryParam("street_number") String streetNumber,
                                       @QueryParam("unit_number") String unitNumber,
                                       @QueryParam( "street_name") String streetName,
                                       @QueryParam("street_type") String streetType,
                                       @QueryParam("city") String city,
                                       @QueryParam("state") String state,
                                       @QueryParam("zip_code") String zipCode,
                                       @QueryParam("username") String username,
                                       @QueryParam("email") String email,
                                       @QueryParam("password") String password,
                                       @QueryParam("idealCoverRange") int coverRange){
        JDBCDAOImpl j = new JDBCDAOImpl();
        VolunteerSignUpStatus vss = new VolunteerSignUpStatus();
        try{
            String combinedAddress = j.combineAddress(streetNumber, unitNumber, streetName,
                    streetType, city, state, zipCode);

            // Check if the address is already registered
            if(j.assessVolunteerAddress(combinedAddress)){

                // Check if the email has already been registered
                HashMap<String, String> emailPasswords = j.retrieveVolunteerEmailsPasswords();
                if(!emailPasswords.containsKey(email)){

                    try{
                        UUID volunteerID = generator.generateId(secureRandom);
                        j.volunteerSignUp(volunteerID.toString(),
                                streetNumber, unitNumber, streetName, streetType, city, state, zipCode, username,
                                email, password, coverRange);
                        vss.setStatus("success");
                        vss.setSuccessfulVolunteerID(volunteerID.toString());
                    } catch (Exception e){
                        LOG.info("exception thrown after checking address and nickname");
                        e.printStackTrace();
                        vss.setStatus("Our system error");
                        vss.setSuccessfulVolunteerID("");
                    }
                }else{
                    LOG.info("Email has already been registered.");
                    vss.setStatus("Email has already been registered.");
                }
            }else{
                LOG.info("Address has already been registered.");
                vss.setStatus("Address has already been registered.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in volunteerSignUp" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return vss;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/volunteerSignIn")
    public VolunteerSignInStatus volunteerSignIn(@QueryParam("email") String email, @QueryParam("password") String password){
        JDBCDAOImpl j = new JDBCDAOImpl();
        VolunteerSignInStatus vss = new VolunteerSignInStatus();
        try{
            if(j.retrieveVolunteerEmailsPasswords().keySet().contains(email)){
                //there is account associated with this account
                if(j.retrieveVolunteerEmailsPasswords().get(email).equals(password)) {
                    // if the password is correct
                    vss.setStatus("success");
                    String volID = j.signInAndReturnVolunteerID(email);
                    //volunteerID is then stored into the JSON object
                    vss.setVolunteerID(volID);
                    System.out.println("sign in success with email: " + email + " and customerID: " + volID);
                }else{
                    vss.setStatus("Email valid, Password incorrect");
                    LOG.info("Email valid, Password incorrect");
                }
            }else{
                vss.setStatus("No account associated.");
                LOG.info("No account associated");
            }
        }catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in volunteerSignIn" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return vss;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/volunteerTaskList")
    public VolunteerTaskListStatus volunteerTaskList(@QueryParam("volunteerID") String volunteerID) {
        JDBCDAOImpl j = new JDBCDAOImpl();
        VolunteerTaskListStatus v = new VolunteerTaskListStatus();
        try{
            ArrayList<String> address = j.volunteerTasks(volunteerID);
            if(!address.isEmpty()) {
                v.setStatus("occupied");
                v.setTasks(address);
            }else{
                v.setStatus("empty");
            }
        }catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in customerSignUp" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return v;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/volunteerPickJob")
    public VolunteerResponsibilityStatus volunteerPickJob(@QueryParam("volunteerID") String volunteerID, @QueryParam("address") String address) {
        JDBCDAOImpl j = new JDBCDAOImpl();
        VolunteerResponsibilityStatus v = new VolunteerResponsibilityStatus();
        try{

        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in customerSignUp" + e.getMessage());
            for (String m : msg) {
                System.out.println(m);
            }
        }
        return v;
    }

}

package com.batti.service;

import com.batti.service.DAO.BattiDAO;
import com.batti.service.DAO.JDBCDAOImpl;
import com.batti.service.model.Order;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by yonzhang on 6/15/17.
 */
@Path("/")
public class BattiService {
    private static final Logger LOG = LoggerFactory.getLogger(BattiService.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final ObjectIdGenerators.UUIDGenerator generator = new ObjectIdGenerators.UUIDGenerator();

    public BattiService(){

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/placeOrder")

    public OrderStatus placeOrder(@QueryParam("customer_id") String customer_id){
        JDBCDAOImpl j = new JDBCDAOImpl();
        OrderStatus ost = new OrderStatus();
        try{
            UUID orderId = generator.generateId(secureRandom);

            Order newOrder = new Order(orderId.toString(), customer_id, 0);
            if(assessRequest(j, customer_id)){
                try{
                    j.createOrder(newOrder);
                    j.changeCustomerStatus(customer_id);
                    ost.setStatus("success");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("already ordered");
                ost.setStatus("fail");
            }
            return ost;
        }catch(Exception e){
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here" + e.getMessage());
            for(String m: msg){
                System.out.println(m);
            }
        }
        return new OrderStatus();
    }

    public boolean assessRequest(JDBCDAOImpl j, String customer_id){
        try {
            ArrayList<String> addrs = j.retrieveCustomerId();
            if(addrs.contains(customer_id)){
                return false;
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/orderList")
    public ArrayList<String> listOrders(){

        JDBCDAOImpl j = new JDBCDAOImpl();
        try{
            return j.retrieveCustomerId();
        }catch(Exception e){
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here" + e.getMessage());
            return msg;
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/SignUp")
    public SignUpStatus customerSignUp(@QueryParam("street_number") String streetNumber,
                              @QueryParam("street_name") String streetName,
                              @QueryParam("street_type") String streetType,
                              @QueryParam("unit_number") String unitNumber,
                              @QueryParam("city") String city,
                              @QueryParam("state") String state,
                              @QueryParam("zip_code") String zipCode,
                              @QueryParam("nickname")String nickname){
        JDBCDAOImpl j = new JDBCDAOImpl();
        SignUpStatus sus = new SignUpStatus();
        try{
            //fist check if address is duplicated
            if(assessAddress(j, streetNumber, streetName, streetType, unitNumber, city, state, zipCode)){
                //check if nickname is duplicated
                if(assessNickname(j, nickname)){
                    try{
                        UUID customerId = generator.generateId(secureRandom);
                        j.signUp(customerId.toString(), streetNumber, unitNumber,streetName, streetType, city, state, zipCode, nickname);
                        sus.setStatus("success");
                    }catch (Exception e){
                        System.out.println("exception thrown after checking address and nickname");
                        e.printStackTrace();
                        sus.setStatus("fail");
                    }
                }else{
                    System.out.println("Nickname has been used.");
                    sus.setStatus("fail");
                }
            }else{
                System.out.println("Address has already been registered.");
                sus.setStatus("fail");
            }

            return sus;
        }catch(Exception e){
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception thrown here in customerSignUp" + e.getMessage());
            for(String m: msg){
                System.out.println(m);
            }
        }
        return new SignUpStatus();


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nicknameList")
    public ArrayList<String> listNicknames(){

        JDBCDAOImpl j = new JDBCDAOImpl();
        try{
            return j.retrieveNickNames();
        }catch(Exception e){
            e.printStackTrace();
            ArrayList<String> msg = new ArrayList<String>();
            msg.add("Exception in listNicknames" + e.getMessage());
            return msg;
        }

    }

    public boolean assessNickname(JDBCDAOImpl j, String nickName){
        try {
            ArrayList<String> nkms = j.retrieveNickNames();
            if(nkms.contains(nickName)){
                return false;
            }
            return true;
        }catch(Exception ex){
            System.out.println("False due to exception in assessNickName.");
            return false;
        }
    }

    public boolean assessAddress(JDBCDAOImpl j, String streetNumber,
                                  String streetName,
                                  String streetType,
                                  String unitNumber,
                                  String city,
                                  String state,
                                  String zipCode){
        try {
            ArrayList<String> chk = j.checkAddress(streetNumber, streetName, streetType, unitNumber, city, state, zipCode);
            if(chk.size() > 0){
                return false;
            }
            return true;
        }catch(Exception ex){
            System.out.println("False due to exception in assessAddress.");
            return false;
        }
    }

}

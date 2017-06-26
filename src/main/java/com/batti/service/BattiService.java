package com.batti.service;

import com.batti.service.DAO.BattiDAO;
import com.batti.service.DAO.JDBCDAOImpl;
import com.batti.service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yonzhang on 6/15/17.
 */
@Path("/")
public class BattiService {
    private static final Logger LOG = LoggerFactory.getLogger(BattiService.class);

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
            Order newOrder = new Order(idGenerator(), customer_id, 0);
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

    public static String idGenerator(){
        int i = (int)(Math.random()*10000000);
        return i + "hello";
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
}

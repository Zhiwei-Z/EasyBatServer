package com.batti.service;

import com.batti.service.DAO.JDBCDAOImpl;
import com.batti.service.model.CustomerInfoEntry;
import com.batti.service.model.CustomerSignUpStatus;
import com.batti.service.model.VolunteerSignInStatus;
import com.batti.service.model.VolunteerSignUpStatus;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by yonzhang on 8/13/17.
 */
public class BattiTest {


    @Test
    public void testVolunteerSignIn(){
        BattiService b = new BattiService();
        VolunteerSignInStatus v = b.volunteerSignIn("zhiweizhang2012@gmail.com", "hahaha");
        assertEquals(v.getStatus(), "success");
    }

    @Test
    public void testVolunteerSignUp(){
        BattiService b = new BattiService();
        VolunteerSignUpStatus v = b.volunteerSignUp("42756", "", "Roberts",
                "Avenue", "Fremont", "CA", "94538", "zheriman",
                "2306827699@qq.com", "hitmeup", 100);
        assertEquals("success", v.getStatus());
    }

    @Test
    public void testCustomerSignUp() {
        BattiService b = new BattiService();
        CustomerSignUpStatus c = b.customerSignUp("3929", "0", "Wild Indigo", "Common", "Fremont", "CA", "94538", "Conan");
    }

    @Test
    public void testCustomerEntry() throws Exception{
        JDBCDAOImpl j = new JDBCDAOImpl();
        ArrayList<CustomerInfoEntry> a = j.getCustomerEntries();
        assertTrue(a.size() == 4);
        for(CustomerInfoEntry c : a){
            System.out.println(c);
        }
    }


    @Test
    public void testCustomerOrder() {
        BattiService b = new BattiService();
        b.placeOrder("29402280-0e05-483c-9a38-7e6d1d5d0165");
        b.placeOrder("5258f814-4c73-45a8-b580-af90b880317f");
//        b.placeOrder("692562e4-65fb-4be1-997a-d907f3770583");
//        b.placeOrder("d332a9f4-9245-4c7f-a170-dbd4419f90c4");
//        b.placeOrder("d5222413-fb9f-4594-9ba7-d227ce4226f4");
    }

    @Test
    public void testNearestUnoccupied() {
        BattiService b = new BattiService();
        System.out.println(b.unoccupiedAddressList("08ec1616-0ad8-46bf-a85e-f5a1cc229dde").getAddresses());
    }
    @Test
    public void testVolunteerPickJob() {
        BattiService b = new BattiService();
        b.volunteerPickJob("08ec1616-0ad8-46bf-a85e-f5a1cc229dde", "4792 Valpey Park Avenue, Fremont, CA, 94538");
        b.volunteerPickJob("08ec1616-0ad8-46bf-a85e-f5a1cc229dde", "3929 Wild Indigo Common, Fremont, CA, 94538");
    }

    @Test
    public void testVolunteerTaskList(){
        BattiService b = new BattiService();
        System.out.println(b.volunteerTaskList("08ec1616-0ad8-46bf-a85e-f5a1cc229dde").getTasks());
    }
    @Test
    public void testVolunteerFinishesJob() {
        BattiService b = new BattiService();
        b.volunteerFinishesJob("08ec1616-0ad8-46bf-a85e-f5a1cc229dde", "4792 Valpey Park Avenue, Fremont, CA, 94538");
//        b.volunteerFinishesJob("08ec1616-0ad8-46bf-a85e-f5a1cc229dde", "3929 Wild Indigo Common, Fremont, CA, 94538");
    }

    @Test
    public void testDouble() {
        System.out.println(Math.round(4.123 * 100.0)/100.0);
    }
}

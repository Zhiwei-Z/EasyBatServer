package com.batti.service;

import com.batti.service.DAO.JDBCDAOImpl;
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
        VolunteerSignInStatus v = b.volunteerSignIn("zhiweizhang2012@gmail.com", "Goodluck99");
        assertEquals("7d6a1889-d866-4c8f-9e6b-43df8c130734", v.getVolunteerID());
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
    public void testCustomerEntry() throws Exception{
        JDBCDAOImpl j = new JDBCDAOImpl();
        ArrayList<CustomerInfoEntry> a = j.getCustomerEntries();
        assertTrue(a.size() == 4);
        for(CustomerInfoEntry c : a){
            System.out.println(c);
        }
    }
}

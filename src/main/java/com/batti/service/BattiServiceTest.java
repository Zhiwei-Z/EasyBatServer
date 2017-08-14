package com.batti.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yonzhang on 8/13/17.
 */
public class BattiServiceTest {
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
}

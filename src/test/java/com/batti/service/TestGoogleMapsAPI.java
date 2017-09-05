package com.batti.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by yonzhang on 8/23/17.
 */
public class TestGoogleMapsAPI {
    @Ignore
    public void test() throws Exception{
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyB2r_Yo8URXrpiKuciAU__n06Yp-iJKSXM")
                .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));
    }

    @Ignore
    public void test2() throws Exception{
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyB2r_Yo8URXrpiKuciAU__n06Yp-iJKSXM")
                .build();
        DistanceMatrix dm = DistanceMatrixApi.newRequest(context)
                .origins("42756 Roberts Avenue,Fremont, CA")
                .destinations("42800 Blacow Road,Fremont,CA")
                .await();
        System.out.println(dm.rows[0].elements[0].status);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(dm));
    }
}

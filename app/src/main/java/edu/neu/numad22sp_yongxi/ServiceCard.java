package edu.neu.numad22sp_yongxi;

import android.graphics.Bitmap;

public class ServiceCard {

    private final Bitmap serviceImg;
    private final String serviceID;
    private final String serviceName;


    //Constructor
    public ServiceCard(Bitmap serviceImg, String serviceID, String serviceName) {
        this.serviceImg = serviceImg;
        this.serviceID = serviceID;
        this.serviceName = serviceName;
    }

    public Bitmap getServiceImg() {
        return serviceImg;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }





}
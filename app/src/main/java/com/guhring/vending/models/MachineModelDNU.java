package com.guhring.vending.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by SmithW on 7/21/2016.
 */
public class MachineModelDNU {

    private  int Id;
    private  String CustomerName;
    private  String EndUser;
    private  String ModelNumber;
    private  String City;
   /* private  String ThumbnailUrl;*/


    public MachineModelDNU() {

    }

    public MachineModelDNU(String CustomerName, String EndUser, String ModelNumber, String City ) {
        this.CustomerName = CustomerName;
        this.EndUser = EndUser;
        this.ModelNumber = ModelNumber;
        this.City = City;
        /*this.ThumbnailUrl = ThumbnailUrl;*/
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getEndUser() {
        return EndUser;
    }

    public void setEndUser(String endUser) {
        EndUser = endUser;
    }

    /*public String getThumbnailUrl() {
        return ThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.ThumbnailUrl = thumbnailUrl;
    }*/

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String modelNumber) {
        ModelNumber = modelNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }



   /* public static final String MACHINE_ID           = "Id";
    public static final String MACHINE_CUSTOMERNAME = "CustomerName";
    public static final String MACHINE_ENDUSER      = "EndUser";
    public static final String MACHINE_MODELNUMBER  = "ModelNumber";
    public static final String MACHINE_CITY         = "City";*/


}

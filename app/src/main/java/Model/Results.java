package Model;

import java.util.ArrayList;
import java.util.List;

public class Results {
    public ArrayList<Photos> photos;

    private String id;

    private String place_id;

    private String icon;

    private String name;

    private String formatted_address;

    private String rating;

    private String[] types;

    private String reference;

    //private Geometry geometry;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
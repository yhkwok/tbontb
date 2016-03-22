/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scr08
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringEscapeUtils;

public class Product {
    
    
    private String BuyURL;
    private String ImageURL;
    private String Description;
    private String Name;
    private String Cost;
    
    public Product(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        try {
            map = mapper.readValue(json, Map.class);
            this.BuyURL = map.get("productUrl").toString();
            this.ImageURL = map.get("largeImage").toString();
            this.Description = map.get("shortDescription").toString();
            this.Name = map.get("name").toString();
            //if cost is null replace with the string Unknown
            this.Cost = map.get("salePrice").toString();
        } catch (IOException ex) {
            this.BuyURL = "";
            this.ImageURL = "";
            this.Description = StringEscapeUtils.unescapeHtml4("");
            this.Name = "Error Loading";
            //if cost is null replace with the string Unknown
            this.Cost = "";
        }
    }

    public Product(String Name, String Description, String Cost, String BuyURL, String ImageURL) {
        this.BuyURL = BuyURL;
        this.ImageURL = ImageURL;
        this.Description = StringEscapeUtils.unescapeHtml4(Description);
        this.Name = Name;
        //if cost is null replace with the string Unknown
        this.Cost = (Cost != null) ? Cost: "Unknown";
    }

    public String getBuyURL() {
        return BuyURL;
    }

    public void setBuyURL(String BuyURL) {
        this.BuyURL = BuyURL;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String Cost) {
        this.Cost = Cost;
    }
    
    public void getJson()
    {
        String json = "{\"name\":\"" + this.Name +  "\","
                + "\"shortDescription\":\"" + this.Description +  "\","
                + "\"salePrice\":\"" + this.Cost +  "\","
                + "\"productUrl\":\"" + this.BuyURL +  "\","
                + "\"largeImage\":\" "+  this.ImageURL + "\"}";
    }
}

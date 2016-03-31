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
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;

public class Product {
    
    private String BuyURL;
    private String ImageURL;
    private String ThumbImageURL;
    private String Description;
    private String Name;
    private String Cost;
    private int votes;

    public int getVotes() {
        return votes;
    }
    
    private String fixJson(String json){
        return json.replace('"', '\'');
    }
    

    public void setVotes(int votes) {
        this.votes = votes;
    }
    
    public Product(LinkedHashMap map)
    {
        //set the link to buy
        try
        {
            this.BuyURL = map.get("productUrl").toString();
        }
        catch (Exception ex)
        {
            //default to plain old walmart.com
            this.BuyURL = "http://www.walmart.com/";
        }
        //set the description
        try
        {
            this.Description = fixJson(StringEscapeUtils.unescapeHtml4(map.get("shortDescription").toString()));
        }
        catch (Exception ex)
        {
            //thy the long description
            try
            {
                this.Description = fixJson(StringEscapeUtils.unescapeHtml4(map.get("longDescription").toString()));
            }
            catch (Exception innerEx)
            {
                this.Description = "No Description Available";
            }
        }
  
        //set the image url
        try
        {
            this.ImageURL = map.get("mediumImage").toString();
        }
        catch (Exception ex)
        {
            this.ImageURL = "";
        }
        //set the image url
        try
        {
            this.ThumbImageURL = map.get("thumbnailImage").toString();
        }
        catch (Exception ex)
        {
            this.ThumbImageURL = "";
        }
        //set the name
        try
        {
            this.Name = fixJson(map.get("name").toString());
        }
        catch (Exception ex)
        {
            this.Name = "No Name Available";
        }
        //set the price
        try
        {
            this.Cost = map.get("salePrice").toString();
        }
        catch (Exception ex)
        {
            this.Cost = "No Price Available";
        }
        this.votes = 0;
    }
    
    public Product(String jsonEscaped)
    {
        String json = StringEscapeUtils.unescapeHtml4(jsonEscaped);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        try {
            map = mapper.readValue(json, Map.class);
            this.BuyURL = map.get("productUrl").toString();
            this.ImageURL = map.get("mediumImage").toString();
            this.ThumbImageURL = map.get("thumbnailImage").toString();
            this.Description = map.get("shortDescription").toString();
            this.Name = map.get("name").toString();
            //if cost is null replace with the string Unknown
            this.Cost = map.get("salePrice").toString();
            this.votes = 0;
        } catch (IOException ex) {
            System.out.print("Error creating productclass.from" + json);
            System.out.print(ex.getMessage());
            this.BuyURL = "";
            this.ImageURL = "";
            this.ThumbImageURL = "";
            this.Description = "";
            this.Name = "Error Loading";
            //if cost is null replace with the string Unknown
            this.Cost = "";
            this.votes = 0;
        }
    }

     public Product(String Name, String Description, String Cost, String BuyURL, String ImageURL, int Count) {
        this.BuyURL = BuyURL;
        this.ImageURL = ImageURL;
        this.Description = StringEscapeUtils.unescapeHtml4(Description);
        this.Name = Name;
        //if cost is null replace with the string Unknown
        this.Cost = (Cost != null) ? Cost: "Unknown";
        this.votes = Count;
    }
     
    public Product(String Name, String Description, String Cost, String BuyURL, String ImageURL) {
        this.BuyURL = BuyURL;
        this.ImageURL = ImageURL;
        this.Description = StringEscapeUtils.unescapeHtml4(Description);
        this.Name = Name;
        //if cost is null replace with the string Unknown
        this.Cost = (Cost != null) ? Cost: "Unknown";
        this.votes = 0;
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

    public String getThumbImageURL() {
        return ThumbImageURL;
    }

    public void setThumbImageURL(String ThumbImageURL) {
        this.ThumbImageURL = ThumbImageURL;
    }
    
    public String getJson()
    {
        String json = "{&quot;name&quot;:&quot;" + this.Name +  "&quot;,"
                + "&quot;shortDescription&quot;:&quot;" + this.Description +  "&quot;,"
                + "&quot;salePrice&quot;:&quot;" + this.Cost +  "&quot;,"
                + "&quot;productUrl&quot;:&quot;" + this.BuyURL +  "&quot;,"
                + "&quot;thumbnailImage&quot;:&quot; "+  this.ThumbImageURL + "&quot;,"
                + "&quot;mediumImage&quot;:&quot; "+  this.ImageURL + "&quot;}";
        return StringEscapeUtils.escapeHtml4(json);
    }
}

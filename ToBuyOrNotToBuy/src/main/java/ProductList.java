
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scr08
 */
public class ProductList {
    private ArrayList<Product> products;
    private String PollName;

    public String getPollName() {
        return PollName;
    }

    public void setPollName(String PollName) {
        this.PollName = PollName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    
    public String getProductsAsJson()
    {
        String json = "{&quot;list&quot;:[";
        int length = this.products.size();
        //1 minus length to handle the last object not having a comma
        for (int i = 0; i < length - 1; i++)
        {
            json = json.concat(this.products.get(i).getJson().concat(","));
            
        }
        
        
        json = json.concat(this.products.get(length - 1).getJson().concat("]}"));
        return StringEscapeUtils.escapeHtml4(json);
    }

    public void setProducts(String json)
    {
        System.out.println("PRODUCTLIST JSON: " + json);
        if (json == null)
        {
            //do nothing...
            System.out.print("in if");
        }
        else if (json.equals("") || json.equals("null"))
        {
            
            System.out.print("in elif");
            //still do nothing...
        }
        else
        {
            System.out.print("in else");
            json = StringEscapeUtils.unescapeHtml4(json);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map;
            try {
                map = mapper.readValue(json, Map.class);
                ArrayList<LinkedHashMap> productsList = (ArrayList<LinkedHashMap>)map.get("list");

                for (LinkedHashMap productMap : productsList)
                {
                    Product p = new Product(productMap);
                    this.products.add(p);
                }           
            } catch (IOException ex) {
                System.out.println("ERROR");
            }
        }
    }
    
    public void addProduct(String json)
    {
        this.products.add(new Product(json));
    }

    public ProductList(String json) {
        products = new ArrayList<>();
        this.setProducts(json);
    }
    public ProductList() {
        products = new ArrayList<>();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author scr08
 */
@WebServlet(urlPatterns = {"/GetSearchResults"})
public class GetSearchResults extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String query = request.getParameter("SearchString");
            List<String> items = (List<String>)request.getAttribute("SelectedResults");
            
            
            query = "pokemon";
            URL url = new URL("http://api.walmartlabs.com/v1/search?"
                    + "apiKey=pqeub2vpccznk89myanw2qbf&query=" + query);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(url, Map.class);
            
            //com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.util.LinkedHashMap out of START_ARRAY token
            //Map<String, Object> map2 = mapper.readValue(map.get("items").toString(), Map.class);
            ArrayList map2 = (ArrayList) map.get("items");
            System.out.println(map2.size());
            List<Product> products = new ArrayList<Product>();
            /*for (Object obj : map2)
            {
                LinkedHashMap item =  (LinkedHashMap) obj;
                System.out.println(item.keySet());
                Product product = new Product(
                    item.get("name").toString(), 
                    item.get("shortDescription").toString(),
                    item.get("salePrice").toString(),
                    item.get("productUrl").toString(),
                        //NOTE: this image line is probably dangerous... should be in a try block...
                    item.get("largeImage").toString());
                products.add(product);
            }*/
            Product p = new Product("{\"name\":\"THISISNAME\","
                + "\"shortDescription\":\"DESCTIPTION\","
                + "\"salePrice\":\"COST\","
                + "\"productUrl\":\"BUY\","
                + "\"largeImage\":\"IMG\"}");
            products.add(p);
            
            request.setAttribute("Products",products);
            request.getRequestDispatcher("ProductForm.jsp").forward(request, response);
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

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
import org.apache.commons.lang3.StringEscapeUtils;

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
        
        String SelectedSearchResults = "";
        String query = "";
        try 
        {
            
            query =  request.getParameter("SearchString").replace(' ', '+');
            SelectedSearchResults = StringEscapeUtils.escapeHtml4(request.getParameter("SelectedSearchResults"));
            System.out.println("GSR: "+ SelectedSearchResults);
            System.out.print("Query: " + query);
            //String 
            //List<String> items = (List<String>)request.getAttribute("SelectedResults");
            
            
            URL url = new URL("http://api.walmartlabs.com/v1/search?"
                + "apiKey=pqeub2vpccznk89myanw2qbf&query=" + query);
            System.out.print("a");
            ObjectMapper mapper = new ObjectMapper();
            System.out.print("a");
            Map<String, Object> map = mapper.readValue(url, Map.class);
            System.out.print("a");
            
            ArrayList map2 = (ArrayList) map.get("items");
            List<Product> products = new ArrayList<Product>();
            for (Object obj : map2)
            {
                LinkedHashMap item =  (LinkedHashMap) obj;
                Product product = new Product(item);
                products.add(product);
            }
            
            System.out.print("b");
            String j = "{\"list\":["+products.get(0).getJson()+"]}";
            ProductList pl = new ProductList();
            pl.setProducts(j);
            
            System.out.print("c");
            request.setAttribute("Products",products);
            request.setAttribute("SelectedSearchResults", SelectedSearchResults);
            request.getRequestDispatcher("ProductForm.jsp").forward(request, response);
        }
        catch (Exception ex)
        {
            request.setAttribute("SelectedSearchResults", SelectedSearchResults);
            request.getRequestDispatcher("SearchWalmart.jsp").forward(request, response);
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

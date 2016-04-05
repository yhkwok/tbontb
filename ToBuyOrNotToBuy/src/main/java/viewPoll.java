/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author YH Jonathan Kwok
 */
@WebServlet(urlPatterns = {"/viewPoll"})
public class viewPoll extends HttpServlet {

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
        
        /*String friendName = request.getParameter("FriendName");
        String friendEmailAddress = request.getParameter("FriendEmailAddress");
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        */
        ArrayList<Product> products = new ArrayList<Product>();
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            String URL;
            String USER;
            String PASS;
            int pollID;
            if(host == null || host == ""){
                URL = "jdbc:mysql://localhost/tbontb";
                USER = "root";
                PASS = "";
            }
            else{
                String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                URL = "jdbc:mysql://" + host + ":" + port + "/tbontb";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }
                        
            Connection conn = null;
            Statement stmt = null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                if(conn == null)
                    System.out.println("NULL\n");
                stmt = conn.createStatement();
                
                String sql = "Select pollName from UserPolls where id = 1";// + pollID;
                //execute
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next())
                {
                     String pollname = rs.getString("pollName");
                }
                
                sql = "Select id, name, userPollID, price, description, imageLink, buyLink, score from UserPollitems where userPollID = 1";// + pollID;
                //execute
                rs = stmt.executeQuery(sql);
                while (rs.next())
                {
                    
                     int id =Integer.parseInt(rs.getString("id"));
                     System.out.println(id);
                     //p.setId(id);
                     
                     String name = rs.getString("name");
                     System.out.println(name);
                     //p.setName(name);
                     
                     int userPollID = Integer.parseInt(rs.getString("userPollID"));
                     System.out.println(userPollID);
                     //p.setPollID(userPollID);
                     
                     String price = rs.getString("price");
                     System.out.println(price);
                     //p.setCost(price);
                     
                     String description = rs.getString("description");
                     System.out.println(description);
                     //p.setDescription(description);
                     
                     String imageLink = rs.getString("imageLink");
                     System.out.println(imageLink);
                     //p.setImageURL(imageLink);
                     
                     String buyLink = rs.getString("buyLink");
                     System.out.println(buyLink);
                     //p.setBuyURL(buyLink);
                     
                     int score = Integer.parseInt(rs.getString("score"));
                     System.out.println(score);
                     //p.setVotes(score);
                     Product p = new Product(buyLink, imageLink, description, name, price, score);
                     products.add(p);
                }
                
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException se2) {
                }
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            request.setAttribute("products", products);
            request.getRequestDispatcher("view_poll.jsp").forward(request, response);
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

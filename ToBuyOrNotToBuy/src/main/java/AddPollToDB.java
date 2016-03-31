/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author scr08
 */
@WebServlet(urlPatterns = {"/AddPollToDB"})
public class AddPollToDB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
            
            String SelectedSearchResults = request.getParameter("SelectedSearchResults");
            ProductList selectedItems = new ProductList(SelectedSearchResults);
            
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
                String sql = "INSERT INTO UserPolls(creatorUserID, pollName) VALUES "
                        + "(1, " + selectedItems.getPollName() + ")";
                //execute and get last insert id
                pollID = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                
                for (Product p : selectedItems.getProducts())
                {
                     sql = "INSERT INTO UserPollItems(name, userPollID, price, description, imageLink, buyLink) VALUES "
                            + "(" + p.getName() + ", " + pollID + ", " + 
                             p.getCost() + ", " + p.getDescription() + ", " + 
                             p.getImageURL() + ", " + p.getBuyURL() + ")";
                    stmt.executeUpdate(sql);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddPollToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddPollToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddPollToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddPollToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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

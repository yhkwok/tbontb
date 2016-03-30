/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String SelectedSearchResults = request.getParameter("SelectedSearchResults");
            ProductList selectedItems = new ProductList(SelectedSearchResults);
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddPollToDB</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            String URL;
            String USER;
            String PASS;
            if(host == null || host == ""){
                URL = "jdbc:mysql://localhost/tbontb"; //<--change database name
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
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Connected database successfully...");
                
                 //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                
                out.println("ADD ME TO DB(PollName): " + selectedItems.getPollName() + "</br>");

                String sql = "Insert INTO UserPolls(creatorUserID, pollName) VALUES "
                        + "(1, " + selectedItems.getPollName() + ")";
                //execute and get last insert id
                int pollID = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                
                
                
                for (Product p : selectedItems.getProducts())
                {
                    out.println("<div>");
                    out.println("ADD ME TO DB(Name): " + p.getName() + "</br>");
                    out.println("ADD ME TO DB(Cost): " + p.getCost()+ "</br>");
                    out.println("ADD ME TO DB(Desc): " + p.getDescription()+ "</br>");
                    out.println("ADD ME TO DB(ImgUrl): " + p.getImageURL()+ "</br>");
                    out.println("ADD ME TO DB(BuyUrl) " + p.getBuyURL()+ "</br>");
                    out.println("</div><br/><br/>");
                    //comment
                
                     sql = "Insert INTO UserPollItems(name, userPollID, price, description, imageLink, buyLink) VALUES "
                            + "(" + p.getName() + ", " + pollID + ", " + 
                             p.getCost() + ", " + p.getDescription() + ", " + 
                             p.getImageURL() + ", " + p.getBuyURL() + ")";
                    stmt.executeQuery(sql);
                }
            } catch (SQLException | ClassNotFoundException se) {
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                }//end finally try
            }//end try
            
            out.println("</body>");
            out.println("</html>");
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

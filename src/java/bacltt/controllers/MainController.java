/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thúy Bắc
 */
public class MainController extends HttpServlet {
    public static final String ERROR="error.jsp";
    public static final String LOGIN="LoginController";
    public static final String LOGOUT="LogoutController";
    public static final String GETPRODUCT="ProductController";
    public static final String LINK_LOGIN="login.jsp";
    public static final String HOME_PAGE="BackToHomeController";
    public static final String CREATE_FOOD_PAGE="create_page.jsp";
    public static final String CREATE_FOOD="CreateFoodController";

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
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if("getProduct".equals(action)){
                url = GETPRODUCT;
            } else if("getLinkLogin".equals(action)){
                url = LINK_LOGIN;
            } else if("homePage".equals(action)){
                url = HOME_PAGE;
            } else if("Login".equals(action)){
                url =  LOGIN;
            } else if("Logout".equals(action)){
                url =  LOGOUT;
            } else if("createFood".equals(action)){
                url =  CREATE_FOOD_PAGE;
            } else if("Create_Product".equals(action)){
                url =  CREATE_FOOD;
            }
            
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

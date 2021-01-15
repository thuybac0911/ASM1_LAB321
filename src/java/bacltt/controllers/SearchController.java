/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.CateDAO;
import bacltt.daos.ProductDAO;
import bacltt.dtos.CateDTO;
import bacltt.dtos.ProductDTO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thúy Bắc
 */
public class SearchController extends HttpServlet {
    private static final String ERROR = "search.jsp";
    private static final String GUEST = "search.jsp";
    private static final String USER = "user_page.jsp";
    

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
            String cateName = request.getParameter("cboCateName");
            String productName = request.getParameter("txtSearch");
            String roleID  = request.getParameter("txtRoleID");
//            String min = request.getParameter("txtMin");
//            String max = request.getParameter("txtMax");
            String cateID = "";
            if("Drinks".equals(cateName)){
                cateID = "C01";
            }
            if("Cakes".equals(cateName)){
                cateID = "C02";
            }
            if("Candies".equals(cateName)){
                cateID = "C03";
            }
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.getListProduct(productName, cateID);
            HttpSession session = request.getSession();
            session.setAttribute("SEARCH_LIST_FOOD", list);
            if(roleID == null ){
                url = GUEST;
            } else if ("US".equals(roleID)){
                url = USER;
            }
        } catch (Exception e) {
            log("ERROR at SearchController: "+ e.getMessage());
        }finally{
            response.sendRedirect(url);
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

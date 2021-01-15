/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.ProductDAO;
import bacltt.dtos.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thúy Bắc
 */
public class GetAllProductController extends HttpServlet {
    private static final String GUEST = "index.jsp";
    private static final String ADMIN = "admin_page.jsp";
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
        String url = "";
        try {
            String roleID = request.getParameter("txtRoleID");
            if(roleID == null ){
                url = GUEST;
            } else if ("AD".equals(roleID)) {
                url = ADMIN;
            }
            String cateName = request.getParameter("cateName");
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
            ProductDAO proDao = new ProductDAO();
            List<ProductDTO> listPro = proDao.findProductByCateID(cateID);
            HttpSession session = request.getSession();
            session.setAttribute("LIST_PRO", listPro);

        } catch (Exception e) {
            log("ERROR at ProductContoller: " + e.getMessage());
        }finally{
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

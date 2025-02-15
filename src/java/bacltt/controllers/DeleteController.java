/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.LogDAO;
import bacltt.daos.ProductDAO;
import bacltt.dtos.LogDTO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thúy Bắc
 */
public class DeleteController extends HttpServlet {
    private static final String ERROR = "errorDelete.jsp";
    private static final String SUCCESS = "GetAllProductController";
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
            //logID: Log-productID-countLog
            String id = request.getParameter("txtProductID");
            ProductDAO dao = new ProductDAO();
            dao.deleteProduct(id);
            
            String user = request.getParameter("txtUserID");
            LogDAO logDao = new LogDAO();
            String logID = logDao.getLastLogIDByProductID(id);
            if(logID == null){
                logID = "Log-"+id+"-1";
            }else{
                String[] tmp = logID.split("-");
                int count = Integer.parseInt(tmp[2]);
                logID = "Log-"+id+"-"+(count+1);
            }
            Date dateAction = new Date();
            logDao.insertLog(new LogDTO(logID, id, user, "Delete", "update status to true", dateAction));
            url = SUCCESS;
        } catch (Exception e) {
            log("ERROR at DeleteController: " + e.getMessage());
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

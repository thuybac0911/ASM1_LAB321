/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.LogDAO;
import bacltt.daos.ProductDAO;
import bacltt.dtos.LogDTO;
import bacltt.dtos.ProductDTO;
import bacltt.dtos.ProductErrorDTO;
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
public class UpdateController extends HttpServlet {
    private final static String SUCCESS = "GetAllProductController";
    private final static String ERROR = "update_page.jsp";
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
        ProductErrorDTO errorPro = new ProductErrorDTO("", "", "", "", "");
        try {
            String roleID = request.getParameter("txtRoleID");
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String price = request.getParameter("txtPrice");
            String quantity = request.getParameter("txtQuantity");
            String description = request.getParameter("txtDescription");
            String image = request.getParameter("txtImage");
            String cateName = request.getParameter("cboCateID");
            String cateID="";
            if("Drinks".equals(cateName)){
                cateID = "C01";
            }
            if("Cakes".equals(cateName)){
                cateID = "C02";
            }
            if("Candies".equals(cateName)){
                cateID = "C03";
            }
            boolean valid=true;
            if (productName.length() > 100) {
                valid = false;
                errorPro.setProductNameError("Food Name in range[1-100]");
            }

            if (quantity.matches("\\d")) {
                valid = false;
                errorPro.setQuantityError("Quantity must be integer number");
            }
            if(price.matches("\\w")){
                valid = false;
                errorPro.setPriceError("Price must be number");
            }
            ProductDAO dao = new ProductDAO();
            ProductDTO product = new ProductDTO(productID, productName, Float.parseFloat(price), Integer.parseInt(quantity), description, "img/"+image,cateID);
            boolean check = dao.updateProduct(product);
            if(valid){
                if("AD".equals(roleID)){
                    if(check){
                        String user = request.getParameter("txtUserID");
                        LogDAO logDao = new LogDAO();
                        String logID = logDao.getLastLogIDByProductID(productID);
                        if(logID == null){
                            logID = "Log-"+productID+"-1";
                        }else{
                            String[] tmp = logID.split("-");
                            int count = Integer.parseInt(tmp[2]);
                            logID = "Log-"+productID+"-"+(count+1);
                        }
                        Date dateAction = new Date();
                        logDao.insertLog(new LogDTO(logID, productID, user, "Update", "update product", dateAction));
                        url = SUCCESS; 
                    }
                }
            }else{
                request.setAttribute("ERROR", errorPro);
            }

        } catch (Exception e) {
            log("ERROR at UpdateController: " + e.getMessage());
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

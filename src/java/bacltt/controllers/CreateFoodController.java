/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.ProductDAO;
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
public class CreateFoodController extends HttpServlet {
    private final static String SUCCESS = "admin_page.jsp";
    private final static String ERROR = "create_page.jsp";
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
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String price = request.getParameter("txtPrice");
            String quantity = request.getParameter("txtQuantity");
            String description = request.getParameter("txtDescription");
            String image = request.getParameter("txtImage");
            Date createDate = new Date();
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
            boolean check = true;
            
            if (!productID.matches("F-\\d{3}")) {
                errorPro.setProductIDError("ID format: F-xxx");
                check = false;
            }
            if (productName.length() > 100) {
                check = false;
                errorPro.setProductNameError("Food Name in range[1-100]");
            }

            if (!quantity.matches("\\d")) {
                check = false;
                errorPro.setQuantityError("Quantity must be integer number");
            }
            if(price.matches("\\w")){
                check = false;
                errorPro.setPriceError("Price must be number");
            }
            
            if(check){
                ProductDTO product = new ProductDTO(productID, productName, Float.parseFloat(price), Integer.parseInt(quantity), description, "img/"+image , createDate, 0, cateID);
                ProductDAO dao = new ProductDAO();
                boolean checkInsert = dao.insert(product);
                if(checkInsert){
                    url = SUCCESS;
                }
            }else{
                request.setAttribute("ERROR", errorPro);
            }
        } catch (Exception e) {
            String errorStr = e.toString();
            if (errorStr.contains("duplicate")) {
                errorPro.setProductIDError("Duplicate FoodID!");
                request.setAttribute("ERROR", errorPro);
            }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.OrderDAO;
import bacltt.daos.ProductDAO;
import bacltt.dtos.CartDTO;
import bacltt.dtos.OrderDTO;
import bacltt.dtos.OrderDetailDTO;
import bacltt.dtos.ProductDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class OrderController extends HttpServlet {
    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "success.jsp";
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
            List<String> listError = new ArrayList<>();
            //order: OD-userID-countOrder
            //orderDetail: OD-userID-countOrder-countProduct (orderID-countProduct)
            HttpSession session = request.getSession();
            CartDTO shoppingCart = (CartDTO)session.getAttribute("shoppingCart");
            String userID = shoppingCart.getCustomerName();
            OrderDAO orderDAO = new OrderDAO();
            String orderID = orderDAO.getLastOrderIDByUser(userID);
            if(orderID == null){
                orderID = "OD-"+userID+"-1";
            }else{
                String[] tmp = orderID.split("-");
                int count = Integer.parseInt(tmp[2]);
                orderID = "OD-" + userID + "-" + (count+1);
            }
            Date dateOfCreate = new Date();
            float total = shoppingCart.getTotal();
            String status = "waiting";
            String payment = "COD";
            OrderDTO orderDTO = new OrderDTO(orderID, userID, total, dateOfCreate,payment,status);
            ProductDAO proDAO = new ProductDAO();
            boolean isEnough = true;
            for(ProductDTO productDTO: shoppingCart.getShoppingCart().values()){
                int quantityDB = proDAO.getQuantity(productDTO.getProductID());
                if (quantityDB < productDTO.getQuantity()) {
                    isEnough = false;
                    listError.add(productDTO.getProductName() + " have " + quantityDB + " left\n");
                } 
            }
            if(isEnough){
                if(orderDAO.inserOrder(orderDTO)){
                int count =1 ;
                    for(ProductDTO productDTO: shoppingCart.getShoppingCart().values()){
                        String orderDetailID = orderID +"-"+count++;
                        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                        orderDetailDTO.setDetailID(orderDetailID);
                        orderDetailDTO.setOrderID(orderID);
                        orderDetailDTO.setProductID(productDTO.getProductID());
                        orderDetailDTO.setQuantity(productDTO.getQuantity());
                        orderDetailDTO.setPrice(productDTO.getPrice());

                        orderDAO.insertOrderDetail(orderDetailDTO);
                        orderDAO.updateQuantity(orderDetailDTO.getQuantity(), orderDetailDTO.getProductID());
                    }
                }
                shoppingCart=null;
                url=SUCCESS;
            }else{
                request.setAttribute("ERROR_CHECKOUT", listError);
            }
        } catch (Exception e) {
            log("ERROR at OrderController: "+e.getMessage());
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

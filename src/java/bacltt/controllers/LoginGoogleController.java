/*
 * To change this license header, choose License Headers in Project Properties.
 * To cnethange this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.controllers;

import bacltt.daos.UserDAO;
import bacltt.dtos.UserDTO;
import bacltt.google.GooglePojo;
import bacltt.google.GoogleUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thúy Bắc
 */
public class LoginGoogleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "user_page.jsp";

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

            String code = request.getParameter("code");
            if (code != null || !code.isEmpty()) {
                UserDAO dao = new UserDAO();
                HttpSession session = request.getSession();
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo gPojo = GoogleUtils.getUserInfo(accessToken);
                String userID = gPojo.getId();
                UserDTO user = dao.checkLoginGG(userID);
                if (user == null) {
                    String gmail = gPojo.getEmail();
                    user = new UserDTO(userID, gmail, "USG", gmail, "");
                    dao.createUserGG(user);
                }
                session.setAttribute("LOGIN_USER", user);
                session.setAttribute("userID", userID);
                url = SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
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

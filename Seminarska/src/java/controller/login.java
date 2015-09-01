/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

/**
 *
 * @author martin
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
        String sparqlEndpoint = "http://localhost:2020/sparql";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sparqlQuery = "PREFIX vocab: <http://localhost:2020/resource/vocab/> \n"
                + "SELECT ?username ?password \n"
                + "WHERE { \n "
                + " ?user vocab:users_username \""+username+"\"; \n"
                + " vocab:users_username ?username. \n"
                + " ?user vocab:users_password \""+password+"\"; \n"
                + " vocab:users_password ?password.}";
        
        Query query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec1 = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
                  ResultSet result = qexec1.execSelect();
                  
                  if(result.hasNext()){
                      QuerySolution sol = result.nextSolution();
                      
                      HttpSession session = request.getSession();
                      session.setAttribute("username", username);
                      getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                  }
                  
                  else{
                      getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                  }
                    
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

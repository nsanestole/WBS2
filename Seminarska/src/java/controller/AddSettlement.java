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
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;

/**
 *
 * @author martin
 */
public class AddSettlement extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/addSettlement.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //Servlet za dodavanje na grad vo baza
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sparqlEndpoint = "http://localhost:2020/sparql";
        String settlementName = request.getParameter("name");
        String population = request.getParameter("population");
        String Abstract = request.getParameter("abstract");
        String leader = request.getParameter("leader");
        
        String sqlQuery = "INSERT DATA { \n"
        + "<http://localhost:2020/data/city> vocab:city_name \""+settlementName+"\" \n"
                +"vocab:city_population \""+population+"\" \n"
                +"vocab:city_abstr \""+Abstract+"\" \n"
                +"vocab:city_leader \""+leader+"\" \n"
                + "}";
        
        try (QueryExecution qexec1 = QueryExecutionFactory.sparqlService(sparqlEndpoint, sqlQuery)) {
            
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

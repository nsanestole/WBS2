/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grad;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;

/**
 *
 * @author martin
 */
public class CityDetails extends HttpServlet {

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
        String sparqlEndpoint = "http://dbpedia.org/sparql";
         
           String sparqlQuery = ""
                   + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                   + "prefix dbr: <http://dbpedia.org/resource/>\n"
                   + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                   + "select ?population\n"
                   + "where { dbr:Skopje dbo:populationTotal ?population;\n"
                   + "}";
           
           Query query = QueryFactory.create(sparqlQuery);
            try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query))
            {
                org.apache.jena.query.ResultSet result = qexec.execSelect();
                
                    QuerySolution sol = result.nextSolution();
                    Grad g = new Grad();
                   
                    g.setPopulation(sol.get("population").toString());
                
                request.setAttribute("grad", g);
                
                System.out.println("GRAD"+g.getPopulation());
                
           
        } 
         getServletContext().getRequestDispatcher("/cityDetails.jsp").forward(request, response);
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
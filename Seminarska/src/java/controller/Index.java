/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grad;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;

/**
 *
 * @author Stole
 */
public class Index extends HttpServlet {

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
    
    //Pocetna strana 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Za prikaz na stranicno meni i na podatocite pod carousel
        String sparqlEndpoint = "http://dbpedia.org/sparql";
        String sparqlQuery = ""
                + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                + "prefix dbr: <http://dbpedia.org/resource/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                +
"select ?grad ?slika\n" +
"where { ?grad dbo:country dbr:Republic_of_Macedonia;\n" +
"              rdf:type dbo:Settlement;\n" +
"              dbo:thumbnail ?slika. }";
        org.apache.jena.query.Query query = QueryFactory.create(sparqlQuery);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query))
        {
            org.apache.jena.query.ResultSet result = qexec.execSelect();
            ArrayList<Grad> lista = new ArrayList<>();
            while(result.hasNext())
            {
                QuerySolution sol = result.nextSolution();
                Grad g = new Grad();
                g.setName(sol.get("grad").toString());
                g.setImgUrl(sol.get("slika").toString());
                lista.add(g);
            }
            
            request.setAttribute("gradovi", lista);
        }
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
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

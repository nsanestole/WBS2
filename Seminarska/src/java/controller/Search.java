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
import javax.servlet.http.HttpSession;
import model.Grad;
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
public class Search extends HttpServlet {

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
        String search = request.getParameter("search");
        //Za prebaruvanje na grad od baza 
        String sparqlEndpoint = "http://localhost:2020/sparql";
        String sparqlQuery = "PREFIX vocab: <http://localhost:2020/resource/vocab/> \n"
                + "SELECT * WHERE { \n"
                + "?x vocab:city_name ?name; \n"
                + " vocab:city_thumb ?thumb. \n"
                + " FILTER regex(?name , \"" + search + "\", \"i\") }";
        Query query = QueryFactory.create(sparqlQuery);
        ArrayList<Grad> lista = new ArrayList<>();
        try (QueryExecution qexec1 = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            ResultSet result = qexec1.execSelect();
            if (result.hasNext()) {
                 
                while (result.hasNext()) {
                    QuerySolution sol = result.nextSolution();
                    Grad g = new Grad();
                    g.setName(sol.get("name").toString());
                    g.setImgUrl(sol.get("thumb").toString());

                    lista.add(g);
                }

                request.setAttribute("gradovi", lista);
                getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
            } else {
                //Za prebaruvanje na podatoci od dbpedia
                sparqlEndpoint = "http://dbpedia.org/sparql";
                sparqlQuery = ""
                        + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                        + "prefix dbr: <http://dbpedia.org/resource/>\n"
                        + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                        + "select ?grad ?slika\n"
                        + "where { ?grad dbo:country dbr:Republic_of_Macedonia;\n"
                        + "              rdf:type dbo:Settlement;\n"
                        + "              dbo:thumbnail ?slika. \n"
                        + "FILTER regex(?grad , \"" + search + "\", \"i\")}";
                query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
                    result = qexec.execSelect();
                    if (result.hasNext()) {
                        while (result.hasNext()) {
                            QuerySolution sol = result.nextSolution();
                            Grad g = new Grad();
                           String part = sol.get("grad").toString();
                           String[] parts = part.split("/");
                            g.setName(parts[parts.length-1]);
                            g.setImgUrl(sol.get("slika").toString());

                            lista.add(g);
                        }

                        request.setAttribute("gradovi", lista);
                        getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/sorryPage.jsp").forward(request, response);
                    }
                }

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

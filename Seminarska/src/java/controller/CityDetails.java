/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CityDetail;
import model.Grad;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;

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
        String str = request.getParameter("grad");
        System.out.println("string:" + str);
        String sparqlQuery = ""
                + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                + "prefix dbr: <http://dbpedia.org/resource/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix dbp: <http://dbpedia.org/property/>\n"
                + "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n"
                + "select ?name ?population ?abstract ?leader ?slika ?lat ?long\n"
                + "where {\n"
                + "dbr:" + str + " dbp:nativeName ?name; \n"
                + "dbo:populationTotal ?population; \n"
                + "dbo:thumbnail ?slika; \n"
                + "dbo:abstract ?abstract; \n"
                + "geo:lat ?lat; \n"
                + "geo:long ?long; \n"
                + "dbp:leaderName ?leader; \n"
                + "Filter(lang (?abstract) = \"en\")}";

        Query query = QueryFactory.create(sparqlQuery);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            org.apache.jena.query.ResultSet result = qexec.execSelect();
            if (result.hasNext()) {
                QuerySolution sol = result.nextSolution();
                System.out.println("SOL :" + sol.toString());

                CityDetail city = new CityDetail();

                if (sol.get("name") != null) {
                    city.setName(sol.get("name").toString());
                } else {
                    city.setName("");
                }

                if (sol.get("population") != null) {
                    city.setPopulation(sol.get("population").toString());
                } else {
                    city.setPopulation("");
                }

                city.setAbstract(sol.get("abstract").toString());

                if (sol.get("leader") != null) {
                    city.setLeader(sol.get("leader").toString());
                } else {
                    city.setLeader("");
                }

                if (sol.get("slika") != null) {
                    city.setThumb(sol.get("slika").toString());
                } else {
                    city.setThumb("");
                }

                if (sol.get("lat") != null) {
                    System.out.println(sol.get("lat").toString().split("\\^")[0]);
                    city.setLat(sol.get("lat").toString().split("\\^")[0]);
                } else {
                    city.setLat("0");
                }

                if (sol.get("long") != null) {
                    System.out.println(sol.get("long").toString().split("\\^")[0]);
                    city.setLongt(sol.get("long").toString().split("\\^")[0]);
                } else {
                    city.setLongt("0");
                }

                request.setAttribute("city", city);

                sparqlQuery = ""
                        + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                        + "prefix dbr: <http://dbpedia.org/resource/>\n"
                        + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                        + "select ?grad ?slika\n"
                        + "where { ?grad dbo:country dbr:Republic_of_Macedonia;\n"
                        + "              rdf:type dbo:Settlement;\n"
                        + "              dbo:thumbnail ?slika. }";
                query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
                    result = qe.execSelect();
                    ArrayList<Grad> lista = new ArrayList<>();
                    while (result.hasNext()) {
                        sol = result.nextSolution();
                        Grad g = new Grad();
                        g.setName(sol.get("grad").toString());
                        g.setImgUrl(sol.get("slika").toString());
                        lista.add(g);
                    }

                    request.setAttribute("gradovi", lista);

                    getServletContext().getRequestDispatcher("/cityDetails.jsp").forward(request, response);
                }
            } else {
                sparqlEndpoint = "http://localhost:2020/sparql";
                str = request.getParameter("grad");
                sparqlQuery = "PREFIX vocab: <http://localhost:2020/resource/vocab/> \n" 
                        +"SELECT ?name ?population ?abstract ?leader ?slika ?lat ?long \n"
                        + "where{ \n"
                        + "?id vocab:city_name \""+str+"\";\n" 
                        + "vocab:city_name ?name;\n "
                        + " vocab:city_population ?population; \n"
                        + " vocab:city_abstr ?abstract; \n "
                        + " vocab:city_leader ?leader; \n "
                        + " vocab:city_thumb ?slika; \n "
                        + " vocab:city_lat ?lat; \n "
                        + " vocab:city_longt ?long. } ";

                query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec1 = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
                    result = qexec1.execSelect();
                    if (result.hasNext()) {
                        QuerySolution sol = result.nextSolution();
                        System.out.println("SOL :" + sol.toString());

                        CityDetail city = new CityDetail();

                        if (sol.get("name") != null) {
                            city.setName(sol.get("name").toString());
                        } else {
                            city.setName("");
                        }

                        if (sol.get("population") != null) {
                            city.setPopulation(sol.get("population").toString());
                        } else {
                            city.setPopulation("");
                        }

                        city.setAbstract(sol.get("abstract").toString());

                        if (sol.get("leader") != null) {
                            city.setLeader(sol.get("leader").toString());
                        } else {
                            city.setLeader("");
                        }

                        if (sol.get("slika") != null) {
                            city.setThumb(sol.get("slika").toString());
                        } else {
                            city.setThumb("");
                        }

                        if (sol.get("lat") != null) {
                            System.out.println(sol.get("lat").toString().split("\\^")[0]);
                            city.setLat(sol.get("lat").toString().split("\\^")[0]);
                        } else {
                            city.setLat("0");
                        }

                        if (sol.get("long") != null) {
                            System.out.println(sol.get("long").toString().split("\\^")[0]);
                            city.setLongt(sol.get("long").toString().split("\\^")[0]);
                        } else {
                            city.setLongt("0");
                        }

                        request.setAttribute("city", city);
                        
                        sparqlEndpoint = "http://dbpedia.org/sparql";
                        sparqlQuery = ""
                                + "prefix dbo: <http://dbpedia.org/ontology/>\n"
                                + "prefix dbr: <http://dbpedia.org/resource/>\n"
                                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                                + "select ?grad ?slika\n"
                                + "where { ?grad dbo:country dbr:Republic_of_Macedonia;\n"
                                + "              rdf:type dbo:Settlement;\n"
                                + "              dbo:thumbnail ?slika. }";
                        query = QueryFactory.create(sparqlQuery);
                        try (QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
                            result = qe.execSelect();
                            ArrayList<Grad> lista = new ArrayList<>();
                            while (result.hasNext()) {
                                sol = result.nextSolution();
                                Grad g = new Grad();
                                g.setName(sol.get("grad").toString());
                                g.setImgUrl(sol.get("slika").toString());
                                lista.add(g);
                            }

                            request.setAttribute("gradovi", lista);

                        }

                        getServletContext().getRequestDispatcher("/cityDetails.jsp").forward(request, response);
                    }
                }
            }

        }
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

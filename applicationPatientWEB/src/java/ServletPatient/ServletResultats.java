/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPatient;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Nicolas
 */
@WebServlet(name = "servletAnalyses", value = "/Main")
public class ServletResultats extends HttpServlet {

    private ws.WSResultat port;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        ws.WSResultat_Service service = new ws.WSResultat_Service();
        port = service.getWSResultatPort();
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
        String type = request.getParameter("type");
        System.out.println("|||||WEB type: " + type);
        if (type != null)
        {
            int numDemande = Integer.parseInt(request.getParameter("numDemande"));

            if(type.equals("resultats")){
                request.setAttribute("ListeAnalyses", port.getAnalyses(numDemande));
            }

            request.getRequestDispatcher("/JSP/resultatsJSP.jsp").forward(request, response);
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

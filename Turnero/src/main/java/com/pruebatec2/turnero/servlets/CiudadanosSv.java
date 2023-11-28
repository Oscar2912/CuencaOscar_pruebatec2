package com.pruebatec2.turnero.servlets;

import com.pruebatec2.turnero.logica.Ciudadanos;
import com.pruebatec2.turnero.logica.Controladora;
import com.pruebatec2.turnero.logica.Turnos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "CiudadanosSv", urlPatterns = {"/CiudadanosSv"})
public class CiudadanosSv extends HttpServlet {
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CiudadanosSv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CiudadanosSv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id-ciudadano");
        Long longId;
        
        if(id.isEmpty())
            longId = null;
        else
            longId = Long.valueOf(id);
        
        if(longId != null){
            if(control.buscarCiudadano(longId) != null)
                request.setAttribute("resultadosCiudadanos", control.buscarCiudadano(longId));
            else
                request.setAttribute("resultadosCiudadanos", null);
        }else
            request.setAttribute("resultadosCiudadanos", null);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Ciudadanos ciudadano = new Ciudadanos();
        
        String nombre = request.getParameter("nombre-ciudadano");
        String apellido = request.getParameter("apellido-ciudadano");
        String dni = request.getParameter("dni-ciudadano");
        List<Turnos> turnos = new ArrayList<>();
        
        if(!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty()){
            ciudadano.setNombre(nombre);
            ciudadano.setApellido(apellido);
            ciudadano.setDni(dni);
            ciudadano.setTurnos(turnos);

            control.crearCiudadano(ciudadano);

            System.out.println(dni);
            response.sendRedirect("CiudadanosSv?id-ciudadano=" + control.buscarCiudadanoDni(dni));
        }else{
            System.out.println("Ciudadano no creado por falta de datos");

            response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

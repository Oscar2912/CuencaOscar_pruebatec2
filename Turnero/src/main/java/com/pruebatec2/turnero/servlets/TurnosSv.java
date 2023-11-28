package com.pruebatec2.turnero.servlets;

import com.pruebatec2.turnero.logica.Ciudadanos;
import com.pruebatec2.turnero.logica.Controladora;
import com.pruebatec2.turnero.logica.Turnos;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "TurnosSv", urlPatterns = {"/TurnosSv"})
public class TurnosSv extends HttpServlet {
    Controladora control = new Controladora();
    //List<Turnos> listaTurnos = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TurnosSv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TurnosSv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Turnos> resultado = new ArrayList<>();
        LocalDate fecha = LocalDate.parse(request.getParameter("filtro-fecha"));
        
        if(!request.getParameter("filtro-fecha").isEmpty()){
            if(request.getParameter("filtro-estado") != null){
                System.out.println("Se muestran todos los turnos de la fecha y estado indicados");
                resultado = control.buscarTurnosFecha(fecha).stream().filter(t -> t.getEstado().equalsIgnoreCase(request.getParameter("filtro-estado"))).toList();
            }else{
                System.out.println("Se muestran todos los turnos de la fecha indicada");
                resultado = control.buscarTurnosFecha(fecha);
            }
        }else
            System.out.println("No se muestra ningún listado, ya que no se han indicado los parámetros necesarios");
        
        request.setAttribute("resultadosTurnos",resultado);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String stringId = request.getParameter("id-turno");
        
        Turnos turno = new Turnos();
        
        if (stringId.equalsIgnoreCase("null")){
            Long idCiudadano = Long.valueOf(request.getParameter("id-ciudadano"));
            turno.setTramite(request.getParameter("tramite"));
            turno.setEstado(request.getParameter("estado"));
            turno.setFecha(LocalDate.parse(request.getParameter("fecha")));
            turno.setCiudadano(control.buscarCiudadano(idCiudadano));

            control.crearTurno(turno);

        }else{
            turno = control.buscarTurno(Long.valueOf(stringId));
            String actualizarEstado = request.getParameter("cambio-estado");
            
            if(actualizarEstado != null){
                turno.setEstado(request.getParameter("cambio-estado"));
                control.modificarTurno(turno);
            }else
                System.out.println("No se ha modificado el estado del turno, ya que no se ha indicado a qué estado se debe cambiar");
            
        }
        
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

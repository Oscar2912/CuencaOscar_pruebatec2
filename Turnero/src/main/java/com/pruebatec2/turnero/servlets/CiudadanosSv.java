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
 * Clase responsable de gestionar los datos que se vayan a cargar en la tabla ciudadanos, o los que se soliciten a la misma, por parte del usuario a través de formularios en la página web
 * @author Oscar
 */
@WebServlet(name = "CiudadanosSv", urlPatterns = {"/CiudadanosSv"})
public class CiudadanosSv extends HttpServlet {
    /**
     * Se crea una variable control que permitirá a esta clase utilizar los métodos de la clase Controladora
     */
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

    /**
     * Este método gestiona los datos que va a devolver la BD al usuario en función a la petición que realice desde los formularios del index.jsp con método get
     * Se comprobará si, al llamar al método desde el formulario, se pasa un dato válido por parámetro
     * Si no se pasa nada por parámetro, devolverá null, al igual que si el parámetro no se encuentra en la tabla ciudadanos
     * En caso de encontrarse en la tabla ciudadanos el parámetro que se pasa al llamar al método, devolverá el objeto Ciudadanos que corresponda
     * @param request registra los datos que se envíen por parámetro al realizar la solicitud de información desde el formulario
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
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

    /**
     * Este método gestiona los datos que se van a insertar en los registros de la tabla ciudadanos en la BD antes de enviarlos a la misma para evitar la creación de registros erróneos
     * Se creará un objeto Ciudadanos, en el que, si los datos introducidos en el formulario son correctos, se añadirán dichos datos, y posteriormente se creará el registro, devolviendo por último la URL a la que se enviará al ciudadano, en la que se indica el Id del registro creado en la tabla ciudadanos
     * En caso de detectarse que alguno de los campos está vacío, se devolverá al ciudadano a la página principal
     * @param request almacena los datos introducidos por parte del usuario en los formularios con método post en el index.jsp
     * @param response indica la dirección a la que se va a redirigir al usuario en función a los parámetros introducidos
     * @throws ServletException
     * @throws IOException 
     */
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

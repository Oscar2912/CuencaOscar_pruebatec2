package com.pruebatec2.turnero.servlets;

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
 * Clase responsable de gestionar los datos que se vayan a cargar en la tabla ciudadanos, o los que se soliciten a la misma, por parte del usuario a través de formularios en la página web
 */
@WebServlet(name = "TurnosSv", urlPatterns = {"/TurnosSv"})
public class TurnosSv extends HttpServlet {
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
            out.println("<title>Servlet TurnosSv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TurnosSv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Este método gestiona los datos que va a devolver la BD al usuario en función a la petición que realice desde los formularios del index.jsp con método get
     * Se comprobará si, al llamar al método desde el formulario, se pasa un dato válido por parámetro
     * Si no se pasa nada por parámetro, devolverá null, al igual que si los parámetros no se encuentran en la tabla turnos
     * En caso de encontrarse en la tabla turnos el parámetro "filtro-fecha", pero no "filtro-estado", devolverá el listado de objetos Turnos que correspondan con la fecha indicada
     * Si se encuentran en la tabla ambos parámetros, devolverá el listado de turnos filtrados por fecha y estado indicados en el formulario
     * @param request registra los datos que se envíen por parámetro al realizar la solicitud de información desde el formulario
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
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

    /**
     * Este método gestiona los datos que se van a insertar en los registros de la tabla turnos en la BD antes de enviarlos a la misma para evitar la creación de registros erróneos
     * Se creará un objeto Turnos, en el que, si no se indica el id del turno al llamar al método, se creará un turno nuevo con los datos indicados en el formulario
     * En caso de indicarse el id del turno, se almacenará el turno que corresponda al id indicado, solicitando los datos a la BD, para poder actualizar los datos del mismo
     * Si se indica en el formulario el estado por el que se desea modificar el turno en la BD, se actualizará el objeto Turnos con el parámetro indicado, y se actualizará en la BD
     * En caso de no indicar el estado por el que se desea modificar el registro de la BD, no se realizarán cambios
     * @param request almacena los datos introducidos por parte del usuario en los formularios con método post en el index.jsp
     * @param response indica la dirección a la que se va a redirigir al usuario
     * @throws ServletException
     * @throws IOException 
     */
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

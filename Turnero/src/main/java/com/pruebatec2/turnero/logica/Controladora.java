package com.pruebatec2.turnero.logica;

import com.pruebatec2.turnero.persistencia.ControladoraPersistencia;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase encargada de controlar las peticiones de información e inserciones de datos que se realizarán desde las clases que se encuentran en el paquete "servlets"
 * @author Oscar
 */

public class Controladora {
    ControladoraPersistencia controladora = new ControladoraPersistencia();
    
    /**
     * Método responsable de llamar al método que creará el ciudadano en la BD
     * @param ciudadano Objeto Ciudadanos necesario para crear el registro en la BD
     */
    public void crearCiudadano(Ciudadanos ciudadano){
        controladora.crearCiudadano(ciudadano);
    }
    
    /**
     * Método que se encarga de devolver una lista con los registros encontrados en la tabla ciudadanos de la BD
     * @return Devuelve la lista mencionada
     */
    public List<Ciudadanos> listarCiudadanos (){
        return controladora.listarCiudadanos();
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla ciudadanos a través de su Id
     * @param id Número requerido para buscar el registro
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Ciudadanos buscarCiudadano (Long id){
        return controladora.buscarCiudadano(id);
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla ciudadanos a través de su DNI
     * @param dni Conjunto de carácteres necesario para realizar la búsqueda
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Long buscarCiudadanoDni (String dni){
        return controladora.buscarCiudadanoDni(dni);
    }
    
    /**
     * Método responsable de llamar al método que creará el turno en la BD
     * @param turno Objeto Turnos necesario para generar el registro en la BD
     */
    public void crearTurno(Turnos turno){
        controladora.crearTurno(turno);
    }
    
    /**
     * Método que se encarga de devolver una lista con los registros encontrados en la tabla turnos de la BD
     * @return Devuelve la lista mencionada
     */
    public List<Turnos> listarTurnos (){
        return controladora.listarTurnos();
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla turnos a través de su Id
     * @param id Número requerido para buscar el registro
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Turnos buscarTurno (Long id){
        return controladora.buscarTurno(id);
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla turnos a través de su fecha
     * @param fecha Fecha necesaria para realizar la búsqueda
     * @return Devuelve el registro en forma de objeto Turnos
     */
    public List<Turnos> buscarTurnosFecha (LocalDate fecha){
        return controladora.buscarTurnosFecha(fecha);
    }
    
    /**
     * Método que permite modificar el registro de la tabla turnos de la BD que tenga el mismo id que el objeto Turnos que se pasa por parámetro al llamar al método
     * @param turno Objeto Turnos que almacenará el id que identificará el registro que se desea modificar de la tabla turnos de la BD, y el resto de datos que se desean modificar del mismo
     */
    public void modificarTurno (Turnos turno){
        controladora.modificarTurno(turno);
    }
}

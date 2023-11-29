package com.pruebatec2.turnero.persistencia;

import com.pruebatec2.turnero.logica.Ciudadanos;
import com.pruebatec2.turnero.logica.Turnos;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de controlar las peticiones de información e inserciones de datos que se realizarán desde la clase controladora del paquete "logica"
 * @author Oscar
 */

public class ControladoraPersistencia {
    CiudadanosJpaController ciudadanosJPA = new CiudadanosJpaController();
    TurnosJpaController turnosJPA = new TurnosJpaController();
    
    /**
     * Método responsable de llamar al método que creará el ciudadano en la BD
     * @param ciudadano Objeto Ciudadanos necesario para crear el registro en la BD
     */
    public void crearCiudadano(Ciudadanos ciudadano){
        ciudadanosJPA.create(ciudadano);
    }
    
    /**
     * Método que se encarga de devolver una lista con los registros encontrados en la tabla ciudadanos de la BD
     * @return Devuelve la lista mencionada
     */
    public List<Ciudadanos> listarCiudadanos (){
        return ciudadanosJPA.findCiudadanosEntities();
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla ciudadanos a través de su Id
     * @param id Número requerido para buscar el registro
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Ciudadanos buscarCiudadano (Long id){
        return ciudadanosJPA.findCiudadanos(id);
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla ciudadanos a través de su DNI
     * @param dni Conjunto de carácteres necesario para realizar la búsqueda
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Long buscarCiudadanoDni (String dni){
        return ciudadanosJPA.findCiudadanosEntities().stream().filter(c -> c.getDni().equalsIgnoreCase(dni)).toList().get(0).getId();
    }
    
    /**
     * Método responsable de llamar al método que creará el turno en la BD
     * @param turno Objeto Turnos necesario para generar el registro en la BD
     */
    public void crearTurno(Turnos turno){
        turnosJPA.create(turno);
    }
    
    /**
     * Método que se encarga de devolver una lista con los registros encontrados en la tabla turnos de la BD
     * @return Devuelve la lista mencionada
     */
    public List<Turnos> listarTurnos (){
        return turnosJPA.findTurnosEntities();
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla turnos a través de su Id
     * @param id Número requerido para buscar el registro
     * @return Devuelve el registro en forma de objeto Ciudadanos
     */
    public Turnos buscarTurno (Long id){
        return turnosJPA.findTurnos(id);
    }
    
    /**
     * Método que permite buscar un registro específico en la tabla turnos a través de su fecha
     * @param fecha Fecha necesaria para realizar la búsqueda
     * @return Devuelve el registro en forma de objeto Turnos
     */
    public List<Turnos> buscarTurnosFecha(LocalDate fecha){
        return turnosJPA.findTurnosEntities().stream().filter(t -> t.getFecha().equals(fecha)).toList();
    }
    
    /**
     * Método que permite modificar el registro de la tabla turnos de la BD que tenga el mismo id que el objeto Turnos que se pasa por parámetro al llamar al método
     * En caso de no poder modificarse el registro, devolverá una excepción indicando el error que ha ocurrido
     * @param turno Objeto Turnos que almacenará el id que identificará el registro que se desea modificar de la tabla turnos de la BD, y el resto de datos que se desean modificar del mismo
     */
    public void modificarTurno (Turnos turno){
        try{
            turnosJPA.edit(turno);
        }catch(Exception e){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

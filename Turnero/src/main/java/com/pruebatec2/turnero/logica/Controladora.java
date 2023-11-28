package com.pruebatec2.turnero.logica;

import com.pruebatec2.turnero.persistencia.ControladoraPersistencia;
import java.time.LocalDate;
import java.util.List;

public class Controladora {
    ControladoraPersistencia controladora = new ControladoraPersistencia();
    
    public void crearCiudadano(Ciudadanos ciudadano){
        controladora.crearCiudadano(ciudadano);
    }
    
    public List<Ciudadanos> listarCiudadanos (){
        return controladora.listarCiudadanos();
    }
    
    public Ciudadanos buscarCiudadano (Long id){
        return controladora.buscarCiudadano(id);
    }
    
    public Long buscarCiudadanoDni (String dni){
        return controladora.buscarCiudadanoDni(dni);
    }
    
    public void modificarCiudadano (Ciudadanos ciudadano){
        controladora.modificarCiudadano(ciudadano);
    }
    
    public void eliminarCiudadano (Long id){
        controladora.eliminarCiudadano(id);
    }
    
    public void crearTurno(Turnos turno){
        controladora.crearTurno(turno);
    }
    
    public List<Turnos> listarTurnos (){
        return controladora.listarTurnos();
    }
    
    public Turnos buscarTurno (Long id){
        return controladora.buscarTurno(id);
    }
    
    public List<Turnos> buscarTurnosFecha (LocalDate fecha){
        return controladora.buscarTurnosFecha(fecha);
    }
    
    public void modificarTurno (Turnos turno){
        controladora.modificarTurno(turno);
    }
    
    public void eliminarTurno (Long id){
        controladora.eliminarTurno(id);
    }
}

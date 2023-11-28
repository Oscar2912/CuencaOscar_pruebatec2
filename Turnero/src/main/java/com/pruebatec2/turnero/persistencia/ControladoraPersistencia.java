package com.pruebatec2.turnero.persistencia;

import com.pruebatec2.turnero.logica.Ciudadanos;
import com.pruebatec2.turnero.logica.Turnos;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    CiudadanosJpaController ciudadanosJPA = new CiudadanosJpaController();
    TurnosJpaController turnosJPA = new TurnosJpaController();
    
    public void crearCiudadano(Ciudadanos ciudadano){
        ciudadanosJPA.create(ciudadano);
    }
    
    public List<Ciudadanos> listarCiudadanos (){
        return ciudadanosJPA.findCiudadanosEntities();
    }
    
    public Ciudadanos buscarCiudadano (Long id){
        return ciudadanosJPA.findCiudadanos(id);
    }
    
    public Long buscarCiudadanoDni (String dni){
        return ciudadanosJPA.findCiudadanosEntities().stream().filter(c -> c.getDni().equalsIgnoreCase(dni)).toList().get(0).getId();
    }
    
    public void modificarCiudadano (Ciudadanos ciudadano){
        try{
            ciudadanosJPA.edit(ciudadano);
        }catch(Exception e){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void eliminarCiudadano (Long id){
        try{
            ciudadanosJPA.destroy(id);
        }catch(Exception e){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void crearTurno(Turnos turno){
        turnosJPA.create(turno);
    }
    
    public List<Turnos> listarTurnos (){
        return turnosJPA.findTurnosEntities();
    }
    
    public Turnos buscarTurno (Long id){
        return turnosJPA.findTurnos(id);
    }
    
    public List<Turnos> buscarTurnosFecha(LocalDate fecha){
        return turnosJPA.findTurnosEntities().stream().filter(t -> t.getFecha().equals(fecha)).toList();
    }
    
    public void modificarTurno (Turnos turno){
        try{
            turnosJPA.edit(turno);
        }catch(Exception e){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void eliminarTurno (Long id){
        try{
            turnosJPA.destroy(id);
        }catch(Exception e){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

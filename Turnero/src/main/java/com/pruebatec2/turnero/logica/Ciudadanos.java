package com.pruebatec2.turnero.logica;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ciudadanos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String dni;
    
    @OneToMany(mappedBy = "ciudadano")
    private List<Turnos> turnos;

    public Ciudadanos() {
    }

    public Ciudadanos(Long id, String nombre, String apellido, String dni, List<Turnos> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.turnos = turnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Turnos> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turnos> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "Ciudadanos{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + '}';
    }
    
}

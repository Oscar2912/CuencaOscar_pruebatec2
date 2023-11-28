package com.pruebatec2.turnero.logica;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Turnos implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tramite;
    private String estado;
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name = "ciudadano_id")
    private Ciudadanos ciudadano;

    public Turnos() {
    }

    public Turnos(Long id, String tramite, String estado, LocalDate fecha, Ciudadanos ciudadano) {
        this.id = id;
        this.tramite = tramite;
        this.estado = estado;
        this.fecha = fecha;
        this.ciudadano = ciudadano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Ciudadanos getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadanos ciudadano) {
        this.ciudadano = ciudadano;
    }
    
    

    @Override
    public String toString() {
        return "Turnos{" + "id=" + id + ", tramite=" + tramite + ", estado=" + estado + ", fecha=" + fecha + '}';
    }
    
    
}

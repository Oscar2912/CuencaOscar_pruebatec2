<%-- 
    Document   : index
    Created on : 23 nov 2023, 17:12:23
    Author     : Oscar
--%>

<%@page import="com.pruebatec2.turnero.logica.Turnos"%>
<%@page import="java.util.*"%>
<%@page import="com.pruebatec2.turnero.logica.Ciudadanos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Turnero</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2>Nuevo Turno</h2>
            <h3>Acceso de ciudadano</h3>
            <!-- Formulario que solicita el id del ciudadano -->
            <form action="CiudadanosSv" method="get">
                <div class="form-group">
                    <label for="id-ciudadano">Introduzca su Id de ciudadano:</label>
                    <input type="text" class="form-control" id="id-ciudadano" name="id-ciudadano">
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>    
            <hr>
            <!-- Se muestra la información del usuario-->
            <div class="results-table">
                <% if (request.getAttribute("resultadosCiudadanos") != null) { %>
                    <h3>Datos de usuario:</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>DNI</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% Ciudadanos ciudadanoFiltro = (Ciudadanos) request.getAttribute("resultadosCiudadanos"); %>
                                <td><%= ciudadanoFiltro.getId() %></td>
                                    <td><%= ciudadanoFiltro.getNombre() %></td>
                                    <td><%= ciudadanoFiltro.getApellido() %></td>
                                    <td><%= ciudadanoFiltro.getDni() %></td>
                                </tr>
                        </tbody>
                    </table>
                    <h2>Solicitud de turno</h2>
                    <form action="TurnosSv" method="post">
                        <input type="hidden" class="form-control" id="id-turno" name="id-turno" value=null>
                        <div class="form-group">
                            <label for="tramite">Indique el trámite: </label>
                            <input type="text" class="form-control" id="tramite" name="tramite">
                        </div>
                        <div class="form-group">
                            <label for="estado">Indique el estado del trámite: </label>
                            <br>
                            <input type="radio" id="espera" name="estado" value="En Espera">
                            <label for="espera">En Espera</label>
                            <br>
                            <input type="radio" id="atendido" name="estado" value="Ya Atendido">
                            <label for="atentido">Ya Atendido</label>
                        </div>
                        <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="date" class="form-control" id="fecha" name="fecha"><br>
                        </div>
                        <div class="form-group">
                            <label for="id-ciudadano">Id Ciudadano:</label>
                            <input type="text" class="form-control" id="id-ciudadano" name="id-ciudadano" value=<%= ciudadanoFiltro.getId() %> readonly>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                <% }else{ %>
            </div>
            <h3> Registro de ciudadano </h3>
            <form action="CiudadanosSv" method="post">
                <div class="form-group">
                    <label for="nombre-ciudadano">Nombre:</label>
                    <input type="text" class="form-control" id="nombre-ciudadano" name="nombre-ciudadano">
                </div>
                <div class="form-group">
                    <label for="apellido-ciudadano">Apellido:</label>
                    <input type="text" class="form-control" id="apellido-ciudadano" name="apellido-ciudadano">
                </div>
                <div class="form-group">
                    <label for="dni-ciudadano">DNI:</label>
                    <input type="text" class="form-control" id="dni-ciudadano" name="dni-ciudadano">
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
            <hr>
            <% } %>
            <br>
            <br>
            <form action="TurnosSv" method="get">
                <button type="submit" class="btn btn-primary">Mostrar Turnos</button>
            </form>
            <br>
            <br>
            <div class="results-table">
                <% if (request.getAttribute("resultadosTurnos") != null) { %>
                <h3>Información de los turnos registrados</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Nº de Turno</th>
                            <th>Trámite</th>
                            <th>Estado Actual</th>
                            <th>Fecha</th>
                            <th>Ciudadano</th>
                            <th>Cambio de Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Turnos turno : (List<Turnos>) request.getAttribute("resultadosTurnos")) { %>
                            <tr>
                                <td><%= turno.getId() %></td>
                                <td><%= turno.getTramite() %></td>
                                <td><%= turno.getEstado() %></td>
                                <td><%= turno.getFecha() %></td>
                                <td><%= turno.getCiudadano().getId() + " " + turno.getCiudadano().getNombre() + " " + turno.getCiudadano().getApellido() %></td>
                                <td>
                                    <form action="TurnosSv" method="post">
                                        <div class="form-group">
                                            <input type="hidden" class="form-control" id="id-turno" name="id-turno" value=<%= turno.getId() %> readonly>
                                        </div>                                        
                                        <div class="form-group">
                                            <input type="radio" id="espera" name="cambio-estado" value="En Espera">
                                            <label for="espera">En Espera</label>
                                            <br>
                                            <input type="radio" id="atendido" name="cambio-estado" value="Ya Atendido">
                                            <label for="atentido">Ya Atendido</label>
                                        </div>
                                        <button type="submit" class="btn btn-warning">Cambiar Estado</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% }%>
            </div>
            <h2>Filtrado de turnos</h2>
            <form action="TurnosSv" method="get">
                <div class="form-group">
                    <label for="filtro-fecha">Fecha del Turno:</label>
                    <input type="date" class="form-control" id="filtro-fecha" name="filtro-fecha"><br>
                </div>
                <div class="form-group">
                    <label for="filtro-estado">Indique el estado del trámite: </label>
                    <br>
                    <input type="radio" id="espera" name="filtro-estado" value="En Espera">
                    <label for="espera">En Espera</label>
                    <br>
                    <input type="radio" id="atendido" name="filtro-estado" value="Ya Atendido">
                    <label for="atentido">Ya Atendido</label>
                </div>
                <button type="submit" class="btn btn-primary">Mostrar Turnos</button>
            </form>
            <br>
           <!-- Scripts de Bootstrap -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>

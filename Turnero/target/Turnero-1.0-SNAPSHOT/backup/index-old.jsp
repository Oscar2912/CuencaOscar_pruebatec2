<%-- 
    Document   : index
    Created on : 23 nov 2023, 17:12:23
    Author     : Oscar
--%>

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
            <h3>Ciudadano</h3>
            <!-- Formulario que solicita el id del ciudadano -->
            <form action="CiudadanoSv" method="get">
                <div class="form-group">
                    <label for="id-ciudadano">Introduzca su Id de ciudadano: (si no está registrado, introduzca 0)</label>
                    <input type="text" class="form-control" id="id-ciudadano" name="id-ciudadano">
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>    
            <hr>
            <!-- Se muestra la información del usuario-->
            <div class="results-table">
                <% if (request.getAttribute("resultados") != null) { %>
                    <h3>Datos de usuario:</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>DNI</th>
                                <th>Turnos</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Ciudadanos ciudadano : (List<Ciudadanos>) request.getAttribute("resultados")) { %>
                                <tr>
                                    <td><%= ciudadano.getId() %></td>
                                    <td><%= ciudadano.getNombre() %></td>
                                    <td><%= ciudadano.getApellido() %></td>
                                    <td><%= ciudadano.getDni() %></td>
                                    <td>
                                        <!-- Se muestra la información de los turnos que ya tiene este usuario -->
                                        <% if (request.getAttribute("resultadosTurnos") != null) { %>
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Nº</th>
                                                        <th>Trámite</th>
                                                        <th>Estado</th>
                                                        <th>Fecha</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (Turnos turno : (List<Turnos>) request.getAttribute("resultados")) { %>
                                                        <tr>
                                                            <td><%= turno.getId() %></td>
                                                            <td><%= turno.getTramite() %></td>
                                                            <td><%= turno.getEstado() %></td>
                                                            <td><%= turno.getFecha() %></td>
                                                            <td>
                                                        </tr>
                                                    <% } %>
                                                </tbody>
                                            </table>
                                        <% }else{ %>
                                            <p>No tiene turnos asignados</p>
                                        <% } %>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                        
                    <!-- Formulario para registrar turnos con este usuario-->
                    <form action="CiudadanoSv" method="post">
                        <div class="form-group">
                            <label for="tramite">Indique el trámite: </label>
                            <input type="text" class="form-control" id="tramite" name="tramite">
                            <label for="descripcion">Indique el estado del trámite: </label>
                            <br>
                            <input type="radio" id="espera" name="estado" value="En Espera">
                            <label for="espera">En Espera</label>
                            <br>
                            <input type="radio" id="atendido" name="estado" value="Ya Atendido">
                            <label for="atentido">Ya Atendido</label>
                            <label for="fecha">Fechadel partido:</label>
                            <input type="date" class="form-control" id="fecha" name="fecha"><br>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                        
                <% }else{%>
                
                <!-- Registro de ciudadano-->
                    <form action="CiudadanoSv" method="post">
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
                <% } %>
            </div>
            
            <!-- Botón que muestra una tabla con todos los turnos y los ciudadanos que tienen asignados -->
            <form action="TurnoSv" method="get">
                <button type="submit" class="btn btn-success">Mostrar todos los turnos registrados</button>
            </form>
            <hr>
            <!-- Tabla que muestra todos los turnos -->
            <% if (request.getAttribute("resultado") != null) { %>
                <h3>Información los turnos registrados</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Nº de Turno</th>
                            <th>Trámite</th>
                            <th>Estado</th>
                            <th>Fecha</th>
                            <th>Ciudadano</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Turnos turno : (List<Turnos>) request.getAttribute("resultado2")) { %>
                            <tr>
                                <td><%= turno.getId() %></td>
                                <td><%= turno.getTramite() %></td>
                                <td><%= turno.getEstado() %></td>
                                <td><%= turno.getFecha() %></td>
                                <td><%= turno.getCiudadano() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% }%>
            
            <!-- Formulario que filtra los turnos por fecha -->
            <h3>Filtro de turnos por fecha</h3>
            <form action="TurnoSv" method="get">
                <div class="form-group">
                    <label for="fecha">Indique la fecha: </label>
                    <input type="date" class="form-control" id="fecha" name="fecha"><br>
                </div>
                <button type="submit" class="btn btn-primary">Buscar</button>
            </form>
            <br>
            <br>
            <% if (request.getAttribute("resultado") != null) { %>
                <!-- Formulario que filtra los turnos filtrados por fecha, por su estado-->
                <h3>Filtro de turnos por estado</h3>
                <form action="TurnoSv" method="get">
                    <div class="form-group">
                        <label for="descripcion">Indique el estado: </label>
                        <br>
                        <input type="radio" id="espera" name="estado" value="En Espera">
                        <label for="espera">En Espera</label>
                        <br>
                        <input type="radio" id="atendido" name="estado" value="Ya Atendido">
                        <label for="atentido">Ya Atendido</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Mostrar todos los turnos registrados</button>
                </form>
                <br>
                <br>
                <% if (request.getAttribute("resultado") != null) { %>
                    <!-- Tabla que muestra los turnos filtrados por fecha-->
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nº de Turno</th>
                                <th>Trámite</th>
                                <th>Estado</th>
                                <th>Fecha</th>
                                <th>Ciudadano</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Turnos turno : (List<Turnos>) request.getAttribute("resultado2")) { %>
                                <tr>
                                    <td><%= turno.getId() %></td>
                                    <td><%= turno.getTramite() %></td>
                                    <td><%= turno.getEstado() %></td>
                                    <td><%= turno.getFecha() %></td>
                                    <td><%= turno.getCiudadano() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% }else{%>
                    <h2>No existen turnos registrados en esta fecha con el estado indicado</h2>
                <% }%>
            <% }else{%>
            <h2>No existen turnos registrados en esta fecha</h2>
            <% }%>
        <!-- Scripts de Bootstrap -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>

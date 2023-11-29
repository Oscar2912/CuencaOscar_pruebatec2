<%-- 
    Document   : index
    Created on : 23 nov 2023, 17:12:23
    Author     : Oscar
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="com.pruebatec2.turnero.logica.Turnos"%>
<%@page import="java.util.*"%>
<%@page import="com.pruebatec2.turnero.logica.Ciudadanos"%>
<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Turnero - Sistema de registro de incidencias</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Sección del menú superior de navegación -->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <!-- Texto con enlace que lleva a la parte superior de la web -->
                <a class="navbar-brand" href="#page-top"><p>Sistema de incidencias</p></a>
                <!-- Botón que sustituye al menú al detectarse que el ancho de pantalla no es lo suficientemente grande como para que se muestre correctamente el contenido -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <!-- Menú -->
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#ciudadano">Ciudadano</a></li>
                        <li class="nav-item"><a class="nav-link" href="#turnos">Turnos</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="page-section" id="ciudadano">
            <h2>Acceso de ciudadano</h2>
            <!-- Formulario que solicita el id del ciudadano -->
            <form action="CiudadanosSv" method="get">
                <div class="form-group">
                    <label for="id-ciudadano">Introduzca su Id de ciudadano:</label>
                    <input type="text" class="form-control" id="id-ciudadano" name="id-ciudadano">
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>    
            <hr>
            <!-- Se muestra la información del usuario-->
            <div class="results-table">
                <% if (request.getAttribute("resultadosCiudadanos") != null) { %>
                <% Ciudadanos ciudadanoFiltro = (Ciudadanos) request.getAttribute("resultadosCiudadanos"); %>
                    <div id="liveAlertPlaceholder">
                        <div class="alert alert-success alert-dismissible" role="alert"> 
                            <div>Bienvenid@ <%= ciudadanoFiltro.getNombre() %></div><button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </div>
                    <!-- Tabla que muestra los datos del ciudadano -->
                    <h4>Datos de usuario:</h4>
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
                                <td><%= ciudadanoFiltro.getId() %></td>
                                    <td><%= ciudadanoFiltro.getNombre() %></td>
                                    <td><%= ciudadanoFiltro.getApellido() %></td>
                                    <td><%= ciudadanoFiltro.getDni() %></td>
                                </tr>
                        </tbody>
                    </table>
                    <!-- Tabla que permite la solicitud de un turno por el ciudadano -->
                    <h4>Solicitud de turno</h4>
                    <form action="TurnosSv" method="post">
                        <input type="hidden" class="form-control" id="id-turno" name="id-turno" value=null>
                        <div class="form-group">
                            <label for="tramite">Indique el trámite: </label>
                            <input type="text" class="form-control" id="tramite" name="tramite" required>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="estado">Indique el estado del trámite: </label>
                            <br>
                            <input type="radio" id="espera" name="estado" value="En Espera" required>
                            <label for="espera">En Espera</label>
                            <br>
                            <input type="radio" id="atendido" name="estado" value="Ya Atendido" required>
                            <label for="atentido">Ya Atendido</label>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="date" class="form-control" id="fecha" name="fecha" required><br>
                        </div>
                        <div class="form-group">
                            <label for="id-ciudadano">Id Ciudadano:</label>
                            <input type="text" class="form-control" id="id-ciudadano" name="id-ciudadano" value=<%= ciudadanoFiltro.getId() %> readonly>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                <% }else{ %>
            </div>
            <!-- Tabla que permite el registro del ciudadano en el sistema -->
            <h4> Registro de ciudadano </h4>
            <form action="CiudadanosSv" method="post">
                <div class="form-group">
                    <label for="nombre-ciudadano">Nombre:</label>
                    <input type="text" class="form-control" id="nombre-ciudadano" name="nombre-ciudadano" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="apellido-ciudadano">Apellido:</label>
                    <input type="text" class="form-control" id="apellido-ciudadano" name="apellido-ciudadano" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="dni-ciudadano">DNI:</label>
                    <input type="text" class="form-control" id="dni-ciudadano" name="dni-ciudadano" required>
                </div>
                <br>
                <button type="button" id="liveAlertBtn" hidden><button type="submit" class="btn btn-primary">Enviar</button></button>
            </form>
            <hr>
            <% } %>
        </section>
        <!-- Sección que muestra la información sobre los turnos -->
        <section class="page-section" id="turnos">
            <h2> Información sobre turnos </h2>
            <br>
            <br>
            <h4>Listado de turnos</h4>
            <!-- Formulario que solicita que se indique la fecha de los turnos ya creados que se desean mostrar -->
            <form action="TurnosSv" method="get">
                <div class="form-group">
                    <label for="filtro-fecha">Fecha del Turno:</label>
                    <input type="date" class="form-control" id="filtro-fecha" name="filtro-fecha" required><br>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Mostrar Turnos</button>
                <br>
                <br>
            </form>
            <!-- Apartado en el que se muestran los turnos filtrados -->
            <div class="results-table">
                <!-- Se comprueba que el resultado de los turnos solicitados no sea nulo -->
                <% if (request.getAttribute("resultadosTurnos") != null) { %>
                    <!-- Se crea una lista donde se almacenarán los turnos resultantes -->
                    <% List<Turnos> listaTurnos = (List<Turnos>) request.getAttribute("resultadosTurnos"); %>
                    <!-- Se comprueba que la lista no esté vacía -->
                    <% if (!listaTurnos.isEmpty()) { %>
                        <h4>Información de los turnos registrados</h4>
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
                                            <!-- Formulario dentro de la tabla con los turnos resultantes que permite el cambio de estado del turno seleccionado -->
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
                                                <button type="submit" class="btn btn-danger">Cambiar Estado</button>
                                            </form>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <br>
                        <!-- Se almacena la fecha indicada en el primer formulario en una variable para mostrarla en el segundo, en caso de que el usuario quiera filtrar los turnos por estado -->
                        <%LocalDate fecha = listaTurnos.get(0).getFecha();%>
                        <!-- Formulario que permite filtrar los turnos de la fecha indicada por estado -->
                        <h4>Filtrado de turnos</h4>
                        <form action="TurnosSv" method="get">
                            <div class="form-group">
                                <label for="filtro-fecha">Fecha del Turno:</label>
                                <input type="text" class="form-control" id="filtro-fecha" name="filtro-fecha"  value=<%= fecha %> readonly><br>
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
                            <br>
                            <button type="submit" class="btn btn-primary">Mostrar Turnos</button>
                        </form>
                    <% } %>
                <% } %>
            </div>
        </section>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

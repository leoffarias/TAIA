<%@ page
	import="repositorio.dados.*,
java.util.ArrayList,
java.util.List,
repositorio.dados.entidades.*"%>
<html class="homepage">
<head>
<title>TAIA</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../slick/slick.css" />
<link rel="stylesheet" type="text/css" href="../slick/slick-theme.css" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<nav></nav>
	<div class="container">
		<h2 class="tit">
			Bem vindo,
			<%
			out.println(session.getAttribute("nome"));
		%>
		</h2>
		<br />
		<h4>Recomendados para você</h4>
	</div>
	<div class="cont-rec">
		<div class="container">
			<div class="slide">
				<%
					Buscas buscas = new Buscas();
					Integer userid = (Integer) session.getAttribute("userid");
					List<Evento> eventos = buscas.getEventos(
							"SELECT e.id, e.nome_curto FROM evento e INNER JOIN usuarios_eventos u ON e.id = u.id_eve WHERE u.id_usu = "
									+ userid + " AND peso = 0 ORDER BY jaccard DESC LIMIT 3",
							"rec");
					for (Evento evento : eventos) {
				%>
				<div>
					<img class="img-ev" src="../evento/img/<%=evento.getId()%>" />
					<%
						out.println(evento.getNomeCurto() + " </div>");
						}
					Integer iduniv = (Integer) session.getAttribute("iduniv");
					List<Materia> materias = buscas.getMaterias(
							"SELECT e.id, e.nome_curto, e.id_univ FROM materia e INNER JOIN usuarios_materias u ON e.id = u.id_eve WHERE u.id_usu = "
									+ userid + " AND peso = 0 AND e.id_univ = "+iduniv+" ORDER BY jaccard DESC LIMIT 3",
							"rec");
					for (Materia materia : materias) {
					%>
					<div>
					<img class="img-ev" src="../univ/img/<%=materia.getIdUniv()%>" />
					<%
						out.println(materia.getNomeCurto() + " </div>");
						}
					List<Estagio> estagios = buscas.getEstagio(
							"SELECT e.id, e.funcao, e.empresa FROM estagio e INNER JOIN usuarios_estagio u ON e.id = u.id_eve WHERE u.id_usu = "
									+ userid + " AND peso = 0 ORDER BY jaccard DESC LIMIT 3",
							"rec");
					for (Estagio estagio : estagios) {					
					%>
										<div>
					<img class="img-ev" src="../estagio/img/<%=estagio.getId()%>" />
					<%
						out.println(estagio.getFuncao() + " - "+estagio.getEmpresa()+" </div>");
						}
					%>
				</div><!-- Slide -->				
			</div> 
		</div>

		<script type="text/javascript"
			src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript"
			src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="../slick/slick.min.js"></script>
		<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>
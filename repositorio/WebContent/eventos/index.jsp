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
	<nav>
		<a href="../home" class="voltar">< Voltar</a>
	</nav>
	<div class="container">
		<h2 class="tit">Eventos</h2>
		<br />
		<h4>Recomendados para você</h4>
	</div>
	<div class="cont-rec">
		<div class="container">
			<div class="slide">
				<%
					Buscas buscas = new Buscas();
					Integer userid = (Integer) session.getAttribute("userid");
					Integer idcurso = (Integer) session.getAttribute("idcurso");
					List<Evento> eventos = buscas.getEventos(
							"SELECT e.id, e.nome_curto FROM evento e INNER JOIN usuarios_eventos u ON e.id = u.id_eve WHERE u.id_usu = "
									+ userid + " AND peso = 0 ORDER BY jaccard DESC LIMIT 8",
							"rec");
					for (Evento evento : eventos) {
				%>
				<div>
					<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img
						class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 	out.println(evento.getNomeCurto() + "</a></div>");
 	}
 %>
				</div>
				<!-- Slide -->
			</div>
		</div>

		<br /> <br />
		<div class="container">
			<h4 style="text-align: left;">Usuários parecidos com você se
				interessam por:</h4>
				<div class="slide">
			<br />
			
			<%
				Aluno aluno = buscas.getAluno(
					"SELECT id FROM aluno a INNER JOIN metricas_usuarios e2 ON (a.id = e2.id_usu1 OR a.id = e2.id_usu2) WHERE (e2.id_usu1 = "+userid+" OR e2.id_usu2 = "+userid+") AND a.id != "+userid+" ORDER BY e2.aa DESC LIMIT 1",
					"grau");
			    int alunoid = aluno.getId();
				eventos = buscas.getEventos(
						//"SELECT id, nome_curto, tags FROM evento e INNER JOIN usuarios_eventos u ON e.id = u.id_eve INNER JOIN aluno a ON a.id = u.id_usu INNER JOIN metricas_usuarios e2 ON (a.id = e2.id_usu1 OR a.id = e2.id_usu2) WHERE (e2.id_usu1 = "+userid+" OR e2.id_usu2 = "+userid+") AND a.id != "+userid+" ORDER BY e2.aa DESC LIMIT 2) AND NOT EXISTS (SELECT 1 FROM usuarios_eventos WHERE id_usu = "+userid+" AND id_eve = e.id AND peso = 1 ) ",
						"SELECT id, nome_curto, tags FROM evento e INNER JOIN usuarios_eventos e2 ON e.id = e2.id_eve WHERE NOT EXISTS (SELECT 1 FROM usuarios_eventos WHERE id_usu = "+userid+" AND id_eve = e.id AND peso = 1 ) AND e2.id_usu = "+alunoid+" AND e2.peso = 1 ORDER BY e2.jaccard DESC LIMIT 8;",
						"grau");
				for (Evento evento : eventos) {
			%>
			
			<div>
				<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 				out.println(evento.getNomeCurto() + "</a></div>");
 				}
 			%>

		</div>
		</div>

		<br /> <br />
		<!-- <div class="container">
			<h4 style="text-align: left;">Eventos parecidos com x</h4>
			<br />

		</div>

		<br /> <br /> -->
		<div class="cont-rec">
		<div class="container">
			<h4 style="text-align: left;">Eventos mais populares</h4>
			<div class="slide">
			<br />

			<%
				eventos = buscas.getEventos(
						"SELECT id, nome_curto, tags FROM evento e INNER JOIN usuarios_eventos e2 ON e.id = e2.id_eve WHERE NOT EXISTS (SELECT 1 FROM usuarios_eventos WHERE id_usu = "+userid+" AND id_eve = e.id AND peso = 1 ) GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 8",
						"grau");
				for (Evento evento : eventos) {
			%>
			<div>
				<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 				out.println(evento.getNomeCurto() + "</a></div>");
 				}
 			%>
			</div>
			</div>
			</div>
			<br /> <br />
			<!--  <div class="container">
				<h4 style="text-align: left;">Quem se interessa por x, também
					se interessa por:</h4>
				<br />

			</div>

			<br /> <br />-->
			<div class="container">
				<h4 style="text-align: left;">Novidades</h4>
				<div class="slide">
			<br />

			<%
				eventos = buscas.getEventos(
						"SELECT id, nome_curto, tags FROM evento e WHERE NOT EXISTS (SELECT 1 FROM usuarios_eventos WHERE id_usu = "+userid+" AND id_eve = e.id AND peso = 1 ) ORDER BY e.id DESC LIMIT 8",
						"grau");
				for (Evento evento : eventos) {
			%>
			<div>
				<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 				out.println(evento.getNomeCurto() + "</a></div>");
 				}
 			%>
			</div>
			</div>

			<br /> <br />
			<div class="cont-rec">
			<div class="container">
				<h4 style="text-align: left;">Sugestões aleatórias</h4>
				<div class="slide">
			<br />

			<%
				eventos = buscas.getEventos(
						"SELECT id, nome_curto, tags FROM evento e WHERE NOT EXISTS (SELECT 1 FROM usuarios_eventos WHERE id_usu = "+userid+" AND id_eve = e.id AND peso = 1 ) ORDER BY RAND() DESC LIMIT 8",
						"grau");
				for (Evento evento : eventos) {
			%>
			<div>
				<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 				out.println(evento.getNomeCurto() + "</a></div>");
 				}
 			%>
			</div>
			</div>
			</div>
			<br /><br />

			<script type="text/javascript"
				src="//code.jquery.com/jquery-1.11.0.min.js"></script>
			<script type="text/javascript"
				src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
			<script type="text/javascript" src="../slick/slick.min.js"></script>
			<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>
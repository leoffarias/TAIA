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
			<%
				Buscas buscas = new Buscas();
				Integer userid = (Integer) session.getAttribute("userid");
				List<Evento> eventos = buscas.getEventos(
						"SELECT e.id, e.nome_curto FROM evento e INNER JOIN usuarios_eventos u ON e.id = u.id_eve WHERE u.id_usu = "
								+ userid + " AND peso != 1 ORDER BY jaccard DESC LIMIT 2",
						"rec");
				for (Evento evento : eventos) {
			%>
				<img src="../evento/img/<%=evento.getId()%>" />
			<%
				out.println(evento.getNomeCurto());
				}
			%>
		</div>
	</div>
</body>
</html>
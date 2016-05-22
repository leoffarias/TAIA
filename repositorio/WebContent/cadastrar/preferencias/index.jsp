<%@ page
	import="repositorio.dados.*,
java.util.ArrayList,
java.util.List,
repositorio.dados.entidades.*"%>

<html>
<head>
<title>TAIA</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="../../css/normalize.css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container" style="text-align: center; margin-top: 50px;">
		<h2 class="tit">Queremos te conhecer melhor!</h2>
		<h3 class="tit">Encontre eventos, matérias e oportunidades de
			estágio que interessam abaixo para que possamos construir o seu
			perfil!</h3>
		<hr class="star-primary">
		<br /> <br />

		<%
			Buscas buscas = new Buscas();

			List<Evento> eventos = buscas.getEventos(
					"SELECT id, nome_curto FROM evento e INNER JOIN (SELECT id_eve FROM usuarios_eventos) AS e2 ON e.id = e2.id_eve GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
					"grau");

			for (Evento evento : eventos) {
		%>
		<div class="col-sm-4">
			<img src="../../evento/img/<%=evento.getId()%>" width="200"
				height="200" />
			<p>
				<%
					out.println(evento.getNomeCurto());
				%>
			</p>
		</div>
		<%
			}

			List<Materia> materias = buscas.getMaterias(
					"SELECT id, nome_curto FROM materia e INNER JOIN (SELECT id_mat FROM usuarios_materias) AS e2 ON e.id = e2.id_mat GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
					"grau");

			for (Materia materia : materias) {
		%>
		<div class="col-sm-4">
			<img src="../../materia/img/<%=materia.getId()%>" width="200"
				height="200" /> <!-- TEM QUE AJEITAR -->
			<p>
				<%
					out.println(materia.getNomeCurto());
				%>
			</p>
		</div>
		<%
			}

			List<Estagio> estagios = buscas.getEstagio(
					"SELECT id, funcao FROM estagio e INNER JOIN (SELECT id_est FROM usuarios_estagio) AS e2 ON e.id = e2.id_est GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
					"grau");

			for (Estagio estagio : estagios) {
		%>
		<div class="col-sm-4">
			<img src="../../estagio/img/<%=estagio.getId()%>" width="200"
				height="200" />
			<p>
				<%
					out.println(estagio.getFuncao());
				%>
			</p>
		</div>
		<%
			}
		%>
	</div>
</body>

</html>
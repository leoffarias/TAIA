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
		<div class="linha" style="margin-top:5px;">
		<h3 class="tit">Eventos</h3><br />
		<%
			Buscas buscas = new Buscas();

			List<Evento> eventos = buscas.getEventos(
					"SELECT id, nome_curto, tags FROM evento e INNER JOIN (SELECT id_eve FROM usuarios_eventos) AS e2 ON e.id = e2.id_eve GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
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
			<form action="../cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=evento.getId()%>"> 
				<input type="hidden" value="usuarios_eventos" name="tipo">
				<input type="hidden" value="<%=evento.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse" onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
			</div>
		<%
			}
			
			out.println("</div> <div class='linha'><h3 class='tit'>Matérias</h3><br />");

			List<Materia> materias = buscas.getMaterias(
					"SELECT id, nome_curto, tags, id_univ FROM materia e INNER JOIN (SELECT id_eve FROM usuarios_materias) AS e2 ON e.id = e2.id_eve GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
					"grau");

			for (Materia materia : materias) {
		%>
		<div class="col-sm-4">
			<img src="../../univ/img/<%=materia.getIdUniv()%>" width="200"
				height="200" />
			<!-- TEM QUE AJEITAR -->
			<p>
				<%
					out.println(materia.getNomeCurto());
				%>
			</p>
			<form action="../cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=materia.getId()%>"> 
				<input type="hidden" value="usuarios_materias" name="tipo">
				<input type="hidden" value="<%=materia.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse" onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
		</div>

		<%
			}
			
			out.println("</div> <div class='linha'><h3 class='tit'>Estágio</h3><br />");

			List<Estagio> estagios = buscas.getEstagio(
					"SELECT id, funcao, tags FROM estagio e INNER JOIN (SELECT id_eve FROM usuarios_estagio) AS e2 ON e.id = e2.id_eve GROUP BY e.id ORDER BY COUNT(*) DESC LIMIT 6",
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
			<form action="../cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=estagio.getId()%>"> 
				<input type="hidden" value="usuarios_estagio" name="tipo"> 
				<input type="hidden" value="<%=estagio.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse" onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
		</div>
		<%
			}
		%>
	 </div>
	</div>
</body>

</html>
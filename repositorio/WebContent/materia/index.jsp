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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
	<nav><a href="../home" class="voltar">< Voltar</a></nav>
	<%
		Buscas buscas = new Buscas();
		Integer userid = (Integer) session.getAttribute("userid");
		int eid = Integer.parseInt(request.getParameter("id"));
		List<Materia> materias = buscas.getMaterias(
				"SELECT m.nome, m.codigo, m.centro, m.id_univ, m.id_curso, m.tags FROM materia m WHERE m.id = " + eid + ";",
				"detalhes");
	%>
	<div class="cont-rec">
		<div class="container">
			<%
				for (Materia materia : materias) {
					out.println("<h2 class='tit'>" + materia.getNome() + "</h2>");
					out.println("<h4 class='tit'>" + materia.getCodigo() + "</h4>");
					Curso curso = buscas.getCurso("SELECT nome FROM curso WHERE id = "+materia.getIdCurso()+";");
					out.println("<br /><i class='fa fa-graduation-cap' aria-hidden='true'></i> " + curso.getNome());
					Universidade univ = buscas.getUniv("SELECT nome FROM universidades WHERE id = "+materia.getIdUniv()+";");
					out.println("<br /><i class='fa fa-university' aria-hidden='true'></i> " + univ.getNome());

					out.println("<br /><br />" + materia.getCentro());
			%><br /><br />
			<form action="../cadastrar/cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=eid%>">
				<input type="hidden" value="usuarios_materias" name="tipo">
				<input type="hidden" value="<%=materia.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse"
					onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
			<% } %>
		</div>
	</div>
	
		<br /><br />
	<div class="container">
	<h4>Matérias Similares</h2><br />
		<div class="slide">
			<%
				List<Materia> similares = buscas.getMaterias(
						"SELECT e.id, e.nome_curto, e.id_univ FROM materia e INNER JOIN metricas_materia u ON e.id = u.id_evento1 OR e.id = u.id_evento2 INNER JOIN usuarios_materias ue ON e.id = ue.id_eve WHERE e.id != "
								+ eid + " AND ue.peso = 0 AND ue.id_usu = "+userid+" AND (u.id_evento1 = "+eid+" OR u.id_evento2 = "+eid+") ORDER BY u.jaccard DESC LIMIT 8",
						"rec");
				for (Materia materia : similares) {
			%>
			<div>
				<a href="../materia/index.jsp?id=<%=materia.getId()%>"> <img
					class="img-ev" src="../univ/img/<%=materia.getIdUniv()%>" /> <%
 			out.println(materia.getNomeCurto() + "</a></div>");
 			}
			 %>
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
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
	<nav></nav>
	<%
		Buscas buscas = new Buscas();
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
</body>

</html>
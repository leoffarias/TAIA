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
		List<Estagio> estagios = buscas.getEstagio(
				"SELECT e.funcao, e.empresa, e.descricao, e.id_curso, e.site, e.tags FROM estagio e WHERE e.id = " + eid + ";",
				"detalhes");
	%>
	<div class="cont-rec">
		<div class="container">
			<%
				for (Estagio estagio : estagios) {
					out.println("<h2 class='tit'>" + estagio.getFuncao() + "</h2>");
					out.println("<h4 class='tit'>" + estagio.getEmpresa() + "</h4>");
					Curso curso = buscas.getCurso("SELECT nome FROM curso WHERE id = "+estagio.getIdCurso()+";");
					out.println("<br /><i class='fa fa-graduation-cap' aria-hidden='true'></i> " + curso.getNome());
					out.println("<br /><i class='fa fa-safari' aria-hidden='true'></i> " + estagio.getSite());

					out.println("<br /><br />" + estagio.getDescricao());
			%><br /><br />
			<form action="../cadastrar/cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=eid%>">
				<input type="hidden" value="usuarios_estagio" name="tipo">
				<input type="hidden" value="<%=estagio.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse"
					onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
			<% } %>
		</div>
	</div>
</body>

</html>
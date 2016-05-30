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
	
			<br /><br />
	<div class="container">
	<h4>Ofertas de Estágio Similares</h2><br />
		<div class="slide">
			<%
				List<Estagio> similares = buscas.getEstagio(
						"SELECT e.id, e.funcao, e.empresa FROM estagio e INNER JOIN metricas_estagio u ON e.id = u.id_evento1 OR e.id = u.id_evento2 INNER JOIN usuarios_estagio ue ON e.id = ue.id_eve WHERE e.id != "
								+ eid + " AND ue.peso = 0 AND ue.id_usu = "+userid+" AND (u.id_evento1 = "+eid+" OR u.id_evento2 = "+eid+") ORDER BY u.jaccard DESC LIMIT 8",
						"rec");
				for (Estagio estagio : similares) {
			%>
			<div>
				<a href="../estagio/index.jsp?id=<%=estagio.getId()%>"> <img
					class="img-ev" src="../estagio/img/<%=estagio.getId()%>" /> <%
 			out.println(estagio.getFuncao() + " - "+estagio.getEmpresa()+" </a></div>");
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
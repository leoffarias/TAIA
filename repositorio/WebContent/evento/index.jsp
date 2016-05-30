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
		List<Evento> eventos = buscas.getEventos(
				"SELECT id, nome, endereco, descricao, tipo, dia, hora, tags FROM evento WHERE id = " + eid + ";",
				"detalhes");
	%>
	<div class="cont-rec">
		<div class="container">
			<%
				for (Evento evento : eventos) {
					out.println("<h2 class='tit'>" + evento.getNome() + "</h2>");
					out.println("<h4 class='tit'>" + evento.getTipo() + "</h4><br />");
					String data = evento.getDia() + "";
					String ano = data.substring(0, 4);
					String mes = data.substring(4, 6);
					String dia = data.substring(6);
					out.println("<i class='fa fa-calendar' aria-hidden='true'></i> " + dia + "/" + mes + "/" + ano);
					out.println("<br /><i class='fa fa-clock-o' aria-hidden='true'></i> " + evento.getHora());
					out.println("<br /><i class='fa fa-map-marker' aria-hidden='true'></i> " + evento.getEndereco());
					out.println("<br /><br />" + evento.getDescricao());
			%><br /> <br />
			<form action="../cadastrar/cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=eid%>"> <input
					type="hidden" value="usuarios_eventos" name="tipo"> <input
					type="hidden" value="<%=evento.getTags()%>" name="tags"> <input
					class="btn btn-success" type="submit" value="Tenho interesse"
					onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
			<%
				}
			%>
		</div>
	</div>
	<br /><br />
	<div class="container">
	<h4>Eventos Similares</h2><br />
		<div class="slide">
			<%
				List<Evento> similares = buscas.getEventos(
						"SELECT e.id, e.nome_curto FROM evento e INNER JOIN metricas_eventos u ON e.id = u.id_evento1 OR e.id = u.id_evento2 INNER JOIN usuarios_eventos ue ON e.id = ue.id_eve WHERE e.id != "
								+ eid + " AND ue.peso = 0 AND ue.id_usu = "+userid+" AND (u.id_evento1 = "+eid+" OR u.id_evento2 = "+eid+") ORDER BY u.jaccard DESC LIMIT 8",
						"rec");
				for (Evento evento : similares) {
			%>
			<div>
				<a href="../evento/index.jsp?id=<%=evento.getId()%>"> <img
					class="img-ev" src="../evento/img/<%=evento.getId()%>" /> <%
 			out.println(evento.getNomeCurto() + "</a></div>");
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
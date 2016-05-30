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
			%><br /><br />
			<form action="../cadastrar/cadastraPreferencias" method="POST">
				<input type="hidden" name="evid" value="<%=eid%>">
				<input type="hidden" value="usuarios_eventos" name="tipo">
				<input type="hidden" value="<%=evento.getTags()%>" name="tags"> 
				<input class="btn btn-success" type="submit" value="Tenho interesse"
					onClick="this.form.submit(); this.disabled=true; this.value='Interessado'; ">
			</form>
			<% } %>
		</div>
	</div>
</body>

</html>
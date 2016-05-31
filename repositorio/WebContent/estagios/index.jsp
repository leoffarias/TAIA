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
	<nav><a href="../home" class="voltar">< Voltar</a></nav>
	<div class="container">
		<h2 class="tit">
			Ofertas de estágio
		</h2>
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
					List<Estagio> estagios = buscas.getEstagio(
							"SELECT e.id, e.funcao, e.empresa FROM estagio e INNER JOIN usuarios_estagio u ON e.id = u.id_eve WHERE u.id_usu = "
									+ userid + " AND peso = 0 AND e.id_curso = "+idcurso+" ORDER BY jaccard DESC LIMIT 8",
							"rec");
					for (Estagio estagio : estagios) {	
				%>
					<div>
					<a href="../estagio/index.jsp?id=<%=estagio.getId()%>">
					<img class="img-ev" src="../estagio/img/<%=estagio.getId()%>" />
					<%
						out.println(estagio.getFuncao() + " - "+estagio.getEmpresa()+" </a></div>");
						}
					%>
				</div><!-- Slide -->				
			</div> 
		</div>
		<br /><br />
		<div class="container">
		<h4 style="text-align:left;">O que você procura?</h4><br />

		</div>

		<script type="text/javascript"
			src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript"
			src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="../slick/slick.min.js"></script>
		<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>
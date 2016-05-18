<%@ page import="repositorio.dados.*,
java.util.ArrayList,
java.util.List,
repositorio.dados.entidades.*"%>

<html>
<head>
<title>TAIA</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/normalize.css">
</head>
<body>
	<div class="container" style="margin-top: 50px;">
		<h1 style="text-align: center;">Cadastrar materia</h1>
		<form action="adicionaMaterias" method="POST" class="form-horizontal">
			<div class="form-group">
				<label for="inputNome" class="col-sm-2 control-label">Nome</label>
				<div class="col-sm-10">
					<input type="text" name="nome" id="inputNome" class="form-control"
						placeholder="Nome">
				</div>
			</div>
			<div class="form-group">
				<label for="inputNomeCurto" class="col-sm-2 control-label">Nome
					Curto</label>
				<div class="col-sm-10">
					<input type="text" name="nomeCurto" id="inputNomeCurto"
						class="form-control" placeholder="Nome Curto">
				</div>
			</div>
			<div class="form-group">
				<label for="inputCódigo" class="col-sm-2 control-label">Código</label>
				<div class="col-sm-10">
					<input type="text" name="codigo" class="form-control"
						id="inputCódigo" placeholder="Código (Ex. IF762)">
				</div>
			</div>

			<div class="form-group">
				<label for="inputCentro" class="col-sm-2 control-label">Centro</label>
				<div class="col-sm-10">
					<input type="text" name="centro" id="inputCentro"
						class="form-control" placeholder="Centro (Ex. CIn)">
				</div>
			</div>
			<div class="form-group">
				<label for="inputUnivs" class="col-sm-2 control-label">Universidade</label>
				<div class="col-sm-10">
					<select name="idUniversidade" id="inputUnivs" class="form-control">
						<%
							Dao dao = new Dao();
							List<Universidade> univs = dao.getUnivs();
							for (Universidade univ : univs) {
						%>
						<option value="<%=univ.getId()%>"><%=univ.getNome()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputCurso" class="col-sm-2 control-label">Curso</label>
				<div class="col-sm-10">
					<select name="idCurso" id="inputCurso" class="form-control">
						<%
							dao = new Dao();
							List<Curso> cursos = dao.getCursos();
							for (Curso curso : cursos) {
						%>
						<option value="<%=curso.getId()%>"><%=curso.getNome()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputAreas" class="col-sm-2 control-label">Área</label>
				<div class="col-sm-10">
					<select name="idArea" id="inputAreas" class="form-control">
						<%
							dao = new Dao();
							List<Area> areas = dao.getAreas();
							for (Area area : areas) {
						%>
						<option value="<%=area.getId()%>"><%=area.getNome()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputTags" class="col-sm-2 control-label">Tags</label>
				<div class="col-sm-10">
					<input type="text" name="tags" id="inputTags" class="form-control"
						placeholder="Tags no formato: #t1 #t2 #t3...">
				</div>
			</div>
			<br />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-success" value="Cadastrar">
				</div>
			</div>
		</form>
	</div>
</body>

</html>
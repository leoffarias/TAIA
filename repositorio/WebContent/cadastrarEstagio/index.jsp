<%@ page
	import="repositorio.dados.*,
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
		<h1 style="text-align: center;">Cadastrar est�gio</h1>
		<form enctype="multipart/form-data" action="adicionaEstagios" method="POST" class="form-horizontal">
			<div class="form-group">
				<label for="inputFuncao" class="col-sm-2 control-label">Fun��o</label>
				<div class="col-sm-10">
					<input type="text" name="funcao" id="inputFuncao"
						class="form-control" placeholder="Fun��o">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmpresa" class="col-sm-2 control-label">Empresa</label>
				<div class="col-sm-10">
					<input type="text" name="empresa" class="form-control"
						id="inputEmpresa" placeholder="Empresa">
				</div>
			</div>

			<div class="form-group">
				<label for="inputDescricao" class="col-sm-2 control-label">Descri��o</label>
				<div class="col-sm-10">
					<input type="text" name="descricao" id="inputDescricao"
						class="form-control" placeholder="Descri��o">
				</div>
			</div>
			<div class="form-group">
				<label for="inputSite" class="col-sm-2 control-label">Site</label>
				<div class="col-sm-10">
					<input type="text" name="site" id="inputSite" class="form-control"
						placeholder="Link com mais informa��es">
				</div>
			</div>
			<div class="form-group">
				<label for="inputCurso" class="col-sm-2 control-label">Curso</label>
				<div class="col-sm-10">
					<select name="idCurso" id="inputCurso" class="form-control">
						<%
							Dao dao = new Dao();
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
				<label for="inputAreas" class="col-sm-2 control-label">�rea</label>
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
			<div class="form-group">
				<label for="inputFoto" class="col-sm-2 control-label">Foto</label>
				<div class="col-sm-10">
					<input style="margin-top: 10px;" type="file" name="foto"
						 required />
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
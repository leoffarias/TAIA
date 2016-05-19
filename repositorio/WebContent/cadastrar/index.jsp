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
		<h1 style="text-align: center;">Cadastrar</h1>
		<form enctype="multipart/form-data" action="adicionaAlunos" method="POST" class="form-horizontal">
			<div class="form-group">
				<label for="inputNome" class="col-sm-2 control-label">Nome</label>
				<div class="col-sm-10">
					<input type="text" name="nome" id="inputNome" class="form-control"
						placeholder="Nome">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-sm-2 control-label">E-mail</label>
				<div class="col-sm-10">
					<input type="email" name="email" id="inputEmail"
						class="form-control" placeholder="E-mail">
				</div>
			</div>

			<div class="form-group">
				<label for="inputSenha" class="col-sm-2 control-label">Senha</label>
				<div class="col-sm-10">
					<input type="password" name="senha" id="inputSenha"
						class="form-control" placeholder="Senha">
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
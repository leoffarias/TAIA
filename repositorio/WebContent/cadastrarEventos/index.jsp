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
		<h1 style="text-align: center;">Cadastrar evento</h1>
		<form enctype="multipart/form-data" action="adicionaEventos"
			method="POST" class="form-horizontal">
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
				<label for="inputEndereco" class="col-sm-2 control-label">Endereço</label>
				<div class="col-sm-10">
					<input type="text" name="endereco" class="form-control"
						id="inputEndereco" placeholder="Endereço">
				</div>
			</div>

			<div class="form-group">
				<label for="inputDescricao" class="col-sm-2 control-label">Descrição</label>
				<div class="col-sm-10">
					<input type="text" name="descricao" id="inputDescricao"
						class="form-control" placeholder="Descrição">
				</div>
			</div>
			<div class="form-group">
				<label for="inputTipo" class="col-sm-2 control-label">Tipo
					de Evento</label>
				<div class="col-sm-10">
					<input type="text" name="tipo" id="inputTipo" class="form-control"
						placeholder="Tipo de Evento (ex. Palestra)">
				</div>
			</div>
			<div class="form-group">
				<label for="inputDia" class="col-sm-2 control-label">Dia</label>
				<div class="col-sm-2">
					<input type="text" name="dia" id="inputDia" class="form-control"
						placeholder="DD">
				</div>
				<label for="inputMes" class="col-sm-2 control-label">Mês</label>
				<div class="col-sm-2">
					<input type="text" name="mes" id="inputMes" class="form-control"
						placeholder="MM">
				</div>
				<label for="inputAno" class="col-sm-2 control-label">Ano</label>
				<div class="col-sm-2">
					<input type="text" name="ano" id="inputAno" class="form-control"
						placeholder="AAAA">
				</div>
			</div>
			<div class="form-group">
				<label for="inputHora" class="col-sm-2 control-label">Hora</label>
				<div class="col-sm-10">
					<input type="text" name="hora" id="inputHora" class="form-control"
						placeholder="HH:MM (com os dois pontos)">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAreas" class="col-sm-2 control-label">Área</label>
				<div class="col-sm-10">
					<select name="idArea" id="inputAreas" class="form-control">
						<%
							Dao dao = new Dao();
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
						placeholder="#t1 #t2 #t3...">
				</div>
			</div>
			<div class="form-group">
				<label for="inputFoto" class="col-sm-2 control-label">Foto</label>
				<div class="col-sm-10">
					<input style="margin-top: 10px;" type="file" name="foto"
						placeholder="Upload Your Image" required />
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
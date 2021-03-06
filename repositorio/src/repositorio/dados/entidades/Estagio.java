package repositorio.dados.entidades;

import java.io.InputStream;

public class Estagio {

		private String funcao;
		private int idCurso;
		private String descricao;
		private String empresa;
		private int idArea;
		private String site;
		private InputStream foto;
		private String tags;
		private int id;
		
		public String getFuncao() {
			return funcao;
		}
		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}
		public int getIdCurso() {
			return idCurso;
		}
		public void setIdCurso(int idCurso) {
			this.idCurso = idCurso;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getEmpresa() {
			return empresa;
		}
		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}
		public int getIdArea() {
			return idArea;
		}
		public void setIdArea(int idArea) {
			this.idArea = idArea;
		}
		public String getSite() {
			return site;
		}
		public void setSite(String site) {
			this.site = site;
		}
		public InputStream getFoto() {
			return foto;
		}
		public void setFoto(InputStream inputStream) {
			this.foto = inputStream;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
}

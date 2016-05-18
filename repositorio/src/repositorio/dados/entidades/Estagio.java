package repositorio.dados.entidades;

public class Estagio {

		private String funcao;
		private int idCurso;
		private String descricao;
		private String empresa;
		private int idArea;
		private String site;
		private String foto;
		private String tags;
		
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
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
}

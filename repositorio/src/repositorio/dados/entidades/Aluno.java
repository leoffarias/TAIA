package repositorio.dados.entidades;

public class Aluno {

		private String nome;
		private int idUniv;
		private int idCurso;
		private String foto;
		private String tags;
		private String email;
		private String senha;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getIdUniv() {
			return idUniv;
		}
		public void setIdUniv(int idUniv) {
			this.idUniv = idUniv;
		}
		public int getIdCurso() {
			return idCurso;
		}
		public void setIdCurso(int idCurso) {
			this.idCurso = idCurso;
		}
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}

}
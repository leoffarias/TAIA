package repositorio.dados.entidades;

public class Universidade {

		private String nome;
		private String nomeCurto;
		private String foto;
		private int id;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getNomeCurto() {
			return nomeCurto;
		}
		public void setNomeCurto(String nomeCurto) {
			this.nomeCurto = nomeCurto;
		}
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}
		
}
package repositorio.dados.entidades;

import java.io.InputStream;

public class Area {

		private String nome;
		private InputStream foto;
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
		public InputStream getFoto() {
			return foto;
		}
		public void setFoto(InputStream inputStream) {
			this.foto = inputStream;
		}
		
}
package repositorio.obj;

import java.util.Calendar;

public class Evento {

		
		private String nome;
		private String endereco;
		private String descricao;
		
		public Evento(String nome, String endereco, String descricao) {
			this.nome = nome;
			this.endereco = endereco;
			this.descricao = descricao;
	}
		
		public Evento() {			
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

}

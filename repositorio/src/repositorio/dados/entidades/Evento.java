package repositorio.dados.entidades;

import java.io.InputStream;
import java.util.Calendar;

public class Evento {

		private String nome;
		private String endereco;
		private String descricao;
		private String nomeCurto;
		private String tipo;
		private int idArea;
		private int dia;
		private String hora;
		private InputStream foto;
		private String tags;
		private int id;

		public String getTags() {
			return tags;
		}
		
		public int getId() {
			return id;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}

		public String getNomeCurto() {
			return nomeCurto;
		}

		public void setNomeCurto(String nomeCurto) {
			this.nomeCurto = nomeCurto;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public int getIdArea() {
			return idArea;
		}

		public void setIdArea(int idArea) {
			this.idArea = idArea;
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public String getHora() {
			return hora;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public InputStream getFoto() {
			return foto;
		}

		public void setFoto(InputStream inputStream) {
			this.foto = inputStream;
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

		public void setId(int id) {
			this.id = id;
		}

}

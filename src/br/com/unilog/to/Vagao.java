package br.com.unilog.to;

public class Vagao {
	private String tipo;
	private String numero;
	private String owner;
	private Nota nota;

	public Vagao(String tipo, String numero, String owner, Nota nota) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.owner = owner;
		this.nota = nota;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
}

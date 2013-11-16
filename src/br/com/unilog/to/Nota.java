package br.com.unilog.to;

import java.util.Date;

import br.com.unilog.enuns.TipoNota;

public class Nota {

	private TipoNota tipoNota;
	private String serie;
	private String numero;
	private Date date;
	private Double valor;
	private Integer peso;
	private String nfeKey;
	
	public Nota(TipoNota tipoNota, String serie, String numero, Date date,
			Double valor, Integer peso, String nfeKey) {
		super();
		this.tipoNota = tipoNota;
		this.serie = serie;
		this.numero = numero;
		this.date = date;
		this.valor = valor;
		this.peso = peso;
		this.nfeKey = nfeKey;
	}	

	public TipoNota getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getNfeKey() {
		return nfeKey;
	}

	public void setNfeKey(String nfeKey) {
		this.nfeKey = nfeKey;
	}
	
	@Override
	public String toString() {
		return serie+"-"+numero;
	}
}

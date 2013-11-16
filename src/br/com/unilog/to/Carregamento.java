package br.com.unilog.to;

import java.util.Date;
import java.util.List;

public class Carregamento {
	
	private String tipoOperacao;
	private String host;
	private String usuario;
	private String senha;
	private String serie;
	private String numero;
	private String fluxo;
	private Date dataCriacao;
	private Integer peso;
	private Boolean unico;
	private Integer atrelados;
	private List<Nota> notas;
	private List<Vagao> vagoes;

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

	public String getFluxo() {
		return fluxo;
	}

	public void setFluxo(String fluxo) {
		this.fluxo = fluxo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Boolean getUnico() {
		return unico;
	}

	public void setUnico(Boolean unico) {
		this.unico = unico;
	}

	public Integer getAtrelados() {
		return atrelados;
	}

	public void setAtrelados(Integer atrelados) {
		this.atrelados = atrelados;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Vagao> getVagoes() {
		return vagoes;
	}

	public void setVagoes(List<Vagao> vagoes) {
		this.vagoes = vagoes;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
}

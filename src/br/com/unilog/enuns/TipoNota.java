package br.com.unilog.enuns;

public enum TipoNota {
	NFE("Nota Fiscal Eletronica"), NFP("Nota Fiscal Papel"), NFO("Nota Fiscal Outros Documentos");
	
	private String descr;

	private TipoNota(String descr) {
		this.descr = descr;
	}
	
	@Override
	public String toString() {
		return descr;
	}
}

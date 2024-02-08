package br.com.fintech.system.enumerator;

public enum TipoTelefone {
	
	COMERCIAL(1, "comercial"),
	RESIDENCIAL(2, "residencial"),
	CELULAR(3, "pessoal/celular");
	
	private int id;
	private String tipo;
	
	TipoTelefone(int id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public static TipoTelefone fromId(final int id) {
		for (final TipoTelefone tipoTelefone : TipoTelefone.values()) {
			if (tipoTelefone.id == id) {
				return tipoTelefone;
			}
		}
		return null;
	}

	public int getId() {
		return this.id;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}

package br.com.fintech.system.model;

import br.com.fintech.system.enumerator.TipoTelefone;

public class Usuario {

	private int idUsuario;
	private String nomeUsuario;
	private String nomeSocial;
	private String cpf;
	private TipoTelefone tipoTelefone;
	private String telefone;
	private Login login;
	private Endereco endereco;

	public Usuario() {
	}

	public Usuario(String nomeUsuario, String nomeSocial, String cpf, TipoTelefone tipoTelefone, String telefone,
			Login login, Endereco endereco) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.nomeSocial = nomeSocial;
		this.cpf = cpf;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.login = login;
		this.endereco = endereco;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

};

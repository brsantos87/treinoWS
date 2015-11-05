package br.com.bssistem.treinows.dominio.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;
import br.com.bssistem.treinows.dominio.tipo.TipoConta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_USUARIO")
@XmlRootElement(name = "usuario")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties({"identificador","descricao"})
public class Usuario implements Entidade {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "nome")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="urlImagem")
	private String picture;
	
	@Id
	@Column(name="tipoConta")
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;

	@Override
	public Serializable getIdentificador() {
		return id;
	}

	@Override
	public String getDescricao() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	
}

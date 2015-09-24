package br.com.bssistem.treinows.dominio.entidade;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.bssistem.infra.arquitetura.entidade.Entidade;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_USUARIO")
@XmlRootElement(name="usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Entidade {

	
	
	@Id
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Override
	public Serializable getIdentificador() {
		return id;
	}

	@Override
	public String getDescricao() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

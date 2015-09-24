package br.com.bssistem.infra.arquitetura.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.bssistem.treinows.dominio.entidade.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@JsonRootName(value="root")
@JsonSerialize()
@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericCollection implements Serializable{

	@XmlElement(name="element")
	@JsonProperty(value="usuarios")
	private Collection<Usuario> lista;

	public Collection<Usuario> getLista() {
		if(lista == null)
			setLista(new ArrayList<Usuario>());
		return lista;
	}

	@JsonPropertyDescription(value="usuarios")
	@JsonProperty(value="usuarios")
	public void setLista(Collection<Usuario> lista) {
		this.lista = lista;
	}
	
		
}

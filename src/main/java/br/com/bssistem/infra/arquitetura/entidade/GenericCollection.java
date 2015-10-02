package br.com.bssistem.infra.arquitetura.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import br.com.bssistem.treinows.dominio.entidade.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@JsonRootName(value="lista")
@JsonSerialize()
@XmlRootElement(name="lista")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Usuario.class})
public class GenericCollection<E> implements Serializable{

	@XmlElement(name="elemento")
	@JsonProperty(value="lista")
	private Collection<E> lista;
	
	public GenericCollection(){
		
	}

	public Collection<E> getLista() {
		if(lista == null)
			setLista(new ArrayList<E>());
		return lista;
	}

	public void setLista(Collection<E> lista) {
		this.lista = lista;
	}
	
		
}

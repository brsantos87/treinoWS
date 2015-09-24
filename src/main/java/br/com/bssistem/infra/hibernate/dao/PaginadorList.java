package br.com.bssistem.infra.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anderson Fonseca
 * */

@SuppressWarnings({ "serial", "rawtypes" })
public class PaginadorList <E> extends ArrayList implements List {

	private int size;
	  private int registrosPorPagina;

	  public int size() {
	    return this.size;
	  }

	  public void setSize(int _size) {
	    this.size = _size;
	  }

	  public Object get(int index) {
	    int indice = index % this.registrosPorPagina;
	    try {
	      super.get(indice);
	    } catch (IndexOutOfBoundsException e) {
	      return null;
	    }

	    return super.get(indice);
	  }

	  public int getRegistrosPorPagina() {
	    return this.registrosPorPagina;
	  }

	  public void setRegistrosPorPagina(int pregistrosPorPagina) {
	    this.registrosPorPagina = pregistrosPorPagina;
	  }
}

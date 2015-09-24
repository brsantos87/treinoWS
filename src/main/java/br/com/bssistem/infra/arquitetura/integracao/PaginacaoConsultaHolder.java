package br.com.bssistem.infra.arquitetura.integracao;

/**
 * @author Anderson Fonseca
 * */

public class PaginacaoConsultaHolder {

	private static ThreadLocal<Integer> numeroPagina = new ThreadLocal<Integer>();

	private static ThreadLocal<Integer> limiteRegistro = new ThreadLocal<Integer>();

	private static ThreadLocal<Integer> totalRegistros = new ThreadLocal<Integer>();

	private PaginacaoConsultaHolder(){}
	
	public static Integer getNumeroPagina() {
		return ((Integer) numeroPagina.get());
	}

	public static void setNumeroPagina(Integer _numeroPagina) {
		numeroPagina.set(_numeroPagina);
	}

	public static Integer getLimiteRegistro() {
		return ((Integer) limiteRegistro.get());
	}

	public static void setLimiteRegistro(Integer _limiteRegistro) {
		limiteRegistro.set(_limiteRegistro);
	}

	public static Integer getTotalRegistros() {
		return ((Integer) totalRegistros.get());
	}

	public static void setTotalRegistros(Integer _totalRegistros) {
		totalRegistros.set(_totalRegistros);
	}

}

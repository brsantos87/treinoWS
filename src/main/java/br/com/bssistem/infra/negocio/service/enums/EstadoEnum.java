package br.com.bssistem.infra.negocio.service.enums;

import br.com.bssistem.infra.negocio.service.util.Util;



public enum EstadoEnum {
	
	ACRE("AC","Acre"),ALAGOAS("AL","Alagoas"),AMAPA("AP",Util.getProp().getString("amapa")),AMAZONAS("AM","Amazonas"),
	BAHIA("BA","Bahia"),CEARA("CE",Util.getProp().getString("ceara")),DIST_FEDERAL("DF","Distrito Federal"),
	ESP_SANTO("ES",Util.getProp().getString("espiritoSanto")),
	GOIAS("GO",Util.getProp().getString("goias")),MARANHAO("MA",Util.getProp().getString("maranhao")),
	MATO_GROSSO("MT","Mato Grosso"),MATO_GROSSO_SUL("MS","Mato Grosso do Sul"),
	MINAS_GERAIS("MG","Minas Gerais"),PARA("PA",Util.getProp().getString("para")),PARAIBA("PB",Util.getProp().getString("paraiba"))
	,PARANA("PR",Util.getProp().getString("parana")),PERNAMBUCO("PE","Pernambuco"),
	PIAUI("PI",Util.getProp().getString("piaui")),RIO_JANEIRO("RJ","Rio de Janeiro"), RIO_GRANDE_NORTE("RN","Rio Grande do Norte"),
	RIO_GRANDE_SUL("RS","Rio Grande do Sul"), RONDONIA("RO",Util.getProp().getString("rondonia")),
	RORAIMA("RR","Roraima"),SANTA_CATARIAN("SC","Santa Catarina"),SAO_PAULO("SP",Util.getProp().getString("saoPaulo")),SERGIPE("SE","Sergipe"),
	TOCANTIS("TO","Tocantins");
	
	private String identificador;
	private String descricao;
	
	private EstadoEnum(String pIdentificador, String pDescricao){
		this.identificador = pIdentificador;
		this.descricao = pDescricao;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoEnum getEnum(String identificador){
		for (EstadoEnum estadoEnum : EstadoEnum.values()) {
			if(identificador.equals(estadoEnum.getIdentificador())){
				return estadoEnum;
			}
		}
		return null;
	}
}

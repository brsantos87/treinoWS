package br.com.bssistem.infra.controle;
public enum ReturnType{
		XML("application/xml"),
		JSON("application/json");
		
		public String header;
		
		ReturnType(String header){
			this.header = header;
		}
		
		public static ReturnType getReturTypeByHeader(String header){
			for(ReturnType rt : ReturnType.values())
				if(header.equalsIgnoreCase(rt.header))
					return rt;
			return JSON;
		}
		
	}
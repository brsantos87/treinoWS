package br.com.bssistem.infra.negocio.service.validadores;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import br.com.bssistem.infra.annotation.CNPJ;
import br.com.bssistem.infra.excecoes.CNPJInvalidoException;
import br.com.bssistem.infra.negocio.service.util.Util;

/**
 * @author Anderson Fonseca
 * 
 * */

public class ValidadorCNPJ extends Validador {
	
	@Override
	public void validar() {
		
		Object objetoParam = getObjeto();
		Field[] fields = objetoParam.getClass().getDeclaredFields();
		boolean cpfInvalido = false;
		String nomeCampo = null;
		
		for (Field fld : fields) {
			if (fld.isAnnotationPresent(CNPJ.class)){
				try {				
					Method metodo = Util.getMetodo(objetoParam, fld.getName());
					String CNPJ = String.valueOf(metodo.invoke(objetoParam, null));
					if(CNPJ.length()< 18){
						//cpfInvalido = true;
						//nomeCampo = getPropProjeto().getString(fld.getName());
						 break;
					}
					String CNPJv = CNPJ.substring(0, 16);
					 
					 CNPJv = CNPJv + calculoPrimeiroDigito(CNPJv);
					 CNPJv = CNPJv + calculoSegundoDigito(CNPJv);
					 
					 if (!CNPJ.equals(CNPJv) || CNPJ.equals("00.000.000/0000-00")){
						 cpfInvalido = true;
						 nomeCampo = getPropProjeto().getString(fld.getName());
						 break;
					 }
				} catch (Exception e) {
					getLogger().error(e.getMessage());
				}
			}		 
		}
		
		if (cpfInvalido){
			MessageFormat mf = new MessageFormat(getProp().getString("VALOR_INVALIDO"));
			String msg =  mf.format(new Object[]{nomeCampo}, new StringBuffer(), null).toString();
		    throw new CNPJInvalidoException(msg);
		}
	}
	
	 private int calculoPrimeiroDigito(String str) {
		 int soma = 0;
		 	
			Integer[] icnpj = new Integer[12];
			
			icnpj[0] = new Integer(str.substring(0, 1)) * 5;
			icnpj[1] = new Integer(str.substring(1, 2)) * 4;
			
			icnpj[2] = new Integer(str.substring(3, 4)) * 3;
			icnpj[3] = new Integer(str.substring(4, 5)) * 2;
			icnpj[4] = new Integer(str.substring(5, 6)) * 9;

			icnpj[5] = new Integer(str.substring(7, 8)) * 8;
			icnpj[6] = new Integer(str.substring(8, 9)) * 7;
			icnpj[7] = new Integer(str.substring(9, 10)) * 6;

			icnpj[8] = new Integer(str.substring(11, 12)) * 5;
			icnpj[9] = new Integer(str.substring(12, 13)) * 4;
			icnpj[10] = new Integer(str.substring(13, 14)) * 3;
			icnpj[11] = new Integer(str.substring(14, 15)) * 2;

			for(int i=0; i < icnpj.length; i++){
				soma+=icnpj[i];
			}
		
			int resultado = (soma / 11) * 11;
			int total = soma - resultado;
			
			if(total==0 || total ==1){
				total = 0;
			} else {
				total = 11 - total;
			}
			
		 return total;
	 }
	 
	 private int calculoSegundoDigito(String str) {
		 int soma = 0;
		 	
			Integer[] icnpj = new Integer[13];
			
			icnpj[0] = new Integer(str.substring(0, 1)) * 6;
			icnpj[1] = new Integer(str.substring(1, 2)) * 5;
			
			icnpj[2] = new Integer(str.substring(3, 4)) * 4;
			icnpj[3] = new Integer(str.substring(4, 5)) * 3;
			icnpj[4] = new Integer(str.substring(5, 6)) * 2;

			icnpj[5] = new Integer(str.substring(7, 8)) * 9;
			icnpj[6] = new Integer(str.substring(8, 9)) * 8;
			icnpj[7] = new Integer(str.substring(9, 10)) * 7;

			icnpj[8] = new Integer(str.substring(11, 12)) * 6;
			icnpj[9] = new Integer(str.substring(12, 13)) * 5;
			icnpj[10] = new Integer(str.substring(13, 14)) * 4;
			icnpj[11] = new Integer(str.substring(14, 15)) * 3;
			
			icnpj[12] = new Integer(str.substring(16, 17)) * 2;

			for(int i=0; i < icnpj.length; i++){
				soma+=icnpj[i];
			}
		
			int resultado = (soma / 11) * 11;
			int total = soma - resultado;
			
			if(total==0 || total ==1){
				total = 0;
			} else {
				total = 11 - total;
			}
			
		 return total;
	 }

}

package br.com.bssistem.infra.negocio.service.validadores;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import br.com.bssistem.infra.annotation.CPF;
import br.com.bssistem.infra.excecoes.CPFInvalidoException;
import br.com.bssistem.infra.negocio.service.util.Util;

/**
 * @author Anderson Fonseca
 * 
 * */

public class ValidadorCPF extends Validador {
	
	@Override
	public void validar() {
		
		Object objetoParam = getObjeto();
		Field[] fields = objetoParam.getClass().getDeclaredFields();
		boolean cpfInvalido = false;
		String nomeCampo = null;
		
		for (Field fld : fields) {
			if (fld.isAnnotationPresent(CPF.class)){
				try {				
					Method metodo = Util.getMetodo(objetoParam, fld.getName());
					String CPF = String.valueOf(metodo.invoke(objetoParam, null));
					String CPFv = CPF.substring(0, 12);
					 
					 CPFv = CPFv + calculoPrimeiroDigito(CPFv);
					 CPFv = CPFv + calculoSegundoDigito(CPFv);
					 
					 if (!CPF.equals(CPFv) || CPF.equals("000.000.000-00")){
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
		    throw new CPFInvalidoException(msg);
		}
		
	}
	
	private Integer calculoPrimeiroDigito(String CPF){
		
		Integer[] icpf = new Integer[9];

		icpf[0] = new Integer(CPF.substring(0, 1)) * 10;
		icpf[1] = new Integer(CPF.substring(1, 2)) * 9;
		icpf[2] = new Integer(CPF.substring(2, 3)) * 8;
		
		icpf[3] = new Integer(CPF.substring(4, 5)) * 7;
		icpf[4] = new Integer(CPF.substring(5, 6)) * 6;
		icpf[5] = new Integer(CPF.substring(6, 7)) * 5;
		
		icpf[6] = new Integer(CPF.substring(8, 9)) * 4;
		icpf[7] = new Integer(CPF.substring(9, 10)) * 3;
		icpf[8] = new Integer(CPF.substring(10, 11)) * 2;
		
		Integer total = 0;
		
		for(int i = 0; i < icpf.length; i++){
			total += icpf[i];
		}

		return verificaDigito(total);
		
	}
	
	private Integer calculoSegundoDigito(String CPF){
		
		Integer[] fcpf = new Integer[10];

		fcpf[0] = new Integer(CPF.substring(0, 1)) * 11;
		fcpf[1] = new Integer(CPF.substring(1, 2)) * 10;
		fcpf[2] = new Integer(CPF.substring(2, 3)) * 9;
		
		fcpf[3] = new Integer(CPF.substring(4, 5)) * 8;
		fcpf[4] = new Integer(CPF.substring(5, 6)) * 7;
		fcpf[5] = new Integer(CPF.substring(6, 7)) * 6;
		
		fcpf[6] = new Integer(CPF.substring(8, 9)) * 5;
		fcpf[7] = new Integer(CPF.substring(9, 10)) * 4;
		fcpf[8] = new Integer(CPF.substring(10, 11)) * 3;
		
		fcpf[9] = new Integer(CPF.substring(12, 13)) * 2;

		Integer total2 = 0;
		
		for(int i = 0; i < fcpf.length; i++){
			total2 += fcpf[i];
		}
		
		return verificaDigito(total2);
	}
	
	private Integer verificaDigito(Integer valor){

		Integer digito = valor % 11;
		
		if (digito < 2){
			digito = 0;
		} else {
			digito = 11 - digito;
		}
		
		return digito;
	}

}

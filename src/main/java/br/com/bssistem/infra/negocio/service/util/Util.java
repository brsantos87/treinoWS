package br.com.bssistem.infra.negocio.service.util;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.text.MaskFormatter;


public class Util {

	private static final String MD5 = "MD5";
	private static final String SIM = "Sim";
	private static final String NAO = getProp().getString("confirmar_nao");
	private static final String DIA = "DD";
	private static final String MES = "MM";
	
	private static ResourceBundle propSystemConfig = ResourceBundle.getBundle("system-config");
	private static ResourceBundle prop = ResourceBundle.getBundle(getSystemConfig().getString("project.package")+".mensagens");
	
	public static void main(String[] args) {
		System.out.println(getSystemConfig().getString("project.package"));
	}
	
	public static ResourceBundle getProp() {
		if (prop == null)
			prop = ResourceBundle.getBundle(getSystemConfig().getString("project.package")+".mensagens");
		return prop;
	}
	public static void setProp(ResourceBundle prop) {
		Util.prop = prop;
	}
	
	public static ResourceBundle getSystemConfig(){
		if(propSystemConfig == null)
			propSystemConfig = ResourceBundle.getBundle("system-config");
		return propSystemConfig;
	}
	
	public static String getNamePackageProject(){
		return getSystemConfig().getString("project.package");
	}
	
	public static String toUppercase(String str){
		String str1 = str.substring(0,1).toUpperCase();
		String str2 = str1 + str.substring(1);
		return str2;
	}
	
	public static Method getMetodo(Object object, String nomeDoCampo) throws NoSuchMethodException {
		Method metodo = object.getClass().getMethod("get" + toUppercase(nomeDoCampo), null);
		return metodo;
	}
	
	public static Class<?> getMethodReturnType(Method meth){
		return meth.getReturnType();
	}
	
	public static Object verifyString(Object obj){
		if (String.valueOf(obj).trim().length() == 0){
			obj = null;
		}
		return obj;
	}
	
	public static Date verifyDate(Object obj){
		return (Date) obj;
	} 
	
	public static String convertDoubleToString(Double valor) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		String valorFormatado ="";
		if(valor == null || valor == 0.0){
			return "R$ 0,00" ;
		}else{
			valorFormatado = "R$ " + df.format(valor);
			
			return valorFormatado.replace("R$ ,", "R$ 0,");
		}
	}

	public static String convertDoubleToStringSemCifrao(Double valor) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		if(valor == null || valor == 0.0){
			return "0,00" ;
		}else{
			String numero = df.format(valor);
			if(numero.length() == 3){
				String numero2 =  addZerosEsquerda(4,numero);
				return numero2;
			}
			return numero;
		}
	}
	
	public static String convertDoubleToStringSemCifraoNaoArredondaValor(Double valor) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		if(valor == null || valor == 0.0){
			return "0,00" ;
		}else{
			BigDecimal valorFormatado = new BigDecimal(truncarCasaDecimal(valor.toString()));
			valor = valorFormatado.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
			String numero = df.format(valor);
			if(numero.length() == 3){
				String numero2 =  addZerosEsquerda(4,numero);
				return numero2;
			}
			return numero;
		}
	}
	
	
	public static Double formatStringToDouble(String valor) {
		Double retorno = new Double(0.0);
		
		if(valor != null){
			String valorFormatado = valor.replace(".","").trim();
			valorFormatado = valorFormatado.replace(",",".").trim();
			
			retorno = Double.parseDouble(valorFormatado);
		}
		
		return retorno;
	}

	public static String formatDoubleToString(Double valor) {
		String retorno = null;
		if(valor != null){
			String valorFormatado = valor.toString().replace(".",",").trim();
			
			retorno = valorFormatado;
			retorno = retorno.replace(",00", "");
			retorno = retorno.replace(",0", "");
		}
		
		return retorno;
	}
	
	public static Double convertStringToDouble(String valor) {
		
		Double retorno = new Double(0.0);
		
		if(valor != null){
			try {
				String valorFormatado = valor.replace("R$", "").trim();
				DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();   
				numberFormat.setMinimumFractionDigits(2);
				try {
					Long db = (Long) numberFormat.parse(valorFormatado);
					retorno = Double.parseDouble(db.toString());
				} catch (Exception e) {
					retorno = Double.parseDouble(numberFormat.parse(valorFormatado).toString());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	public static String convertBooleanToString(Long valor){
		String retorno = "";
		if(valor != null && valor == 0){
			retorno = NAO;
		}else if (valor != null && valor == 1){
			retorno = SIM;
		}
		return retorno;
	}
	
	public static int convertStringToInt(String valor){
		if(!isNullOrEmpty(valor) && valor.equalsIgnoreCase(SIM)){
			return 1;
		}else{
			return 0;
		}
	}
	
	public static boolean isNullOrEmpty(Object objeto){
		return objeto == null || objeto.toString() == "";
	}
	
	/**
	 * Funcao que converte a String com formato "10:10:10" em Timestamp com a
	 * data atual
	 * 
	 * @param hora
	 * @return
	 */
	public static Date convertHoraStringTimestamp(String hora){
		Date dateTime = null;
		if(!isNullOrEmpty(hora)){
			String[] values = hora.split(":");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(values[0]));
			c.set(Calendar.MINUTE, Integer.parseInt(values[1]));
			c.set(Calendar.SECOND, Integer.parseInt(values[2]));
			
			dateTime = c.getTime();
			
		}
		return dateTime;
	}
	
	
	public static String convertDateToStringTime(Date hora) {
		String retorno = "";

		if (hora != null) {
			Calendar c = Calendar.getInstance();
			
			c.setTime(hora);
			String hr = String.valueOf(c.get(Calendar.HOUR)).length() == 1 ? "0"
					+ String.valueOf(c.get(Calendar.HOUR))
					: String.valueOf(c.get(Calendar.HOUR));
			String mm = String.valueOf(c.get(Calendar.MINUTE)).length() == 1 ? "0"
					+ String.valueOf(c.get(Calendar.MINUTE))
					: String.valueOf(c.get(Calendar.MINUTE));
			String ss = String.valueOf(c.get(Calendar.SECOND)).length() == 1 ? "0"
					+ String.valueOf(c.get(Calendar.SECOND))
					: String.valueOf(c.get(Calendar.SECOND));
			retorno = hr + ":" + mm + ":" + ss;
		}
		return retorno;
	}
	
	public static String addZerosEsquerda(int nCasas, String valor) {
		int n = nCasas - valor.length();
		String retorno = "";
		for (int i = 0; i < n; i++) {
			retorno = retorno + "0";
		}
		return retorno + valor;
	}

	public static String formataTipoPrazo(String prazoGarantiaTipo) {
		if(prazoGarantiaTipo.equalsIgnoreCase(DIA)){
			return "Dias";
		}else if(prazoGarantiaTipo.equalsIgnoreCase(MES)){
			return "Meses";
		}else{
			return "Anos";
		}
	}
	
	public static String formataValorDecimalFormat(String valor){
		if(valor.equals("0.00")){
			return "R$ 0,00" ;
		}else{
			String valor2[] = valor.split("\\.");
			if (valor2.length == 2){
				if(valor2[1].trim().length() == 1){
					valor2[1] = valor2[1]+"0";
				}
				valor2[0] = valor2[0].length() > 3 ? 
						valor2[0].substring(0, valor2[0].length() - 3) + "." +
						valor2[0].substring(valor2[0].length() - 3, valor2[0].length()) : valor2[0];
				valor2[0] = valor2[0].length() > 7 ? 
						valor2[0].substring(0, valor2[0].length() - 7) + "." +
						valor2[0].substring(valor2[0].length() - 7, valor2[0].length()) : valor2[0];
				valor2[0] = valor2[0].length() > 11 ? 
						valor2[0].substring(0, valor2[0].length() - 11) + "." +
						valor2[0].substring(valor2[0].length() - 11, valor2[0].length()) : valor2[0];
			}
			return "R$ " + valor2[0] + "," + valor2[1];//df.format(valor);
		}
	}
	public static long diferencaDiasComDataAtual(Date data){
		
		 SimpleDateFormat formato = new SimpleDateFormat("d/MM/yyyy");
		 String dat = formato.format(data);
		 Date dataInicial = null;
		 try {
			  dataInicial = formato.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace(); }
	      Date dataAtual = new Date();
	      
			// Calcula a diferenca entre hoje e da data de inicio
			long diferenca =  dataInicial.getTime() - dataAtual.getTime();

			// Quantidade de milissegundos em um dia
			long tempoDia = 1000L * 60L * 60L * 24L;

		long diasDiferenca = diferenca / tempoDia;
		return diasDiferenca;
		

	}
	
	/**  
     * Calcula a diferenca de duas datas em dias  
     * <br>  
     * <b>Importante:</b> Quando realiza a diferenca em dias entre duas datas, este metodo considera as horas restantes e as converte em fracao de dias.  
     * @param dataInicial  
     * @param dataFinal  
     * @return quantidade de dias existentes entre a dataInicial e dataFinal.  
     */   
    public static double diferencaEmDias(Date dataInicial, Date dataFinal){   
        double result = 0;   
        long diferenca = dataFinal.getTime() - dataInicial.getTime();   
        double diferencaEmDias = (diferenca /1000) / 60 / 60 /24; //resultado e diferenca entre as datas em dias   
        long horasRestantes = (diferenca /1000) / 60 / 60 /24; //calcula as horas restantes   
        result = diferencaEmDias + (horasRestantes /24d); //transforma as horas restantes em fracao de dias   
        
        return result;   
    }   
    
    /**Recupera o valor do array de duas posicoes para os tipo de garantai
	 * EX: 10;MM
	 * @param valor
	 * @param indice
	 * @return
	 */
	public static String getValorArray(String valor, int indice) {
		String retorno = "";
		if(!Util.isNullOrEmpty(valor)){
			String[] values = valor.split(";");
			if(values != null && values.length == 2){
				retorno = values[indice];
			} 
		}else{
			retorno = valor;
		}
		return retorno;
	}
	
	public static int getMonthDiff(Date dateStart, Date dateEnd) {  
        int count = 0;  
        if (dateStart != null && dateEnd != null && dateStart.before(dateEnd)) {  
  
            Calendar clStart = Calendar.getInstance();  
            clStart.setTime(dateStart);  
  
            Calendar clEnd = Calendar.getInstance();  
            clEnd.setTime(dateEnd);  
  
            while (clStart.get(Calendar.MONTH) != clEnd.get(Calendar.MONTH) ||
            		clStart.get(Calendar.YEAR) != clEnd.get(Calendar.YEAR)) {  
                clStart.add(Calendar.MONTH, 1);  
                count++;  
            }
            count++;
        }  
        return count;  
    }

	/**
	 * O metodo retorna a quantidade de meses  entre as datas exemplo:
	 * dataInicio = 12/11/2012
	 * dataFinal = 12/12/2012
	 * o sistema vai retornar 2 pois o intervalo das datas pega o mes 11 e 12, o metodo considera o mes independente do dia. 
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
	public static double diferencaMeses(Date dataInicio, Date dataFinal){
		/*  
         * valor de um mes em milisegundos, sendo que os valores sao:  
         * 30 dias no mes, 24 horas no dia, 60 minutos por hora, 60 segundos por minuto e 1000 milisegundos    
         */   
        final double MES_EM_MILISEGUNDOS = 30.0 * 24.0 * 60.0 * 60.0 * 1000.0;   
        //final double MES_EM_MILISEGUNDOS = 2592000000.0;   
        
//        int numeroDeMeses = (int)((dataFinal.getTime() - dataInicio.getTime())/MES_EM_MILISEGUNDOS) + 1;
        int numeroDeMeses = (int)((dataFinal.getTime() - dataInicio.getTime())/MES_EM_MILISEGUNDOS);
//        System.out.println("numero de meses: "+ numeroDeMeses); 
        return numeroDeMeses;
	}
	
	public static int quantidadeMesesRestanteAnoAtual(Date data){
		Calendar calendar  = Calendar.getInstance();
		calendar.setTimeInMillis(data.getTime());
		int mes = calendar.get(Calendar.MONTH);
		int restante = 12 - mes;
		return restante;
	}
	
	public static String truncarCasaDecimal(String valor) {
		System.out.println(valor);
		String formatada = valor.substring(valor.lastIndexOf(".")+1, valor.length());
		if ( formatada.length() <= 2 )
			return valor;
		else
			return valor.substring(0,valor.indexOf(".")+1).concat(formatada.substring(0, 2));
	}
	
	public static String formatarString(String texto, String mascara) {
			try {
				if ( !isNullOrEmpty(texto) ){
					MaskFormatter mf = new MaskFormatter(mascara);
					mf.setValueContainsLiteralCharacters(false);  
					return mf.valueToString(texto);  
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		return null;
	}
	
	public static String removerMascara(String texto){
		if ( !isNullOrEmpty(texto) )
			return texto.replaceAll("[^0-9]",""); 
		return null;
	}
	
	public static boolean senhasInguais(String senha, String senhaBanco){
		String senhaCriptografada = "";
		try {
			byte[] hash = Hashs.gerarHashTexto(senha,MD5);
			senhaCriptografada= null; /*new String(org.bouncycastle.util.encoders.Hex.encode(hash));*/
		} catch (HashingExceptions e) {
			e.printStackTrace();
		}
		return senhaCriptografada.equals(senhaBanco);
	}
	
	public static byte[] enviarParametrosServlet(HashMap<String, String> map, String caminhoServlet) {
		
		byte[] status = null;
		
		try {
			URL url = new URL( caminhoServlet );
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setDoOutput( true );
			connection.setRequestProperty( "Content-Type", "application/octet-stream" );

			ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream() );
			oos.writeObject(map);
			oos.flush();
			oos.close();

			ObjectInputStream ois = new ObjectInputStream(connection.getInputStream() );
			status = (byte[]) ois.readObject();
			ois.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static Date getDataHoraInicioOrFim(Date data, boolean inicio){
		Calendar c = Calendar.getInstance();
		if ( data == null )
			return new Date();
		c.setTime(data);
		if (inicio){
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
		}else{
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
		}
		return c.getTime();
	}
	
	public static List<String> getListPalavrasTexto(String texto, boolean comPreposicoes){
		   
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(texto);
		while (tokenizer.hasMoreElements()) {
			String valor = (String) tokenizer.nextElement();
			if ( comPreposicoes )
				result.add("%".concat(valor).concat("%"));
			else if ( !ehPreposicao(valor) )
			if ( !comPreposicoes  )
				result.add("%".concat(valor).concat("%"));
		}
		return result;
	}
	
	public static String getPalavrasTexto(String texto, boolean comPreposicoes){
		   
		StringBuilder result = new StringBuilder();
		if ( !isNullOrEmpty(texto) ){
			result.append("%");
			StringTokenizer tokenizer = new StringTokenizer(texto);
			while (tokenizer.hasMoreElements()) {
				String valor = (String) tokenizer.nextElement();
				if ( comPreposicoes )
					result.append(valor).append("%");
				else if ( !ehPreposicao(valor) )
					if ( !comPreposicoes  )
						result.append(valor).append("%");
			}
		}
		return result.toString();
	}
	private static boolean ehPreposicao(String valor){
		String[] array = new String[]{"de","do","da","dos","das","a","o","na","no","em","e","ou","xou"};
		for (int i = 0; i < array.length; i++) {
			if ( array[i].equals(valor) )
				return true;
		}
		return false;
	}
	
	
	public static String getTextoMarcado(String textoBusca, String texto) {
		return doDestacaTextoBusca(textoBusca, texto, false);
	}
	
	private static String doDestacaTexto(String texto, String termoBusca){
		
		String inicioTag = "<span class=\"marcador\">";
		String fimTag = "</span>";
		
		String novoTexto = "";
		int i = -1;
		String lcTermoBusca = Util.isNullOrEmpty(termoBusca) ? "" : termoBusca.toLowerCase();
		String lcTexto = Util.isNullOrEmpty(texto) ? "" :  texto.toLowerCase();
		
		if ( !Util.isNullOrEmpty(lcTermoBusca) && !Util.isNullOrEmpty(lcTexto) ){
			while (texto.length() > 0){
				i = lcTexto.indexOf(lcTermoBusca, i+1);
				if (i < 0){
					novoTexto += texto;
					texto = "";
				}
				else{
					if (texto.lastIndexOf(">", i) >= texto.lastIndexOf("<", i)){
						if (lcTexto.lastIndexOf("/script>", i) >= lcTexto.lastIndexOf("<script", i)){
//						novoTexto += texto.substring(0, i) + inicioTag + texto.substring(i, termoBusca.length()) + fimTag;
							novoTexto += texto.substring(0, i) + inicioTag + termoBusca + fimTag;
							texto = texto.substring(i + termoBusca.length());
							lcTexto = texto.toLowerCase();
							i = -1;
						}
					}
				}
			}
		}
		return novoTexto;
	}
	
	public static String doDestacaTextoBusca(String textoBusca, String textoObj, boolean ehFrase){
		String[] arrayBusca = null;
		if (ehFrase){
				arrayBusca= new String[]{textoBusca};
		}else{
			arrayBusca = textoBusca.split(" ");
		}
		String texto = textoObj;

		for (int i = 0; i < arrayBusca.length; i++){
			texto = doDestacaTexto(texto, arrayBusca[i]);
		}
		return texto;
	}
	
	
	public static Date getDataAtual(){
		Date dataAtual = new Date(System.currentTimeMillis());
		return dataAtual;
	}
	
	public static String getNomeArquivo(String urlArquivo){
		int begin = 0;
		int end = urlArquivo.length();
		String retorno = urlArquivo;
		if(urlArquivo.contains(File.separator) || urlArquivo.contains("\\")){
			for(int i = urlArquivo.length(); i > 0; i--){
				if(i < end){
					if(urlArquivo.substring(i,i+1).equals(File.separator) || urlArquivo.substring(i,i+1).equals("\\")){
						begin = i+1;
						break;
					}
				}
			}
			retorno = urlArquivo.substring(begin, end); 
		}
		
		return retorno;
	}
	
		
	 public static String removerAcentos(String acentuada) {  
	        CharSequence cs = new StringBuilder(acentuada);  
	        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");  
	    }  
}

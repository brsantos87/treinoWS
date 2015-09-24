package br.com.bssistem.teste;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;




public class Teste {

	public static void main(String[] args) throws Exception {
		String t = "1007 - SEÇÃO DE PROCESSAMENTO DE MANDADOS DE SEGURANÇA HABEAS CORPUS HABEAS DATA E OUTROS FEITOS";
		
        System.out.println(t.length());    
}
	

	 public String getApplicationPath() {  
	        String url = getClass().getResource(getClass().getSimpleName() + ".class").getPath();  
	        File dir = new File(url).getParentFile();  
	        String path = null;  
	          
	        if (dir.getPath().contains(".jar"))  
	            path = findJarParentPath(dir);  
	        else  
	            path = dir.getPath();  
	  
	        try {  
	            return URLDecoder.decode(path, "UTF-8");  
	        }  
	        catch (UnsupportedEncodingException e) {                  
	            return path.replace("%20", " ");  
	        }  
	    }  
	      
	    /** 
	     *  retorna o caminho quando a aplicao est dentro de um  
	     *  arquivo .jar 
	     * @param jarFile 
	     * @return 
	     */  
	    private String findJarParentPath(File jarFile) {  
	        while (jarFile.getPath().contains(".jar"))  
	            jarFile = jarFile.getParentFile();  
	          
	        return jarFile.getPath().substring(6);  
	    }  
	    

}
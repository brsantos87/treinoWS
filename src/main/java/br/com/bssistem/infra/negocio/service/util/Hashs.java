package br.com.bssistem.infra.negocio.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;



/**
 * @author Gerv�sio Kayser
 *
 * Classe abstrata que implementa a geracao de Hashs.
 */
public class Hashs
{
	
	/**
	 * M�todo interno usado para a gera��o do hash.
	 * 
	 * @param buffer - Array de bytes a ser criptografado
	 * @param hashAlg - Algoritmo de Has a ser utilizado
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	private static byte[] gerarHash(byte[] buffer,String algoritmoHash) throws NoSuchAlgorithmException, NoSuchProviderException
	{		
		//Registra o provedor criptografico da BouncyCastle
    	Security.addProvider(new BouncyCastleProvider());
    	
    	//Cria um objeto MessageDigest usando o provedor da BouncyCastle e o algoritmo de Hash indicado em 'algoritmoHash'. Exemplo: SHA1, MD5
        MessageDigest digest=MessageDigest.getInstance(algoritmoHash,"BC");
        
        //Passa o texto claro como um array de bytes
        byte[] hash=digest.digest(buffer);
        
        return hash;
	}
	
	/**
	 * M�todo respons�vel por implementar a gera��o de Hash de uma String
	 * 
	 * @param textoClaro - Texto a ser criptografado
	 * @param algoritmoHash - Algoritmo de Hash a ser utilizado
	 * @throws HashingException
	 */
	public static byte[] gerarHashTexto(String textoClaro, String algoritmoHash) throws HashingExceptions
	{		
        try
        {            
            //Passa o texto claro como um array de bytes      	
            return gerarHash(textoClaro.getBytes(),algoritmoHash);            
        }
        catch(NoSuchAlgorithmException e)
        	{        	
            throw new HashingExceptions("Nao h� suporte ao algoritmo "+algoritmoHash+"\nTente usar 'SHA1' ou 'MD5'!");
        	}
        catch (NoSuchProviderException e)
        	{
			throw new HashingExceptions("Provedor criptogr�fico n�oo dispon�vel!");
        	}
    }
	
	/**
	 * M�todo respons�vel por implementar a geracao de Hash de um array de bytes
	 * 
	 * @param buffer - Array de bytes a ser criptografado
	 * @param algoritmoHash - Algoritmo de Hash a ser usado
	 * @throws HashingException
	 */
	public static byte[] gerarHashBuffer(byte[] buffer, String algoritmoHash) throws HashingExceptions
	{
		try
		{          
        //Passa o texto claro como um array de bytes
        return gerarHash(buffer,algoritmoHash);
            
        }
			catch(NoSuchAlgorithmException e)
			{        	
            throw new HashingExceptions("Não há suporte ao algoritmo "+algoritmoHash+"!\nTente usar 'SHA1' ou 'MD5'.");            
			}
			catch (NoSuchProviderException e)
			{
			throw new HashingExceptions("Provedor criptografico nao disponivel!");
			}
	}
	
	/**
	 * Gera o hash de um aquivo, em ciclos de leitura de tamanho bufferSize KB.
	 * 
	 * @param arquivo - Nome do arquivo que ser� gerado o Hash
	 * @param algoritmoHash - Algoritmo de Hash a ser usado
	 * @throws HashingException
	 */
	public static byte[] gerarHashArquivo(String arquivo, String algoritmoHash) throws HashingExceptions
	{		
		try
		{		
		byte[] hash=null;
			
		// Registra o provedor criptogr�fico da BouncyCastle
	    Security.addProvider(new BouncyCastleProvider());
	    
	    // Cria um objeto MessageDigest usando o provedor da BouncyCastle e o algoritmo de Hash indicado em 'algoritmoHash'
	    MessageDigest digest = MessageDigest.getInstance(algoritmoHash,"BC");
	    
	    //Tamanho do buffer de trabalho	    
	    int tamanhoBuffer=128;
	    
	    //bufferSize KB
        byte[] storage = new byte[tamanhoBuffer*1024-1];
			
        // Abre o arquivo a ser "hasheado"
        File file=new File(arquivo);        
        long tamanho=file.length();        
        long posicao=0;
        int bytesRead;
	        
        InputStream is=new FileInputStream(file);
	        
        // Gera o hash em tamanho/bufferSize passos.
        while (posicao<tamanho)
	        {
	        //L� bufferSize bytes
	        bytesRead=is.read(storage, 0, storage.length);
	        posicao+=bytesRead;
	
            // Incrementa o hash
            digest.update(storage, 0, bytesRead);
	        }

        // Finaliza o hash.
	    hash = digest.digest();
	        
	    is.close();
	        
	    return hash;	                
	        
		}
			catch(NoSuchAlgorithmException e)
			{    	
				throw new HashingExceptions("N�o h� suporte ao algoritmo "+algoritmoHash+ "!");
			}
			catch (NoSuchProviderException e)
			{			
				throw new HashingExceptions("Provedor criptografico nao disponivel!");
			}
			catch (FileNotFoundException e)
			{			
				throw new HashingExceptions("Arquivo "+algoritmoHash+ " n�o encontrado!");
			}
			catch (IOException e)
			{			
				throw new HashingExceptions("Erro em opera��o de arquivo.");
			}
	}
	
	/**
	 * Gera o hash de um aquivo, em ciclos de leitura de tamanho bufferSize KB e coloca dentro de um arquivo .txt
	 * 
	 * @param nomeArquivo - Nome do arquivo que ser� gerado o Hash
	 * @param algoritmoHash - Algoritmo de Hash a ser usado
	 * @throws HashingException
	 */
	public static String gerarHashStringArquivo(String nomeArquivo, String algoritmoHash) throws HashingExceptions
	{
		byte[] hashByte=Hashs.gerarHashArquivo(nomeArquivo,algoritmoHash);
		
		String hashHexa=converterArrayByteHexa(hashByte);
        
        return hashHexa;
	}
	
	/**
	 * Confere o ha1sh de um arquivo utilizando o m�todo gerarHashStringArquivo da classe br.gov.jfce.criptografia.Hashs
	 * 
	 * @param nomeArquivo - Nome do arquivo que ser� testado o Hash
	 * @param algoritmoHash - Algoritmo de Hash a ser usado
	 * @param hash - Hash a ser usado
	 * @throws HashingExceptions 
	 */
	public static boolean conferirHashArquivo(String nomeArquivo,String algoritmoHash,String hash) throws HashingExceptions
	{
		String hashArquivo=gerarHashStringArquivo(nomeArquivo,algoritmoHash);
		
        if (hashArquivo.equals(hash))
        	return true;
        	else
           	return false;
	}

	/**
	 * Converte um array de bytes em uma string hexadeciaml
	 * 
	 * @param arrayBytes - Array de bytes a ser convertido
	 */
	public static String converterArrayByteHexa(byte[] arrayBytes)
	{
	StringBuilder s=new StringBuilder();
	
	for (int i=0;i<arrayBytes.length;i++) 
		{
	    int parteAlta=((arrayBytes[i]>>4)&0xf)<<4;
	    int parteBaixa=arrayBytes[i]&0xf;
	    
	    if (parteAlta==0)
	    	s.append('0');
	    
	    s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
	return s.toString();
	}
}
package br.com.bssistem.infra.negocio.service.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


public class EmailUtil extends Thread {

	private static Properties prop;
	
	SimpleEmail emailConf;
	HtmlEmail emailHtml;
		
	private static void carregarProperties() {
		if (prop == null) {
			prop = new Properties();
			InputStream in = new EmailUtil().getClass().getClassLoader()
					.getResourceAsStream(File.separator + "recursos" + File.separator + "email.properties");
			try {
				prop.load(in);
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}

	public void enviarEmail(String emailDe, String emailPara, String assunto, String msg) {
		carregarProperties();
		emailConf = new SimpleEmail();
		try {
		
			emailConf.setDebug(false);
			emailConf.setHostName(prop.getProperty("host"));
//			emailConf.setAuthentication(prop.getProperty("usuario"),prop.getProperty("senha"));
			emailConf.addTo(emailPara, null);
			emailConf.setFrom(emailDe);
			emailConf.setSubject(assunto);
			emailConf.setMsg(msg);
			emailConf.setCharset("utf-8");
			this.start();
			
		} catch (EmailException e) {
			System.out.println("falha no envio do email " + e.getMessage());
		} catch (Exception ex) {
			System.out.println("falha no envio do email");
			ex.printStackTrace();
		}
	}
	
	public void enviarEmailHtml(String emailDe, String emailPara, String assunto, String msg) {
		carregarProperties();
		emailHtml = new HtmlEmail(); 
		
		try {
			emailHtml.setDebug(false);
			emailHtml.setHostName(prop.getProperty("host"));
//			emailHtml.setAuthentication(prop.getProperty("usuario"),prop.getProperty("senha"));
			emailHtml.addTo(emailPara, null);
			emailHtml.setFrom(emailDe);
			emailHtml.setSubject(assunto);
			emailHtml.setHtmlMsg(msg);
			emailHtml.setCharset("utf-8");
			this.start();
			
		} catch (EmailException e) {
			System.out.println("falha no envio do email " + e.getMessage());
		} catch (Exception ex) {
			System.out.println("falha no envio do email");
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			if (emailConf != null){
				emailConf.send();
			}else if(emailHtml != null) {
				emailHtml.send();
			}else{
				System.out.println("Falha na criacao do objeto de configuracao de email");
			}
		} catch (EmailException e) {
			System.out.println("falha no envio do email " + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public static EmailUtil getInstance() {
		return new EmailUtil();
	}
}

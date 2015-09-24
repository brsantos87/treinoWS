package br.com.bssistem.infra.negocio.fabrica;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;



/**
 * @author Anderson Fonseca
 * */

public class FabricaEntidadesAnotadas extends AnnotationSessionFactoryBean {

	
	
	private Logger logger = Logger.getLogger(FabricaEntidadesAnotadas.class);
	
	private String[] packages;

	public void setAnnotatedPackages(String[] annotatedPackages) {
		this.packages = annotatedPackages;
		super.setAnnotatedPackages(annotatedPackages);
		setAnnotatedClasses(new Class[0]);
	}

	@SuppressWarnings("rawtypes")
	public void setAnnotatedClasses(Class[] classes) {
		List<Class> classesEntidades = new ArrayList<Class>();

		for (int i = 0; i < this.packages.length; ++i) {
			try {
				String packageName = this.packages[i];
				String[] classesEntidade = getClassesPorPacote(super.getClass()
						.getClassLoader(), packageName);
				if (classesEntidade != null) {
					for (String s : classesEntidade) {
						
						if (s.indexOf(".class") != -1) {
							String nome = packageName + "." + s.substring(0, s.indexOf(".class"));
							
							try {
								classesEntidades.add(Class.forName(nome));
							} catch (ClassNotFoundException e) {
								logger.error(e);
							}
						}
					}
				}	
			} catch (Exception e) {
				logger.error(e);
			}
		}

		super.setAnnotatedClasses((Class[]) classesEntidades
				.toArray(new Class[0]));
	}

	private String[] getClassesPorPacote(ClassLoader loader, String pack) throws Exception {
     
		try {
		       URL resource = loader.getResource(pack.replace('.', '/'));
		       File dir = new File(resource.toURI());
		       String[] nomes = dir.list();
		       return nomes;
		} catch (Exception e) {
		       throw new Exception(e);
		}
   }
}


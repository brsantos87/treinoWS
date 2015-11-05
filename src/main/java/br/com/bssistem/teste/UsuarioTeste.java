package br.com.bssistem.teste;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.negocio.componente.usuario.bo.UsuarioBO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/mvc-dispatcher-servlet.xml"})
@Controller
public class UsuarioTeste {

	@Autowired
	UsuarioBO usuario;
	
	@Test
	public void test() {
		Usuario usuario = new Usuario();
		
		usuario.setName("Matheus Lubarino");
		//usuarioControle.incluirUsuarios2(usuario);
	
		
	}

}

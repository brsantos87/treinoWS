package br.com.bssistem.treinows.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bssistem.infra.controle.GenericController;
import br.com.bssistem.infra.negocio.service.GenericService;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.negocio.service.manterusuario.UsuarioService;

@Controller
@RequestMapping("/user")
public class UsuarioController extends
		GenericController<Usuario, UsuarioService> {
	
	@Autowired
	private UsuarioService service;

	@Override
	public GenericService<Usuario> getGenericService() {
		return service;
	}

	
	
}

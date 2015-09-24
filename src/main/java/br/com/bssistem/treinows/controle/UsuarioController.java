package br.com.bssistem.treinows.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bssistem.infra.arquitetura.entidade.GenericCollection;
import br.com.bssistem.infra.controle.GenericController;
import br.com.bssistem.infra.negocio.service.GenericService;
import br.com.bssistem.treinows.dominio.EntidadeAbsFabrica;
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

	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public Object findAll() {

		GenericCollection usuarios = new GenericCollection();

		Usuario usuario1 = EntidadeAbsFabrica.getFactory().createUsuario();
		Usuario usuario2 = EntidadeAbsFabrica.getFactory().createUsuario();

		usuario1.setId(1l);
		usuario2.setId(2l);
		usuario1.setNome("BRUNO");
		usuario2.setNome("SANTOS");

		usuarios.getLista().add(usuario1);
		usuarios.getLista().add(usuario2);

		return returnGenericCollection(usuarios);
	}

	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	public Object findByName(@PathVariable String nome) {
		Usuario usuario = new Usuario();
		usuario.setId(10L);
		usuario.setNome(nome);

		return usuario;
	}



	// handles person form submit
	@RequestMapping(value = "/us", method = RequestMethod.POST)
	@ResponseBody
	public String savePerson(Usuario person) {
		service.salvar(person);
		return "Saved person: " + person.toString();
	}

}

package br.com.bssistem.treinows.controle.autenticacao;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.json.Json;
import com.google.gson.Gson;
import com.restfb.json.JsonObject;

import br.com.bssistem.infra.controle.GenericController;
import br.com.bssistem.infra.negocio.service.GenericService;
import br.com.bssistem.treinows.dominio.entidade.Usuario;
import br.com.bssistem.treinows.negocio.service.manterusuario.UsuarioService;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController extends
		GenericController<Usuario, UsuarioService> {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private GoogleAuthHelper helper;
	
	@Override
	public GenericService<Usuario> getGenericService() {
		return service;
	}

	@RequestMapping(value = "/helper", method = RequestMethod.GET)
	@ResponseBody
	public void authViaHelper(){
		try {
			getResponse().sendRedirect(helper.buildLoginUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//helper.getUserInfoJson(request.getParameter("code");
	}
	
	@RequestMapping(value = "/newtoken", method = RequestMethod.GET)
	@ResponseBody
	public void authResponse(){
		helper.getStateToken();
		try {
			String json = helper.getUserInfoJson(getRequest().getParameter("code"));
			Usuario user = new Usuario();
			Gson gson = new Gson();
			user = gson.fromJson(json, Usuario.class);
			System.out.println(user.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

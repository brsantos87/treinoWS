package br.com.bssistem.infra.controle;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import br.com.bssistem.infra.excecoes.CampoObrigatorioNaoInformadoException;
import br.com.bssistem.infra.excecoes.TRFException;

public abstract class AbstractMenssageController extends FilterRequest{
	
	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	private ResourceBundle propProjeto = ResourceBundle.getBundle("br.com.bssistem.mensagens_projeto");
	
	private ResourceBundle prop = ResourceBundle.getBundle("br.com.bssistem.mensagens");
	
	public void getMensagemConfirmacaoOperacaoInclusao() {
	}
	
	public void getMensagemConfirmacaoOperacaoAlteracao() {
	}
	
	public void getMensagemConfirmacaoOperacaoExclusao() {
	}
	
	public String getMensagemNenhumRegistroSelecionado() {
		return prop.getString("MN010");
	}
	
	public void setMensagemNenhumRegistroSelecionado() {
		setMensagemSucesso(prop.getString("MN010"));
	}
	
	public void setMensagemInportarLotacoes() {
		setMensagemSucesso(prop.getString("MN030"));
	}
	
	public void setMensagemMaisDeUmRegistroSelecionado(){
		setMensagemSucesso(prop.getString("MN026"));
	}	
	
	public void setMensagemExcluirSucesso() {
		setMensagemSucesso(prop.getString("MN006"));
	}
	
	public void setMensagemAlterarSucesso() {
		setMensagemSucesso(prop.getString("MN008"));
	}

	public void setMensagemIncluirSucesso() {
		setMensagemSucesso(prop.getString("MN007"));
	}
	
	protected void setMensagemSucesso(String msg) {
	}
	
	protected void mostrarMensagemCamposObrigatorios(String[] requiredFields) {
		StringBuffer builder = new StringBuffer();
		if (requiredFields.length > 0){
			builder.append(prop.getString("MN001") + "<br/>");	

			builder.append("<ul>");
			for (String str: requiredFields){
				builder.append("<li>" + str + "</li>");	
			}
			builder.append("</ul>");
			
			
			throw new CampoObrigatorioNaoInformadoException(builder.toString());
		} else {
		}
	}

	/**
	 * Metodo temporario, utilizado para o cadastro de mapa.
	 * @param requiredFields
	 */
	protected void exibirMensagemCamposObrigatorios(List<String> requiredFields) {
		StringBuffer builder = new StringBuffer();
		if (requiredFields.size() > 0){
			builder.append(prop.getString("MN001") + "<br/>");	

			builder.append("<ul>");
			for (String str: requiredFields){
				builder.append("<li>" + str + "</li>");	
			}
			builder.append("</ul>");
			throw new CampoObrigatorioNaoInformadoException(builder.toString());
		} else {
		}
	}
	
	/**
	 * Metodo temporario, utilizado para o cadastro de mapa.
	 * @param requiredFields
	 */
	protected void exibirMensagemCamposObrigatoriosDePesquisa(List<String> requiredFields) {
		StringBuffer builder = new StringBuffer();
		if (requiredFields.size() > 0){
			builder.append(prop.getString("MN023") + "<br/>");	

			builder.append("<ul>");
			for (String str: requiredFields){
				builder.append("<li>" + str + "</li>");	
			}
			builder.append("</ul>");
			throw new CampoObrigatorioNaoInformadoException(builder.toString());
		} else {
		}
	}
	
	protected void addMensagemFalha(String msg){
	}
	
	protected void addMensagem(String msg){
	}
	
	
	protected void mostrarMensagemCamposObrigatorios(List<String> requiredFields) {
		StringBuffer builder = new StringBuffer();
		if ( requiredFields != null &&  !requiredFields.isEmpty()){
			builder.append(prop.getString("MN023") + "<br/>");	

			builder.append("<ul>");
			for (String str: requiredFields){
				builder.append("<li>" + str + "</li>");	
			}
			builder.append("</ul>");
			
			
			throw new CampoObrigatorioNaoInformadoException(builder.toString());
		} else {
		}
	}

	protected void mostrarMensagemCamposObrigatorios2(List<String> requiredFields) {
		StringBuffer builder = new StringBuffer();
		if ( requiredFields != null &&  !requiredFields.isEmpty()){
			builder.append(prop.getString("MN023") + "<br/>");	

			builder.append("<ul>");
			for (String str: requiredFields){
				builder.append("<li>" + str + "</li>");	
			}
			builder.append("</ul>");
		} 
	}

	
	protected void setAlgumCampoPreenchido(String msg) {
		StringBuffer builder = new StringBuffer();
		builder.append(prop.getString("MN027") + "<br/>");	
		builder.append("<ul>");
		builder.append("</ul>");
		throw new CampoObrigatorioNaoInformadoException(builder.toString());
	}
	
	public static void setMensagemFalha(String msg){
		throw new TRFException(msg); 
	}
	
	public ResourceBundle getProp() {
		return prop;
	}

	public ResourceBundle getPropProjeto() {
		return propProjeto;
	}
	
	
	public void montarMensagensSucesso(String codigoMensagem, Object[] mensagens){
		MessageFormat mf = new MessageFormat(getProp().getString(codigoMensagem));
		String msg =  mf.format(mensagens, new StringBuffer(), null).toString();
		
		setMensagemSucesso(msg);
	}
	
}

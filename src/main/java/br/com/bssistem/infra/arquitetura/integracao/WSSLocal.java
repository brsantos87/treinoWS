package br.com.bssistem.infra.arquitetura.integracao;

public class WSSLocal {

	private String url;
	private String local;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getComplete() {
		return url + local;
	}

}
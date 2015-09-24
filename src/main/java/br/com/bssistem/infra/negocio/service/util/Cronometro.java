package br.com.bssistem.infra.negocio.service.util;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro extends TimerTask {

	private int hora = 0;
	private int minuto = 0;
	private int segundo = 0;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @author bruno.santos
	 * @param args
	 * @throws
	 */
	public void run() {

		// System.out.println(hora + ":" + minuto + ":" + segundo);
		setTime(hora + ":" + minuto + ":" + segundo);

		segundo++;

		if (segundo == 59) {
			segundo = 0;
			minuto++;
			if (minuto == 59) {
				minuto = 0;
				hora++;
				if (hora == 23) {
					hora = 0;
				}
			}
		}

	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new Cronometro(), 1000, 1000);
		
	}
}

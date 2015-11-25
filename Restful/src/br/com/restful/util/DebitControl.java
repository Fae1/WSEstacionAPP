package br.com.restful.util;

import br.com.restful.controller.VeiculoController;

public class DebitControl extends Thread{
	private int horas;
	private String placa;
	
	public DebitControl(String placa, int horas) {
		this.horas = horas;
		this.placa = placa;
	}

	@Override
	public void run() {
		try {
			this.sleep(3600000*horas);
			System.out.println("Acabou, é teeeetra!\nValor horas: " + horas);
			new VeiculoController().pararCobranca(placa);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

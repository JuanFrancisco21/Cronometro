package controlador;

import contador.Contador;

public class ejecutable {
	public static void main(String[] args) {
		Contador c1 = new Contador();
		Thread t = new Thread(c1);
		
		t.start();
		while(true) {
			//System.out.println(c1.getFullHour());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

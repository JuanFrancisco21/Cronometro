package contador;

import java.util.Observable;

import controlador.PrimaryController;

public class Contador extends Observable implements Runnable{
	private String fullHour = "";
    private static int second=0;
    private static int hour=0;
    private static int minute=0;
	private static Boolean cronometroActivo;
	
	public Contador() {
 		this.fullHour = "";
		second = 0;
		hour = 0;
		minute = 0;
		cronometroActivo=false;
	}
	public Contador(String fullHour, int second, int hour, int minute) {
		super();
		this.fullHour = fullHour;
		second = 0;
		hour = 0;
		minute = 0;
	}
	
	
	public String getFullHour() {
		return fullHour;
	}
	public void setFullHour(String fullHour) {
		this.fullHour = fullHour;
	}
	public static Boolean getCronometroActivo() {
		return cronometroActivo;
	}
	public static void setCronometroActivo(Boolean cronometroActiv) {
		cronometroActivo = cronometroActiv;
	}



	public static void ResetTime() {
    	second=0;
        hour=0;
        minute=0;
    }

	public String printTime(int hour, int minute, int second) {
	    	fullHour="";
	        fullHour += (hour > 9) ? ":" + hour : "0" + hour;
	        fullHour += (minute > 9) ? ":" + minute : ":0" + minute;
	        fullHour += (second > 9) ? ":" + second : ":0" + second;
	        setChanged();
	        notifyObservers(fullHour);
	        return fullHour;
    }

	public void run() {
		while (true) {
			try {
				System.err.print("");
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			while (cronometroActivo) {
				second++;
				if (second > 59) {
					second = 0;
					minute++;
					if (minute > 60) {
						minute = 0;
						hour++;
						if (hour > 24) {
							System.out.println("Are you crazy?");
						}
					}
				}
				try {
					this.printTime(hour, minute, second);
					Thread.sleep(1000);
					while (cronometroActivo) {
						wait();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
		}
		
	}
	
}

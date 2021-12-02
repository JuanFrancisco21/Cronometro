package controlador;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import contador.Contador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable, Observer{
	static String time;
    @FXML
    Text fulltime= new Text();
    @FXML
    Label labelTitleBase;
    @FXML
    Label labelTitleTimes;
    @FXML 
    Label labelTimes;
    @FXML
    Button btnStop;
    @FXML
    Button btnStart;


	static Contador c1 = new Contador();
	static Thread t = new Thread(c1);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//t.start();
		c1.addObserver(this);
		
	}
	/**
	 * Funcion para iniciar hilo el cual empezará a contar
	 */
    @FXML
    public void onClickStartButton() {
    	if (!c1.getCronometroActivo() ) {
    		if (t.isAlive()) {
				t.resume();
			}else {
				t.start();
			}
    		Contador.setCronometroActivo(true);
    		fulltime.setText("00:00:00");
    		btnStart.setDisable(true);
    		btnStart.setText("CONTANDO");
    		btnStop.setDisable(false);
		}

    }

    /**
     * Metodo para detener el contador hasta que lo reanudemos
     */
    @FXML
    public void onClickStopButton() {
    	if (Contador.getCronometroActivo()) {
    		Contador.setCronometroActivo(false);
    		t.interrupt();
    		btnStop.setText("Reanudar");
        	btnStart.setText("DETENIDO");
		}else if (!Contador.getCronometroActivo()) {
			Contador.setCronometroActivo(true);
			t.resume();
			btnStop.setText("Parar");
	    	btnStart.setText("CONTANDO");
		}
    }

    /**
     * Función para setear los valores del contador a 0
     */
    @FXML
    public void onClickResetButton() {
		fulltime.setText("00:00:00");
		btnStop.setDisable(true);
		btnStart.setDisable(false);
    	btnStart.setText("Iniciar");
    	btnStop.setText("Parar");
    	Contador.setCronometroActivo(false);
    	Contador.ResetTime();
    	t.interrupt();
    }
    
    /**
     * Función para cerrar el hilo contador al cerrar el programa
     */
	public static void cerrarHilo() {
		t.stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		fulltime.setText(c1.getFullHour());
		
	}






}

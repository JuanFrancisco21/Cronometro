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
		t.start();
		c1.addObserver(this);
		
	}
    @FXML
    public void onClickStartButton() {
    	if (!c1.getCronometroActivo()) {
    		Contador.setCronometroActivo(true);
    		fulltime.setText("00:00:00");
    		btnStart.setDisable(true);
    		btnStart.setText("CONTANDO");
    		btnStop.setDisable(false);
		} 

    }

    @FXML
    public void onClickStopButton() {
    	if (Contador.getCronometroActivo()) {
    		Contador.setCronometroActivo(false);
    		btnStop.setText("Reanudar");
        	btnStart.setText("DETENIDO");
		}else if (!Contador.getCronometroActivo()) {
			Contador.setCronometroActivo(true);
			btnStop.setText("Parar");
	    	btnStart.setText("CONTANDO");
		}
    }

    @FXML
    public void onClickResetButton() {
		fulltime.setText("00:00:00");
		btnStop.setDisable(true);
		btnStart.setDisable(false);
    	btnStart.setText("Iniciar");
    	Contador.setCronometroActivo(false);
    	Contador.ResetTime();
    }
    
	public static void cerrarHilo() {
		t.stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		fulltime.setText(c1.getFullHour());
		
	}






}

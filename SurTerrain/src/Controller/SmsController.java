/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author ArwaBj
 */
public class SmsController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private TextField textsender;
    @FXML
    private TextField textnum;
    @FXML
    private TextArea txtmess;
    @FXML
    private Button envoisms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void envoi(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == envoisms) {
        try {
			// Construct data
			String apiKey = "apikey=" + txtid.getText();
			String message = "&message=" + txtmess.getText();
			String sender = "&sender=" +textsender.getText() ;
			String numbers = "&numbers=" + textnum.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
                       JOptionPane.showMessageDialog(null, "message"+line);

			}
			rd.close();
			
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                JOptionPane.showMessageDialog(null, e);

		}
    }
    }  
}

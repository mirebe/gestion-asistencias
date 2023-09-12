package pe.gob.sunat.gestion.asistencias.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.util.IconosAwesome;

/**
 *
 * @author mireb
 */
public class DialogoMensajeController implements Initializable{
    
    @FXML
    private Label lblMensaje,lblTituloInterface;
    @FXML
    private FontAwesomeIcon lblIcono;
    @FXML
    private Button botonCancelar, botonAceptar;
    @FXML
    private AnchorPane panelFondoMensaje;

  
    @FXML
    public void onActionAceptar() {		
        close();
    }
     @FXML
    public void onActionCancelar() {		
        close();
    }
    
    @FXML
    public void onClick_btnSalir() {		
    }        
    

    public void open(AnchorPane contentMain) {
        parent = contentMain;
        AnchorPane.setBottomAnchor(panelFondoMensaje, 0.0);
	AnchorPane.setTopAnchor(panelFondoMensaje, 0.0);
	AnchorPane.setLeftAnchor(panelFondoMensaje, 0.0);
	AnchorPane.setRightAnchor(panelFondoMensaje, 0.0);
        
        System.out.println("::::: Open mensaje ");
        if(contentMain != null){
             contentMain.getChildren().add(panelFondoMensaje);
             AsignarValores();
        }
        System.out.println("::::: Open mensaje ");
    }

    private void close() {
        System.out.println("::::: cerrando mensaje ");
        if(parent != null){
            parent.getChildren().remove(panelFondoMensaje);
        }
	System.out.println("::::: cerrando mensaje ");
    }
    
    
    public void AsignarValores(String titulo,String mensaje,IconosAwesome type){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.icono = type;        
    }
    
    private void AsignarValores(){

        lblTituloInterface.setText(titulo);
        if(mensaje !=null){
            lblMensaje.setWrapText(true);
            lblMensaje.setText(mensaje);
            if(ancho > 80){
        	int cant = (ancho-80)/8;
        	int numLineas = mensaje.length()/cant;
        	lblMensaje.setPrefHeight(30*(numLineas+1));
            }
        }else{
             lblMensaje.setText("");
        }
        lblIcono.setIcon(  icono==null?IconosAwesome.INFO.getIcono():icono.getIcono());
        
        botonAceptar.setText(btnAceptarLabel==null?"":btnAceptarLabel);
        botonCancelar.setText(btnCerrarLabel==null?"":btnCerrarLabel);
        
        if (btnAceptarEvent == null){
            botonAceptar.setVisible(true);
            botonAceptar.setOnAction(null);
            botonAceptar.setOnAction((ActionEvent event) -> {
                close();
            }); 
        }else{
            botonAceptar.setVisible(true);
            botonAceptar.setOnAction((ActionEvent event) -> {
                try {
                        btnAceptarEvent.call();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
                close();
            }); 
        }
        
        if (btnCerrarEvent == null){
            botonCancelar.setVisible(false);
            botonCancelar.setOnAction(null);
        }else{
            botonCancelar.setVisible(true);
            botonCancelar.setOnAction((ActionEvent event) -> {
                try {
                    btnCerrarEvent.call();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                close();
            }); 
        }    
    }
    
    
    private String titulo = "Mensaje";
    private String mensaje ="";
    private IconosAwesome icono;
    private String btnAceptarLabel ="Aceptar";
    private String btnCerrarLabel = "Cancelar";
    private int ancho = 400;
    private Callable btnAceptarEvent;
    private Callable btnCerrarEvent;
    private AnchorPane parent;
    private int tipoDlg = 1;//1:primario,2:error,3:info,4:success
    

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setIcono(IconosAwesome icono) {
        this.icono = icono;
    }
    
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setTipoDlg(int tipoDlg) {
        this.tipoDlg = tipoDlg;
    }

    public String getBtnAceptarLabel() {
        return btnAceptarLabel;
    }

    public void setBtnAceptarLabel(String btnAceptarLabel) {
        this.btnAceptarLabel = btnAceptarLabel;
    }

    public String getBtnCerrarLabel() {
        return btnCerrarLabel;
    }

    public void setBtnCerrarLabel(String btnCerrarLabel) {
        this.btnCerrarLabel = btnCerrarLabel;
    }

    public Callable getBtnAceptarEvent() {
        return btnAceptarEvent;
    }

    public void setBtnAceptarEvent(Callable btnAceptarEvent) {
        this.btnAceptarEvent = btnAceptarEvent;
    }

    public Callable getBtnCerrarEvent() {
        return btnCerrarEvent;
    }

    public void setBtnCerrarEvent(Callable btnCerrarEvent) {
        this.btnCerrarEvent = btnCerrarEvent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 

}

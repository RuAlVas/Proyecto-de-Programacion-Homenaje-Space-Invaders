

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Intrucciones {
	private Pane panelInst;
	
	public Intrucciones (Pane panelInst) {
		this.panelInst = panelInst;
		//Creamos el panel y lo limpiamos.
		panelInst.getChildren().clear();
		iniciarTexto();
		iniciarBoton();
	}
	
	public void iniciarTexto() {
		//Iniciar texto.
		Text inicio = new Text("Instrucciones");
		Text cuerpo = new Text ("Use las flechas dirrecionales (Izquierda) y (Derecha) \n para mover al avatar que se encuentra en la parte inferior de la pantalla.\n\n La barra espaciadora efectúa el disparo. \n\n Elimina a los enemigos antes de que ellos lo hagan. \n\n Sólo tienes 3 vidas."); 
		//Tamaño del texto
		inicio.setFont(new Font(50));
		cuerpo.setFont(new Font(20));
		//Color del texto
		cuerpo.setFill(Color.WHITE);
		inicio.setFill(Color.WHITE);
		//Posición del Texto
		inicio.xProperty().bind(panelInst.widthProperty().divide(2).subtract(inicio.getLayoutBounds().getWidth()/2));
		inicio.yProperty().bind(panelInst.heightProperty().divide(2).subtract(200));
		
		cuerpo.xProperty().bind(panelInst.widthProperty().divide(2).subtract(cuerpo.getLayoutBounds().getWidth()/2));
		cuerpo.yProperty().bind(panelInst.heightProperty().divide(2).subtract(50));
		
		//Añadir el texto al panel
		panelInst.getChildren().add(inicio);
		panelInst.getChildren().add(cuerpo);
	}

	// Iniciar boton
	private void iniciarBoton()
	{
		Button botonMenu = new Button("Volver al Menu");
		//Le damos un evento 
		botonMenu.setOnAction(new MenuHandler());
		//Su escala...
		botonMenu.setScaleX(2);
		botonMenu.setScaleY(2);
		//Definimos su posición.
		botonMenu.layoutXProperty().bind(panelInst.widthProperty().divide(2).subtract(botonMenu.widthProperty().divide(2)));
		botonMenu.layoutYProperty().bind(panelInst.heightProperty().divide(2).add(300));
		//Lo añadimos al panel.
		panelInst.getChildren().add(botonMenu);
	}

	class MenuHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			//Con este evento volvemos a la pantalla inicial.
			new PantallaInicial(panelInst);
		}
	}		
	
	

}

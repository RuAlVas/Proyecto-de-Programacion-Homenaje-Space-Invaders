// SAME AS OTHER SCREENS, BUT HAS BUTTONS THAT NAVIGATE TO OTHER SCREENS
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
//import TitleScreen.PlayHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;



public class PantallaInicial{
	private Pane panel;


	public PantallaInicial (Pane panel){
		this.panel = panel;
		panel.getChildren().clear();
		iniciarTexto();
		iniciarBotones();

	}

	
	private void iniciarTexto(){
		//Iniciamos los textos.
		Text titulo = new Text("Homenaje a");
		Text subtitulo = new Text("Space Invaders");
		//Definimos su tamaño y color.
		titulo.setFont(new Font(40));
		subtitulo.setFont(new Font(100));
		titulo.setFill(Color.GREY);
		subtitulo.setFill(Color.WHITE);
		//Definimos su posición.
		titulo.xProperty().bind(panel.widthProperty().divide(2).subtract(titulo.getLayoutBounds().getWidth()/2));
		titulo.yProperty().bind(panel.heightProperty().divide(2).subtract(150));

		subtitulo.xProperty().bind(panel.widthProperty().divide(2).subtract(subtitulo.getLayoutBounds().getWidth()/2));
		subtitulo.yProperty().bind(panel.heightProperty().divide(2).subtract(75));
		
		//LOs añadimos al panel
		panel.getChildren().add(titulo);
		panel.getChildren().add(subtitulo);
	}

	
	private void iniciarBotones(){
		//Iniciamos los tres botones.
		Button botonStart = new Button("Comenzar");
		Button botonIstrucciones = new Button("Instrucciones");
		Button salir = new Button("Salir");
		//Les damos un evento que más adelante ejecutaran. 
		botonStart.setOnAction(new PlayHandler());
		botonIstrucciones.setOnAction(new InstructionsHandler());
		salir.setOnAction(new QuitHandler());
		//Definimos su escala.
		botonStart.setScaleX(3);
		botonStart.setScaleY(3);
		botonIstrucciones.setScaleX(2);
		botonIstrucciones.setScaleY(2);
		salir.setScaleX(2);
		salir.setScaleY(2);
		//Definimos sus posiciones.
		botonStart.layoutXProperty().bind(panel.widthProperty().divide(2).subtract(botonStart.widthProperty().divide(2)));
		botonStart.layoutYProperty().bind(panel.heightProperty().divide(2).add(100));
		botonIstrucciones.layoutXProperty().bind(panel.widthProperty().divide(2).subtract(botonIstrucciones.widthProperty().divide(2)));
		botonIstrucciones.layoutYProperty().bind(panel.heightProperty().divide(2).add(200));
		salir.layoutXProperty().bind(panel.widthProperty().divide(2).subtract(salir.widthProperty().divide(2)));
		salir.layoutYProperty().bind(panel.heightProperty().divide(2).add(300));
		//Los añadimos al panel.
		panel.getChildren().add(botonStart);
		panel.getChildren().add(botonIstrucciones);
		panel.getChildren().add(salir);
	}
	
	
	class PlayHandler implements EventHandler<ActionEvent>{
		

		public void handle(ActionEvent e){
			// Inicia la pantalla del juego al presionar el botón "comenzar"
			new PantallaJuego(panel);
		}
	}
	class InstructionsHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e){
			//Inicia la pantalla de las instrucciones al presionar el botón "Instrucciones"
			new Intrucciones(panel);
		}
	}
	
	class QuitHandler implements EventHandler <ActionEvent>{
		
		public void handle(ActionEvent e) {
			//Esconde la ventana
			panel.getScene().getWindow().hide();
		}
	}
}
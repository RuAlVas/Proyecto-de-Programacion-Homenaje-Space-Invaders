

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOver {
	
	private Pane panelG = new Pane();
	private Button botonMenu = new Button ("Volver al Menú");
	
	public GameOver(Pane panelG) {
		
		
		panelG.getChildren().clear();
		this.panelG = panelG;
		iniciarTexto();
		iniciarBoton();
	}
	public void iniciarTexto () {
		Text gameover = new Text ("Juego Terminado");
		gameover.setFill(Color.WHITE);
		gameover.setFont(new Font(70));
		
		gameover.xProperty().bind(panelG.widthProperty().divide(2).subtract(gameover.getLayoutBounds().getWidth()/2));
		gameover.yProperty().bind(panelG.heightProperty().divide(2).subtract(gameover.getLayoutBounds().getHeight()/2));
		
		panelG.getChildren().add(gameover);
	}
	
	public void iniciarBoton() {
		botonMenu.setOnAction(new MenuHandler());
		
		botonMenu.setScaleX(2);
		botonMenu.setScaleY(2);
		
		botonMenu.layoutXProperty().bind(panelG.widthProperty().divide(2).subtract(botonMenu.widthProperty().divide(2)));
		botonMenu.layoutYProperty().bind(panelG.heightProperty().divide(2).add(100));
		
		panelG.getChildren().add(botonMenu);
	}
	
	class MenuHandler implements EventHandler <ActionEvent>{
		
		public void handle(ActionEvent e) {
			new PantallaInicial (panelG);
		}
	}
}


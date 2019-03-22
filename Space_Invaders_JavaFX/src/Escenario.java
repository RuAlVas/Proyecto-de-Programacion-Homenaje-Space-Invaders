import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Escenario extends Application
{
	public void start(Stage primaryStage)
	{
		Pane root = new Pane();
		root.setStyle("-fx-background: #000000;");
		Scene scene = new Scene(root, 800, 750, Color.BLACK);
		
		//Instancia a la Pantalla inicial en el root
		new PantallaInicial(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}

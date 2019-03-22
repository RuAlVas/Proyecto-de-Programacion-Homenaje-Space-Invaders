import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Enemigo extends Sprite
{
	private int tipo;
	private PantallaJuego pantallaJuego;

	public Enemigo(double offsetX, double offsetY, int tipo, PantallaJuego pantallaJuego, Pane panel)
	{
		//Creamos la apariencia de la bala heredando atributos de la clase Sprite
		super(32, 32, new Image("/Sprites/Alien.png", 32, 32, false, false), new Image("/Sprites/Alien.png", 32, 32, false, false), panel);
		this.tipo = tipo;
		
		
		cuerpo = new ImageView(imagen1);
		//Definimos la posición de la imagen y el hitbox que juntos crean al enemigo.
		cuerpo.xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
		cuerpo.yProperty().bind(panel.heightProperty().subtract(700).add(offsetY));
		
		hitbox.getLimite().xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
		hitbox.getLimite().yProperty().bind(panel.heightProperty().subtract(700).add(offsetY));
		//Añadimos el enemigo al panel
		panel.getChildren().add(hitbox.getLimite());
		panel.getChildren().add(cuerpo);

		this.offsetX = offsetX;
		this.offsetY = offsetY;

		this.pantallaJuego = pantallaJuego;

	}

	
	protected void enColisionBala(Sprite sprite)
	{
		
		if(sprite instanceof Bala)
		{
			
			Bala bala = (Bala)sprite;
			if(bala.getDireccion() == 1)
			{
				muerteBala();
			}
		}
	}

	public void muerteBala()
	{
		
		panel.getChildren().remove(hitbox.getLimite());
		panel.getChildren().remove(cuerpo);
		
		hitbox.getLimite().xProperty().bind(panel.widthProperty().subtract(panel.widthProperty().add(500)));
		
		vivo = false;
	}

	public void disparoEnemigo(ArrayList<Bala> shotList)
	{
		
		shotList.add(new Bala(this, -1, panel));
	}

	
	public int getTipo()
	{
		return tipo;
	}
}

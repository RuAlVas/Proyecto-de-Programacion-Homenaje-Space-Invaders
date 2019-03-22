
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Nave extends Sprite
{
	private boolean puedeDisparar = true;
	private PantallaJuego pantallaJuego;

	public Nave(PantallaJuego pantallaJuego, Pane panel)
	{
		//Creamos la apariencia de la nave heredando atributos de la clase Sprite
		super(60, 32, new Image("/Sprites/Player.png", 60, 32, false, false), new Image("/Sprites/Player.png", 60, 32, false, false), panel);
		cuerpo = new ImageView(imagen1);
		offsetX = -30;
		
		//Definimos la posición de la imagen y el hitbox que juntos crean la nave
		cuerpo.xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
		cuerpo.yProperty().bind(panel.heightProperty().subtract(40));
	
		hitbox.getLimite().xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
		hitbox.getLimite().yProperty().bind(panel.heightProperty().subtract(40));
		//Añadimos la nave al panel
		panel.getChildren().add(hitbox.getLimite());
		panel.getChildren().add(cuerpo);

		this.pantallaJuego = pantallaJuego;
	}

	@Override
	public void muerteBala()
	{
		panel.getChildren().remove(cuerpo);
		panel.getChildren().remove(hitbox.getLimite());
		hitbox.getLimite().xProperty().bind(panel.widthProperty().subtract(panel.widthProperty().add(100)));

		
		pantallaJuego.muerte();
	}

	@Override
	protected void enColisionBala(Sprite sprite)
	{
		muerteBala();
	}

	public void movimientoNave(int direction)
	{
		int velocidad = 4;
		
		if (offsetX >= -390 && offsetX <= 330)
		{
			offsetX += (velocidad * direction);
			cuerpo.xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
			hitbox.getLimite().xProperty().bind(panel.widthProperty().divide(2).add(offsetX));
		}

		else if(offsetX > 0)
		{
			offsetX = 330;
		}
		else
		{
			offsetX = -390;
		}
	}

	public void disparoNave(ArrayList<Bala> listaBalas)
	{
		if (puedeDisparar)
		{
			
			listaBalas.add(new Bala(this, 1, panel));
	
			puedeDisparar = false;
		}
	}

	public boolean getPuedeDisparar()
	{
		return puedeDisparar;
	}

	public void setPuedeDisparar(boolean puedeDisparar)
	{
		this.puedeDisparar = puedeDisparar;
	}

}

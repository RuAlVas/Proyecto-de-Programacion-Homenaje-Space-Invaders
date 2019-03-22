
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bala extends Sprite
{
	private int direccion;
	private Sprite auxiliar;

	public Bala(Sprite sprite, int direccion, Pane panel)
	{
		//Creamos la apariencia de la bala heredando atributos de la clase Sprite
		super(2, 8, new Image("/Sprites/PlayerShot.png", 2, 8, false, false), new Image("/Sprites/PlayerShot.png", 2, 8, false, false), panel);
		cuerpo = new ImageView(imagen1);
		offsetY = -36 * direccion;
		
		//Definimos la posición de la imagen y el hitbox que juntos crean la bala
		cuerpo.xProperty().bind(panel.widthProperty().divide(2).add(sprite.getOffsetX()).add(sprite.getHitbox().getLimite().getWidth()/2));
		cuerpo.yProperty().bind(sprite.cuerpo.yProperty().add(sprite.hitbox.getLimite().getHeight()/2).add(offsetY));
		
		hitbox.getLimite().xProperty().bind(panel.widthProperty().divide(2).add(sprite.getOffsetX()).add(sprite.getHitbox().getLimite().getWidth()/2));
		hitbox.getLimite().yProperty().bind(sprite.cuerpo.yProperty().add(sprite.hitbox.getLimite().getHeight()/2).add(offsetY));
		
		//Añadimos la bala al panel
		panel.getChildren().add(hitbox.getLimite());
		panel.getChildren().add(cuerpo);
		auxiliar = sprite;
		this.direccion = direccion;
	}


	protected void enColisionBala(Sprite sprite)
	{
		
		muerteBala();
	}

	public void muerteBala()
	{
		
		panel.getChildren().remove(hitbox.getLimite());
		panel.getChildren().remove(cuerpo);
		hitbox.getLimite().xProperty().bind(panel.widthProperty().subtract(panel.widthProperty()).add(100));
		vivo = false;
	}

	public void movimientoBala()
	{
		int velocidad = 8;
		offsetY -= (direccion * velocidad);
		cuerpo.yProperty().bind(auxiliar.cuerpo.yProperty().add(auxiliar.hitbox.getLimite().getHeight() / 2).add(offsetY));
		hitbox.getLimite().yProperty().bind(auxiliar.cuerpo.yProperty().add(auxiliar.hitbox.getLimite().getHeight() / 2).add(offsetY));
	}

	public int getDireccion()
	{
		return direccion;
	}

	public void addOffset(int x, int y)
	{
		//El offset es una compensación de" x" y de "y" que interactua con el hitbox y la imagen.
		offsetX += x;
		offsetY += y;

		cuerpo.xProperty().bind(panel.widthProperty().divide(2).add(auxiliar.getOffsetX()).add(auxiliar.getHitbox().getLimite().getWidth()/2));
		
		cuerpo.yProperty().bind(auxiliar.cuerpo.yProperty().add(auxiliar.hitbox.getLimite().getHeight()/2).add(offsetY));
		
		hitbox.getLimite().xProperty().bind(panel.widthProperty().divide(2).add(auxiliar.getOffsetX()).add(auxiliar.getHitbox().getLimite().getWidth()/2));
		hitbox.getLimite().yProperty().bind(auxiliar.cuerpo.yProperty().add(auxiliar.hitbox.getLimite().getHeight()/2).add(offsetY));
	}
}

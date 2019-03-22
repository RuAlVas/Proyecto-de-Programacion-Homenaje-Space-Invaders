
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Hitbox
{
	private Rectangle limite;
	private Sprite sprite;
	private Pane panel;

	public Hitbox(double width,double height, Sprite sprite, Pane panel)
	{
		//Cremos un rectangulo que actuará como un limite.
		limite = new Rectangle(0, 0, width, height);
		this.sprite = sprite;
	}

	protected void verColisionHitbox(Sprite sprite)
	{
		
		if (limite.intersects(sprite.getHitbox().getLimite().getBoundsInLocal()))
		{
		
			this.sprite.enColisionBala(sprite);
			sprite.enColisionBala(this.sprite);
		}
	}

	protected Rectangle getLimite()
	{
		return limite;
	}

	protected Pane getPanel()
	{
		return panel;
	}

	protected void setBoundaries(Rectangle limite)
	{
		this.limite = limite;
	}
}

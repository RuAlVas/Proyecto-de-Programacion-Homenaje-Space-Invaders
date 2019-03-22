import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite
{
	protected Image imagen1;
	protected Image imagen2;
	protected ImageView cuerpo;
	protected boolean vivo = true;
	protected Hitbox hitbox;
	protected Pane panel;
	
	// Los offset (tanto x como y) son una pequeña "compensación" que se le da a "x" y a "y", estos dependeran del sprite que se este usando ya que definen una relación entre el sprite y el hitbox.
	protected double offsetX = 0;
	protected double offsetY = 0;

	public Sprite(double width, double height, Image imagen1, Image imagen2, Pane panel)
	{
		
		hitbox = new Hitbox(width, height, this, panel);
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.panel = panel;
		
	}

	public void verColision(Sprite sprite)
	{
		
		hitbox.verColisionHitbox(sprite);
	}

	protected abstract void enColisionBala(Sprite sprite);

	public abstract void muerteBala();

	public Hitbox getHitbox()
	{
		return hitbox;
	}

	public ImageView getCuerpo()
	{
		return cuerpo;
	}

	public double getOffsetX()
	{
		return offsetX;
	}

	public double getOffsetY()
	{
		return offsetY;
	}

	public void setOffsetX(int offsetX)
	{
		this.offsetX = offsetX;
	}

	public void setOffsetY(int offsetY)
	{
		this.offsetY = offsetY;
	}
	public boolean estaVivo()
	{
		return vivo;
	}

}

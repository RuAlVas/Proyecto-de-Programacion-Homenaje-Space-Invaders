import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PantallaJuego
{
	private Pane panel;
	private ArrayList<ArrayList<Enemigo>> enemigos = new ArrayList<ArrayList<Enemigo>>();
	
	private boolean listoParaDisparar = false;
	private boolean borrarBalas = false;
	private double oportunidadDisparo = 0.003;
	private int vidas;
	private Nave nave;
	private Text textoVidas;
	private ArrayList<Bala> listaBalas = new ArrayList<Bala>();
	private EventHandler<ActionEvent> eventoAnimacionBala;
	private EventHandler<ActionEvent> eventoNaveizq;
	private EventHandler<ActionEvent> eventoNaveDer;
	private Timeline animacionBala;
	private Timeline animacionNaveIzq;
	private Timeline animacionNaveDer;

	
	public PantallaJuego(int vidas, Pane panel)
	{
		this.vidas = vidas;
		this.panel = panel;

		// Limpiar el panel y ejecutar las acciones.
		panel.getChildren().clear();
		nave = new Nave(this, panel);
		
		llenarFilas();
		iniciarEvento();
		iniciarAnimaciones();
		iniciarTexto();
	}

	public PantallaJuego(Pane panel)
	{
		this(3, panel);
	}

	// Iniciar texto.
	private void iniciarTexto()
	{
		textoVidas = new Text("Vidas: " + String.valueOf(vidas));
		
		textoVidas.setFont(new Font(30));
		
		textoVidas.setFill(Color.MEDIUMSEAGREEN);
		
		textoVidas.xProperty().bind(panel.widthProperty().divide(1.5).add(textoVidas.getLayoutBounds().getWidth()/2));
		textoVidas.yProperty().bind(panel.heightProperty().divide(16));
		
		panel.getChildren().add(textoVidas);
	}

	// Actualizar Texto
	private void actualizarTexto()
	{
		
		textoVidas.setText("Vidas: " + String.valueOf(vidas));
	}

	private void iniciarEvento()
	{
		
		eventoAnimacionBala = e ->
		{
			accionesBala();
		};

		// Mover la nave a la izquierda.
		eventoNaveizq = e ->
		{
			nave.movimientoNave(-1);
		};

		// Mover la nave a la derecha.
		eventoNaveDer = e ->
		{
			nave.movimientoNave(1);
		};

		// Acciones ejecutadas al presionar una tecla.
		panel.getScene().setOnKeyPressed(e ->
		{
			// La nave dispara con la barra espaciadora.
			if(e.getCode() == KeyCode.SPACE)
			{
				listoParaDisparar = true;
			}

			// La nave se mueve a la izquierda con la tecla direccional izquierda.
			else if(e.getCode() == KeyCode.LEFT)
			{
				animacionNaveIzq.play();
			}

			// La nave se mueve a la derecha con la tecla direccional derecha.
			else if (e.getCode() == KeyCode.RIGHT)
			{
				animacionNaveDer.play();
			}
		});

		// La navedeja de moverse al soltar las teclas.
		panel.getScene().setOnKeyReleased(e ->
		{
			if(e.getCode() == KeyCode.LEFT)
			{
				animacionNaveIzq.pause();
			}

			else if(e.getCode() == KeyCode.RIGHT)
			{
				animacionNaveDer.pause();
			}
		});


	}

	private void iniciarAnimaciones()
	{
		// Animacion de la bala.
		animacionBala = new Timeline(new KeyFrame(Duration.millis(15), eventoAnimacionBala));
			animacionBala.setCycleCount(Timeline.INDEFINITE);
			animacionBala.play();

		// Animación del movimiento hacia la izquierda.
		animacionNaveIzq = new Timeline(new KeyFrame(Duration.millis(33), eventoNaveizq));
		animacionNaveIzq.setCycleCount(Timeline.INDEFINITE);

		// Animación del movimiento hacia la izquierda.
		animacionNaveDer = new Timeline(new KeyFrame(Duration.millis(33), eventoNaveDer));
		animacionNaveDer.setCycleCount(Timeline.INDEFINITE);	
	}

	private void naveDisparo()
	{
		double posibilidad;
		// Ciclo para todos los enemigos.
		for(ArrayList<Enemigo> fila : enemigos)
		{
			// Ciclo para los enemigos en una fila.
			for(Enemigo enemigo : fila)
			{
				// Si el enemigo esta vivo...
				if(enemigo.estaVivo())
				{
					posibilidad = Math.random();
					if(posibilidad > (1 - oportunidadDisparo))
					{
						enemigo.disparoEnemigo(listaBalas);
					}
				}
			}
		}

	}


	private void accionesBala()
	{
		// Si el jugador esta listo para disparar.
		if(listoParaDisparar)
		{
			nave.disparoNave(listaBalas);
			// Definir que el jugador no esta listo para disparar.
			listoParaDisparar = false;
		}

		// borrar todas las balas...
		if(borrarBalas)
		{
			for(Bala bala : listaBalas)
			{
				bala.muerteBala();
			}
			borrarBalas = false;
		}

		naveDisparo();

		List<Integer> balasMuertas = new ArrayList<Integer>();
		for(Bala bala: listaBalas)
		{
			// Si la bala es "viva"
			if(bala.estaVivo())
			{
				bala.movimientoBala();
				
				if (bala.getOffsetY() > -650 && bala.getOffsetY() < 700)
				{
					for(ArrayList<Enemigo> fila : enemigos)
					{
						for(Enemigo enemigo : fila)
						{
							if (enemigo.estaVivo())
							{
								// Ver colision con el enemigo.
								bala.verColision(enemigo);
							}
						}
					}
				
					// Ver colisión con la nave
					bala.verColision(nave);
				}
				// Si la bala no le golpea a nada.
				else
				{
					// Eliminar a la bala
					bala.muerteBala();
					// Añadirla a la Lista de Balas muertas
					balasMuertas.add(listaBalas.indexOf(bala));
				}

			}
			// Si la bala esta muerta
			else
			{
				// Añadirla a la Lista de Balas muertas
				balasMuertas.add(listaBalas.indexOf(bala));
			}
		}
		/*//Ordenar la lista de balas muertas
		Collections.sort(balasMuertas);
		Collections.reverse(balasMuertas);*/
		
		// Para cada indice de balas muertas.
		for(Integer disparo : balasMuertas)
		{
			// Si la bala iba hacia arriba (por lógica,disparada por la nave)
			if(listaBalas.get(disparo.intValue()).getDireccion() == 1)
			{
				// Que la nave pueda volver a disparar
				nave.setPuedeDisparar(true);
			}
			// Remover la bala de la listade Balas
			listaBalas.remove(disparo.intValue());
		}
	}

	private void llenarFilas()
	{
		int tipo = 1;
		int ancho = 0;
		enemigos.add(new ArrayList<Enemigo>());
		enemigos.add(new ArrayList<Enemigo>());
		enemigos.add(new ArrayList<Enemigo>());
		
		for (int i = 0; i < 3; i++)
		{
			if (tipo == 1)
			{
				ancho = 8;
			}
			
			// Crear los aliens en una fila.
			for (int j = 0; j < 11; j ++)
			{
				int offsetX = -330 + ancho + (60 * j);
				int offsetY = 150 - (60 * i);
				enemigos.get(i).add(new Enemigo(offsetX, offsetY, tipo, this, panel));
			}
		}

	}


	public void muerte()
	{
		// Quitar una vida
		vidas -= 1;
		// Crear una nueva nave.
		nave = new Nave(this, panel);
		// Eliminar todas las balas
		borrarBalas = true;
		// Actualizar texto.
		actualizarTexto();
		// Finalizar el juego si el jugador ya no tiene vidas.
		if(vidas == 0)
		{
			finalizar();
		}
	}

	private void finalizar()
	{
		// Detener las animaciones.
		animacionBala.stop();
		// Si aun sobran vidas...
		if(vidas > 0)
		{
			// Iniciar otro juego.
			new PantallaJuego(vidas, panel);
		}
		// Si ya no quedan vidas.
		else
		{
			// LLamar a la Pantalla Game Over.
			new GameOver(panel);
		}
	}

}

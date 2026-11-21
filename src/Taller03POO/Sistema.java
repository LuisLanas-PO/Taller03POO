package Taller03POO;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	
	private final ArrayList<Monstruo> monstruos = new ArrayList<>();
	private final ArrayList<Jugador> jugadores = new ArrayList<>();
	private final LinkedList<ResultadoMinijuego> historial = new LinkedList<>();
	private final Minijuego[] minijuegos = { new CarreraDelCementerio(), new CazaDeCalabazas(), new LanzamientoDeLapidas(), new SobrevivirAlAmanecer() };

	public void agregarMonstruo(String[] campos) {
		
		if (campos.length == 0 || campos[0].trim().isEmpty()) {
			
			throw new IllegalArgumentException("Falta el tipo de monstruo");
			
		}

		String tipo = campos[0].trim();
		int cantidadEsperada;

		switch (tipo) {
		
		case "Zombi":
			
		case "Momia":
			
			cantidadEsperada = 6;
			break;
			
		case "Vampiro":
			
		case "HombreLobo":
			
			cantidadEsperada = 7;
			break;
			
		default:
			
			throw new IllegalArgumentException("Tipo desconocido: " + tipo);
			
		}

		if (campos.length != cantidadEsperada) {
			
			throw new IllegalArgumentException(tipo + " necesita " + cantidadEsperada + " campos");
			
		}

		int vida = leerAtributo(campos[1], "Vida", 200);
		int velocidad = leerAtributo(campos[2], "Velocidad", 100);
		int fuerza = leerAtributo(campos[3], "Fuerza", 100);
		int resistencia = leerAtributo(campos[4], "Resistencia", 100);

		switch (tipo) {
		
		case "Zombi":
			
			int mordida = leerAtributo(campos[5], "FuerzaDeMordida", 100);
			monstruos.add(new Zombi(vida, velocidad, fuerza, resistencia, mordida));
			break;
			
		case "Vampiro":
			
			int vuelo = leerAtributo(campos[5], "VelocidadDeVuelo", 100);
			int resistenciaAlSol = leerAtributo(campos[6], "ResistenciaAlSol", 100);
			monstruos.add(new Vampiro(vida, velocidad, fuerza, resistencia, vuelo, resistenciaAlSol));
			break;
			
		case "Momia":
			
			int vendajes = leerAtributo(campos[5], "LongitudVendajes", 300);
			monstruos.add(new Momia(vida, velocidad, fuerza, resistencia, vendajes));
			break;
			
		case "HombreLobo":
			
			int ferocidad = leerAtributo(campos[5], "Ferocidad", 100);
			int faseLunar = leerAtributo(campos[6], "FaseLunar", 4);
			monstruos.add(new HombreLobo(vida, velocidad, fuerza, resistencia, ferocidad, faseLunar));
			break;
			
		}
		
	}
	
	public ArrayList<Jugador> getJugadores() {
	    return new ArrayList<>(jugadores);
	}
	
	public ArrayList<Jugador> getRanking() {
		
	    ArrayList<Jugador> ranking = getJugadores();

	    for (int i = 0; i < ranking.size() - 1; i++) {
	    	
	        for (int j = 0; j < ranking.size() - 1 - i; j++) {
	        	
	            Jugador actual = ranking.get(j);
	            Jugador siguiente = ranking.get(j + 1);

	            boolean menorPuntaje = actual.getPuntajeMaximo() < siguiente.getPuntajeMaximo();

	            boolean empateDesordenado = actual.getPuntajeMaximo() == siguiente.getPuntajeMaximo() && actual.getNombre().compareToIgnoreCase(siguiente.getNombre()) > 0;

	            if (menorPuntaje || empateDesordenado) {
	            	
	            	ranking.set(j, siguiente);
	                ranking.set(j + 1, actual);
	                
	            }
	            
	        }
	        
	    }

	    return ranking;
	}
	
	public int getCantidadJugadores() {
	    return jugadores.size();
	}

	public int getCantidadMonstruos() {
		return monstruos.size();
	}

	public ArrayList<Monstruo> getMonstruos() {
		return new ArrayList<>(monstruos);
	}

	public Minijuego[] getMinijuegos() {
		return minijuegos.clone();
	}

	public void iniciarSesion() {
		historial.clear();
	}

	public ResultadoMinijuego getResultado(Minijuego minijuego) {
		
		for (ResultadoMinijuego resultado : historial) {
			
			if (resultado.getMinijuego() == minijuego) {
				
				return resultado;
				
			}
			
		}
		
		return null;
		
	}

	public ArrayList<Monstruo> getMonstruosDisponibles() {
		
		ArrayList<Monstruo> disponibles = new ArrayList<>();
		
		for (Monstruo monstruo : monstruos) {
			
			if (!monstruoYaUsado(monstruo)) {
				
				disponibles.add(monstruo);
				
			}
			
		}
		
		return disponibles;
		
	}

	public LinkedList<ResultadoMinijuego> getHistorial() {
		return new LinkedList<>(historial);
	}

	public int getPuntajeAcumulado() {
		
		int total = 0;
		for (ResultadoMinijuego resultado : historial) {
			
			total += resultado.getPuntaje();
		}
		
		return total;
		
	}

	public ResultadoMinijuego registrarPartida(Minijuego minijuego, Monstruo monstruo, String habilidad) {
		
		if (!esMinijuegoDelCatalogo(minijuego)) {
			
			throw new IllegalArgumentException("El minijuego debe pertenecer al menú");
			
		}
		
		if (getResultado(minijuego) != null) {
			
			throw new IllegalArgumentException("El minijuego ya fue jugado en esta sesión");
			
		}
		
		if (!esMonstruoDelCatalogo(monstruo) || monstruoYaUsado(monstruo)) {
			
			throw new IllegalArgumentException("El monstruo no está disponible en esta sesión");
			
		}

		String nombreHabilidad = "Ninguna";
		String mensajeHabilidad = null;
		boolean habilidadEfectiva = false;

		if (habilidad != null) {
			
			nombreHabilidad = habilidad;
			mensajeHabilidad = usarHabilidad(monstruo, habilidad);
			habilidadEfectiva = esHabilidadEfectiva(minijuego, habilidad);
			
		}

		double potencial = calcularPotencial(minijuego, monstruo);
		int puntaje = calcularPuntaje(potencial, habilidadEfectiva);
		ResultadoMinijuego resultado = new ResultadoMinijuego(minijuego, monstruo, nombreHabilidad, puntaje, mensajeHabilidad, habilidadEfectiva);
		historial.addLast(resultado);
		return resultado;
		
	}

	public double calcularPotencial(Minijuego juego, Monstruo monstruo) {
		return juego.calcularPotencial(monstruo);
		
	}

	public ArrayList<String> getHabilidades(Monstruo monstruo) {
		
		ArrayList<String> disponibles = new ArrayList<>();
		
		if (monstruo instanceof Mordedor) {
			
			disponibles.add(Habilidad.MORDER);
			
		}
		
		if (monstruo instanceof Volador) {
			
			disponibles.add(Habilidad.VOLAR);
			
		}
		
		if (monstruo instanceof Regenerable) {
			
			disponibles.add(Habilidad.REGENERARSE);
			
		}
		
		if (monstruo instanceof Furioso) {
			
			disponibles.add(Habilidad.ENFURECERSE);
			
		}
		
		return disponibles;
		
	}

	public String usarHabilidad(Monstruo monstruo, String habilidad) {
		
		if (Habilidad.MORDER.equals(habilidad) && monstruo instanceof Mordedor) {
			
			return ((Mordedor) monstruo).morder();
			
		}
		
		if (Habilidad.VOLAR.equals(habilidad) && monstruo instanceof Volador) {
			
			return ((Volador) monstruo).volar();
			
		}
		
		if (Habilidad.REGENERARSE.equals(habilidad) && monstruo instanceof Regenerable) {
			
			return ((Regenerable) monstruo).regenerarse();
			
		}
		
		if (Habilidad.ENFURECERSE.equals(habilidad) && monstruo instanceof Furioso) {
			
			return ((Furioso) monstruo).enfurecerse();
			
		}
		
		throw new IllegalArgumentException("El monstruo no tiene esa habilidad");
		
	}

	public boolean esHabilidadEfectiva(Minijuego juego, String habilidad) {
		
		return juego.getHabilidadPremiada().equals(habilidad);
		
	}

	public int calcularPuntaje(double potencial, boolean habilidadEfectiva) {
		
		double multiplicador = 1.0;
		
		if (habilidadEfectiva) {
			
			multiplicador = 1.5;
			
		}
		
		return (int) Math.round(potencial * 10 * multiplicador);
		
	}

	private boolean esMinijuegoDelCatalogo(Minijuego minijuego) {
		
		for (Minijuego delMenu : minijuegos) {
			
			if (delMenu == minijuego) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	private boolean esMonstruoDelCatalogo(Monstruo monstruo) {
		
		for (Monstruo delCatalogo : monstruos) {
			
			if (delCatalogo == monstruo) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	public boolean monstruoYaUsado(Monstruo monstruo) {
		
		for (ResultadoMinijuego resultado : historial) {
			
			if (resultado.getMonstruo() == monstruo) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	private int leerAtributo(String campo, String atributo, int maximo) {
		
		int valor;
		
		try {
			
			valor = Integer.parseInt(campo.trim());
			
		} catch (NumberFormatException e) {
			
			throw new IllegalArgumentException(atributo + " debe ser un número entero");
			
		}
		
		if (valor < 1 || valor > maximo) {
			
			throw new IllegalArgumentException(atributo + " debe estar entre 1 y " + maximo);
			
		}
		
		return valor;
		
	}
	
	public void agregarRegistro(String[] datos) {
		
	    if (datos.length != 2) {
	        throw new IllegalArgumentException("Se esperaba nombre;puntaje.");
	        
	    }

	    String nombre = datos[0].trim();
	    
	    if (nombre.isEmpty()) {
	    	
	        throw new IllegalArgumentException("El nombre está vacío.");
	        
	    }

	    int puntaje;
	    
	    try {
	    	
	        puntaje = Integer.parseInt(datos[1].trim());
	        
	    } catch (NumberFormatException e) {
	    	
	        throw new IllegalArgumentException("El puntaje debe ser un número entero.");
	        
	    }

	    if (puntaje < 0) {
	    	
	        throw new IllegalArgumentException("El puntaje debe ser mayor o igual a cero.");
	        
	    }

	    jugadores.add(new Jugador(nombre, puntaje));
	    
	}
	
	public int buscarRecord(String nombre) {
		
	    int record = -1;

	    for (Jugador jugador : jugadores) {
	    	
	        if (jugador.getNombre().equals(nombre) && jugador.getPuntajeMaximo() > record) {
	        	
	            record = jugador.getPuntajeMaximo();
	            
	        }
	        
	    }

	    return record;
	    
	}
	
	public boolean actualizarRecordDeSesion(String nombre) {
		
	    if (historial.isEmpty()) {
	    	
	        return false;
	        
	    }

	    int puntaje = getPuntajeAcumulado();
	    int recordAnterior = buscarRecord(nombre);

	    if (puntaje <= recordAnterior) {
	    	
	        return false;
	        
	    }

	    Jugador nuevoRegistro = new Jugador(nombre, puntaje);

	    for (int i = 0; i < jugadores.size(); i++) {
	    	
	        if (jugadores.get(i).getNombre().equals(nombre)) {
	        	
	            jugadores.set(i, nuevoRegistro);
	            return true;
	            
	        }
	        
	    }

	    jugadores.add(nuevoRegistro);
	    return true;
	    
	}
	
}
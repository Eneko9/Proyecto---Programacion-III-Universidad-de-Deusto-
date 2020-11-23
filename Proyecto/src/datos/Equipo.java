package datos;


import datos.Equipo;

import datos.Partido;


public abstract class Equipo implements DatoParaTabla {
	protected String nombre;
	protected double puntos;
	public static final boolean[] COLUMNAS_EDITABLES = { false, false, false };
	public static final String[] NOMBRES_COLUMNAS = { "Nombre", "Puntos", "Rebotes","Triples"};
	
	public Equipo( String nombre ) {
		this.nombre = nombre;
		init();
	}

	public void init() {
		puntos = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPuntos() {
		return puntos;
	}

	public void setPuntos(double puntos) {
		puntos += puntos;
	}

	public void incPuntos(double puntosMas) {
		puntos += puntosMas;
	}

	public abstract void calculaPartido( Partido p );

	public abstract boolean esMejorQue( Equipo e2 );

	public abstract String[] getCabs();

	public abstract int[] getCols();
	
	@Override
	public String toString() {
		return nombre + " - " + puntos + " puntos.";
	}
	
}

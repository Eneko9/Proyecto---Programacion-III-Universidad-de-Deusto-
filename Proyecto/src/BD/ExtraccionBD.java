package BD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import datos.Competicion;
import datos.Equipo;
import datos.Jugador;
import datos.Usuario;

public class ExtraccionBD {
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	public static ArrayList<Competicion> competiciones = new ArrayList<Competicion>();
	public static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	public static HashMap<Usuario, ArrayList<Equipo>> mapaUsEq = new HashMap<Usuario, ArrayList<Equipo>>();
	public static HashMap<Usuario, ArrayList<Competicion>> mapaUsComp = new HashMap<Usuario, ArrayList<Competicion>>();
	public static HashMap<String, ArrayList<Equipo>> mapaEqComp = new HashMap<String, ArrayList<Equipo>>();
	private static Logger logger = Logger.getLogger(ExtraccionBD.class.getName());
	private static final boolean ANYADIR_A_FIC_LOG = false; // poner true para no sobreescribir
	static {
		try {
			logger.addHandler(
					new FileHandler("src/logs/" + ExtraccionBD.class.getName() + ".log.xml", ANYADIR_A_FIC_LOG));
		} catch (SecurityException | IOException e) {
			logger.log(Level.SEVERE, "Error en creacion fichero log");
		}
	}

	public static void usuarioFavs(Usuario u) {
		u.setCompeticionesFavoritas(ExtraccionBD.mapaUsComp.get(u));
		u.setEquiposFavoritos(ExtraccionBD.mapaUsEq.get(u));
	}

	public static String getUsuarioPorCod1(int codigo) {
		String u = "lul";
		for (int i = 0; i < ExtraccionBD.usuarios.size(); i++) {

			if (ExtraccionBD.usuarios.get(i).getCodigo() == codigo) {
				u = ExtraccionBD.usuarios.get(i).getNombre();
			} else {
			}
		}
		return u;
	}

	public static Usuario getUsuarioPorCod2(int codigo) {
		Usuario u = new Usuario("null", "null", 100);
		for (int i = 0; i < ExtraccionBD.usuarios.size(); i++) {

			if (ExtraccionBD.usuarios.get(i).getCodigo() == codigo) {
				u = ExtraccionBD.usuarios.get(i);
			} else {
			}
		}
		return u;
	}

	public static Equipo getEquipoPorCod(int codigo) {
		Equipo e = new Equipo(0, "null");
		for (int i = 0; i < ExtraccionBD.equipos.size(); i++) {

			if (ExtraccionBD.equipos.get(i).getCodigo() == codigo) {
				e = ExtraccionBD.equipos.get(i);
			} else {
			}
		}
		return e;
	}

	public static void actualizarEquiposBD() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;
		try {

			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			rs1 = stm1.executeQuery("SELECT * FROM equipo");

			while (rs1.next()) {

				int cod_equipo = rs1.getInt(1);
				String nombre = rs1.getString(2);
				Equipo eq = new Equipo(cod_equipo, nombre);
				equipos.add(eq);
				logger.log(Level.INFO, "Equipo sacado de BD correctamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void addEquiposBD(int cod, String nombre) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;
		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO EQUIPO VALUES(" + cod + ",'" + nombre + "')");
			logger.log(Level.INFO, "Equipo a�adido a BD correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void addJugadoresBD(String n, int codequipo, int num, int edad) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;

		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO JUGADOR VALUES(" + num + ",'" + n + "'," + codequipo + "," + edad + ")");
			logger.log(Level.INFO, "Jugador a�adido a BD correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void addCompeticionBD(String n, int cod, int anyo, String pais, String c, String sc) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;

		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO COMPETICION VALUES(" + cod + ",'" + n + "','" + pais + "'," + anyo + ",'"
					+ c + "','" + sc + "');");

			logger.log(Level.INFO, "Competicion a�adida a BD correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// USUARIOS
	public static void addUsuarioBD(String nom, String contr, int cod) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;
		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO USUARIO VALUES(" + cod + ",'" + nom + "','" + contr + "')");
			logger.log(Level.INFO, "Usuario a�adido a BD correctamente.");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static String getContrUsuariosPorNom(String nombre) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		String contrasenya = "hola";
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT contrase�a FROM usuario WHERE nombre = '" + nombre + "';");
			while (rs1.next()) {
				contrasenya = rs1.getString("contrase�a");
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return contrasenya;
	}

	public static Integer getCodUsuariosPorNom(String nombre) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		int codigo = 0;
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT codigo FROM usuario WHERE nombre = '" + nombre + "';");
			while (rs1.next()) {
				codigo = rs1.getInt("codigo");
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return codigo;
	}

	public static ArrayList<String> getNomUsuarios() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT nombre FROM usuario;");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}

	public static ArrayList<Integer> getCodUsuarios() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> cods = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT codigo FROM usuario;");
			while (rs1.next()) {
				cods.add(rs1.getInt("codigo"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cods;
	}

	public static ArrayList<Integer> getCodEquiposPorCodUsuario(int codusuario) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> cods = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt
					.executeQuery("SELECT cod_equipo FROM usuarioequipo WHERE cod_usuario = " + codusuario + ";");
			while (rs1.next()) {
				cods.add(rs1.getInt("cod_equipo"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cods;
	}

	public static ArrayList<String> getNomCompsPorCodUsuario(int codusuario) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery(
					"SELECT nombre FROM competicion WHERE codigo IN (SELECT cod_competicion FROM usuariocompeticion WHERE COD_USUARIO = "
							+ codusuario + ");");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}

	// EQUIPOS
	public static ArrayList<String> getNomEquipos() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT nombre FROM equipo;");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}
	
	public static ArrayList<Equipo> getEquipos() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Equipo> eqs = new ArrayList<Equipo>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM equipo;");
			while (rs1.next()) {
				int cod_equipo = rs1.getInt("codigo");
				String nom_equipo = rs1.getString("nombre");
				eqs.add(new Equipo(cod_equipo, nom_equipo));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return eqs;
	}

	public static ArrayList<Integer> getCodEquipos() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> cods = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT codigo FROM equipo;");
			while (rs1.next()) {
				cods.add(rs1.getInt("codigo"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cods;
	}

	public static Equipo getEquipoPorNombre(String nombreEquipo) {

		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Equipo eq = null;
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM equipo WHERE nombre = '" + nombreEquipo + "';");
			while (rs1.next()) {
				int cod_equipo = rs1.getInt("codigo");
				String nom_equipo = rs1.getString("nombre");
				eq = new Equipo(cod_equipo, nom_equipo);
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return eq;
	}

	public static ArrayList<Integer> getEquiposDeComp(String nombreCompeticion) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> cods = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery(
					"SELECT cod_equipo FROM equipocompeticion WHERE cod_competicion = (select codigo from competicion where nombre = '"+ nombreCompeticion + "');");
			while (rs1.next()) {
				cods.add(rs1.getInt("codigo"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cods;
	}

	// COMPETICIONES
	public static Competicion getCompeticion(String nom, int anyo) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Competicion c = new Competicion(nom, 0, anyo, "", null, null);
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM competicion WHERE nombre = '"+nom+"' AND a�o = "+anyo+";");
			while (rs1.next()) {
				int cod_comp = rs1.getInt("codigo");
				String pais = rs1.getString("pais");
				String campeon = rs1.getString("campeon");
				String subcampeon = rs1.getString("subcampeon");
				Equipo camp = getEquipoPorNombre(campeon);
				Equipo subcamp = getEquipoPorNombre(subcampeon);
				c = new Competicion(nom, cod_comp, anyo, pais, camp, subcamp);
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return c;
	}
	public static ArrayList<String> getNomCompeticiones() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT nombre FROM competicion;");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}
	public static ArrayList<String> getNomCompeticionesPorAnyo(int anyo) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT NOMBRE FROM COMPETICION WHERE A�O ="+anyo+" ;");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}

	public static ArrayList<String> getNomCompeticionesPorAnyoUsuario(String nomUsuario,int anyo) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<String> noms = new ArrayList<String>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT NOMBRE FROM COMPETICION WHERE A�O ="+anyo+" AND CODIGO IN (SELECT COD_COMPETICION FROM usuariocompeticion WHERE COD_USUARIO = (SELECT CODIGO FROM USUARIO WHERE NOMBRE = '"+nomUsuario+"'))");
			while (rs1.next()) {
				noms.add(rs1.getString("nombre"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return noms;
	}

	public static ArrayList<Integer> getCodCompeticiones() {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> cods = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT codigo FROM competicion;");
			while (rs1.next()) {
				cods.add(rs1.getInt("codigo"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cods;
	}

	public static ArrayList<Integer> getAnyoCompeticion(String nomComp) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		ArrayList<Integer> anyos = new ArrayList<Integer>();
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT a�o FROM competicion WHERE nombre = '" + nomComp + "';");
			while (rs1.next()) {
				anyos.add(rs1.getInt("a�o"));
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return anyos;
	}
	public static String getPaisCompeticion(String nomComp) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		String pais = "";
		try {

			cn1 = conexion1.conectar();
			Statement stmt = cn1.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT pais FROM competicion WHERE nombre = '" + nomComp + "';");
			while (rs1.next()) {
				pais = rs1.getString("pais");
			}
			rs1.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (cn1 != null) {
					cn1.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pais;
	}

	public static void addUsuarioEquipoBD(Usuario u, Equipo eq) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;

		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO USUARIOEQUIPO VALUES(" + u.getCodigo() + "," + eq.getCodigo() + ")");
			ArrayList<Equipo> eqs = mapaUsEq.get(u);
			eqs.add(eq);
			// mapaUsEq.put(u, eqs);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void addUsuarioCompeticionBD(Usuario u, Competicion c) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;

		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeUpdate("INSERT INTO USUARIOEQUIPO VALUES(" + u.getCodigo() + "," + c.getCodigo() + ")");
			ArrayList<Competicion> comps = mapaUsComp.get(u);
			comps.add(c);
			mapaUsComp.put(u, comps);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void addEquipoCompeticionBD(Equipo eq, Competicion c) {
		Conexion conexion1 = new Conexion();
		Connection cn1 = null;
		Statement stm1 = null;
		ResultSet rs1 = null;

		try {
			cn1 = conexion1.conectar();
			stm1 = cn1.createStatement();
			stm1.executeQuery("INSERT INTO USUARIOEQUIPO VALUES(" + eq.getCodigo() + "," + c.getCodigo() + ")");

			ArrayList<Equipo> eqs = mapaEqComp.get(c.getNombre());
			eqs.add(eq);
			mapaEqComp.put(c.getNombre(), eqs);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se ha podido establecer conexion a la BD.");
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stm1 != null) {
					stm1.close();
				}
				if (cn1 != null) {
					cn1.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

package jeu;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import modele.*;
/* 
 * classe permettant de représenter les inputs des joueurs
 * exemple : joueur1 : [A+->] = code atq1 
 */
public class Joueur {
	private static int nbInstances = 0;
	protected int[][] inputs;  
	private int numeroJoueur;
	private final static int NB_TOUCHES = 6;
	private Personnage personnage;
	private int score;
	private String chaineScore;
	
	
	/** Construiure un joueur avec des inputs et un personnage donnee
	 * @param inputs les inputs
	 * @param p le personnage
	 */
	public Joueur(int[][] touches,Personnage p) {
		this.inputs = new int[3][];
		this.inputs[0] = new int[2];
		this.inputs[1] = new int[4];
		this.inputs[2] = new int[1];
		configurerInputs(touches);
		this.personnage = p;
		personnage.setProprietaire(this);
		nbInstances++;
		this.numeroJoueur = nbInstances;
	}
	
	/** Construiure un joueur avec un personnage donnee
	 * @param p le personnage 
	 */
	public Joueur(Personnage p) {
		inputs  = new int[3][];
		inputs[0] = new int[2];
		inputs[1] = new int[4];
		inputs[2] = new int[1];
		configurerInputs();
		this.personnage = p;
		personnage.setProprietaire(this);
		nbInstances++;
		this.numeroJoueur = nbInstances;
	}

	/** Configurer les inputs en fonction d'inputs donnees 
	 * @param inputs les inputs
	 */
	public void configurerInputs(int[][] touches) {
		for(int i = 0; i < inputs.length; i++) {
			for(int j=0;j<inputs[i].length;j++){
				this.inputs[i][j] = touches[i][j];
			}
		}
	}

	/** Configurer les inputs en fonction d'inputs par defaults */
	public void configurerInputs() {
		//inputs par defaut
		if (nbInstances == 0) {
			inputs[1][Mouvement.HAUT] = KeyEvent.VK_UP;
			inputs[1][Mouvement.BAS] = KeyEvent.VK_DOWN;
			inputs[1][Mouvement.DROITE] = KeyEvent.VK_RIGHT;
			inputs[1][Mouvement.GAUCHE] = KeyEvent.VK_LEFT;
			inputs[0][Attaque.A] = KeyEvent.VK_P;
			inputs[0][Attaque.B] = KeyEvent.VK_O;
			inputs[2][0] = KeyEvent.VK_SPACE;
		} else {
			inputs[1][Mouvement.GAUCHE] = KeyEvent.VK_Q;
			inputs[1][Mouvement.BAS] = KeyEvent.VK_S;
			inputs[1][Mouvement.DROITE] = KeyEvent.VK_D; 
			inputs[1][Mouvement.HAUT] = KeyEvent.VK_Z;
			inputs[0][Attaque.A] = KeyEvent.VK_A;
			inputs[0][Attaque.B] = KeyEvent.VK_E;
			inputs[2][0] = KeyEvent.VK_N;
		}

	}

	/** Modifie le score du joueur
	 * @param score le nouveau score du joueur
	 **/
	public void setScore(int score) {
		this.score = score;
	}

	/**Renvoie le score du joueur 
	 * @return int le score
	 */
	public int getScore() {
		return this.score;
	}

	/** Renvoie le personnage controle par le joueur 
	 * @return Personnage le personnage 
	 */
	public Personnage getPersonnage() {
		return this.personnage;
	}
	
	/** Incrementer le score du joueur*/
	public void incrementerScore() {
		score++;
	}


	/** Renvoie l'input correspondant au code de l'attaque donnee 
	 * @param code le code de l'attaque
	 * @return l'input correspondant au code
	 */
	public int getCodeAtq(int code) {
		return inputs[0][code];	
	}
	
	/** Renvoie l'input correspondant au code du mouvement donne 
	 * @param code le code du mouvement
	 * @return l'input correspondant au code
	 */
	public int getCodeMov(int code) {
		return inputs[1][code];	
	}
	
	/** Renvoie l'inputs correspondant a code de la pause
	 * @return l'inputs correspondant a code de la pause
	 */
	public int getCodePause() {
		return inputs[2][0];
	}
	
	/** Modifie le score
	 * @param chaine le score
	 */
	public void setChaineScore(String chaine) {
		this.chaineScore = chaine;
	}
	
	/** Retourne le score
	 * @return le score
	 */
	public String getChaineScore() {
		return getScore() + " " +chaineScore + (getScore() > 1 ? "s" : "");
	}
	
	/** Renvoie le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return "J"+numeroJoueur;
	}
}

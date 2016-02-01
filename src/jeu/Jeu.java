package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import modele.Attaque;
import modele.Personnage;
import modele.Plateforme;
import modele.Stage;

/* classe qui charge toutes les informations d'un jeu */

public class Jeu {
    //index de liste
    public final static int ATTAQUES = 0;
    public final static int PERSONNAGES = 1;
    public final static int PLATEFORMES = 2;
    //hauteur et largeur du jeu
    public final static int HAUTEUR_PX = 700;
    public final static int LARGEUR_PX = 1200;
    public final static int NB_VIES_MAX = 20;
	private List<Attaque> attaques;
	private List<Personnage> personnages;
	private List<Plateforme> plateformes;
    private Stage stage;
    private boolean pause;
    protected Joueur j1;
    protected Joueur j2;
    
	public Jeu() {
        pause = false;
		attaques = new LinkedList<Attaque>();
		personnages = new LinkedList<Personnage>();
		plateformes = new LinkedList<Plateforme>();
	} 
    
	/** Initialise le jeu
	 * @param j1 le premier joueur 
	 * @param j2 le deuxieme joueur 
	 * @param stage le stage pendant lequel le jeu se deroule
	 */
	public Jeu(Joueur j1, Joueur j2, Stage stage) {
    	this.j1 = j1;
    	this.j2 = j2;
    	initialiserScore();
    	pause = false;
        attaques = new LinkedList<Attaque>();
        personnages = new LinkedList<Personnage>();
        personnages.add(j1.getPersonnage());
        personnages.add(j2.getPersonnage());
        plateformes = stage.getPlateformes();
        this.stage = stage;
    } 

	/** Renvoie le joueur de numero de numJoueur 
	 * @param numJoueur le numero du joueur egale a 0 ou 1
	 * @return le joueur de numero numJoueur
	 */
    public Joueur getJoueur(int numJoueur) {
        if (numJoueur == 0)
            return j1;
        else
            return j2;
    }
    
    /** Renvoie l'hauter du jeu*/
    //on peut le faire dependre d'un stage
    public int getHauteur() {
        return HAUTEUR_PX;
    }

    /** Renvoie la largeur du jeu*/
    public int getLargeur() {
        return LARGEUR_PX;
    }

    /** Renvoie la liste des attaques du jeu*/
    public List<Attaque> getAttaques() {
    	return attaques;
    } //liste 0
    
    /** Renvoie la liste des Personnage du jeu*/
    public List<Personnage> getPersonnages() {
    	return personnages;
    } //liste 1
    
    /** Renvoie la liste des Plateforme du jeu*/
    public List<Plateforme> getPlateformes() {
    	return plateformes;
    } //liste 2
    
    /** Renvoie le stage du jeu 
     * @return Stage le stage du jeu
     */
    public Stage getStage() {
        return this.stage;
    }

    /** Indique la fin du jeu
     * @return true si le jeu est fini vrai sinon
     */
    public boolean estFini() {
    	boolean trouve = false;
    	/* fin du jeu quand un joueur a plus de vie */
    	if (j1.getScore() == 0 || j2.getScore() == 0) {
    		trouve = true;
    	}
    	return trouve;
    }
    

    /** Indique si le jeu est en pause
     * @return true si le jeu est en pause
     */
    public boolean pause() {
        return this.pause;	
    }

    /** Fait passer le jeu d'une etat de pause a l'etat opposee */
    public boolean togglePause() {
        this.pause = !this.pause;
        return this.pause;
    }

    /** Supprimer une attaque de la liste des attaques du jeu
     * @param atq l'attaque a supprimer
     */
    public void supprimer(Attaque atq) {
        attaques.remove(atq);
    }
    
    /** Supprimer un personnage de la liste des personnage du jeu
     * @param perso le personnage a supprimer
     */
    public void supprimer(Personnage perso) {
        personnages.remove(perso);
    }
    
    /** Supprimer une plateforme de la liste des plateforme du jeu
     * @param pla la plateforme a supprimer
     */
    public void supprimer(Plateforme pla) {
        plateformes.remove(pla);
    }

    /** Ajouter une attaque de la liste des attaques du jeu
     * @param atq l'attaque a ajouter
     */
    public void ajouter(Attaque atq) {
        attaques.add(atq);
    }
    
    /** Ajouter un personnage de la liste des personnage du jeu
     * @param p le personnage a ajouter
     */
    public void ajouter(Personnage p) {
        personnages.add(p);
    }
    
    /** Ajouter une plateforme de la liste des plateforme du jeu
     * @param p la plateforme a ajouter
     */
    public void ajouter(Plateforme p) {
        plateformes.add(p);
    }
    
    /** Compare des scores des joueurs
     * @param score1 le premier score
     * @param score2 le deuxieme score
     * @return 1 si score1 > score2
     *          0 s'il y a egalite
     *          -1 si score1 < score2
     */
    public int compareScore(int score1, int score2) {
    	if (score1 > score2)
    		return 1;
    	if (score1 == score2)
    		return 0;

    	return -1;
    }
    
    /** Decide le vainquer en fonction des scores des joueurs
     * @return j1 si joueur1 gagne  
     *          j2 si joueur2 gagne
     *          null s'il y a egalite
     */
    public Joueur getVainqueur() {
    	Joueur gagnant = null;
    	if (compareScore(j1.getScore(),j2.getScore()) > 0 ) {
    		gagnant = j1;
    	} else  {
    		if (compareScore(j1.getScore(),j2.getScore()) < 0){
        		gagnant = j2;
    		}
    	}
    	return gagnant; // null si match nul
     }
    
    /** Affecte le score du joueur en fonction du mode de jeu*/
    public void affecterScore(Joueur joueur) {
    	//mode vie, donc on decremente
    	joueur.setScore(joueur.getScore()-1);
    }
    
    
    /** Initialise les scores*/
    protected void initialiserScore() {
    	j1.setChaineScore("vie");
    	j1.setScore(NB_VIES_MAX);
    	j2.setScore(NB_VIES_MAX);
    	j2.setChaineScore("vie");
    }
    
    /** Renvoie la duree restante du jeu 
     * @return int -1 car dans ce mode de jeu 
     */
    public int getDuree() {
    	return -1;
    }
    
    /** Renvoie le nom du mode de vie*
     * @return String le nom du mode */
    public String getNom() {
    	return "Mode Vies";
    }
}
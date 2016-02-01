package jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Stage;

/* classe qui charge toutes les informations d'un jeu */

public class JeuTemps extends Jeu {
    public final static int TEMPS_JEU = 120; 
    private int temps;
    
    public JeuTemps(Joueur j1, Joueur j2, Stage stage) {
    	super(j1,j2,stage);
    	//on initialise un compteur de temps
    	temps = TEMPS_JEU;
    	demarrerTimer();
    } 

    
    
    /** Demarre le timer de jeu*/
    private void demarrerTimer() {
    	(new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pause()) {
					if (temps > 0) {
						temps--;				
					}
				}
			}
		})).start();
    }
    
    /** Indique qu ele jeu est fini 
     * @return boolean true si le jeu est fini false sinon
     */
    public boolean estFini() {
    	return (temps == 0);
    }

    /** Indique la duree de jeu restante
     * @return int la duree de jeu restante 
     */
    public int getDuree() {
    	return temps;
    }

    /** Affecte le score du joueur 
     * @param Joueur le joueur dont le score est affectee
     */
    public void affecterScore(Joueur joueur) {
    	//mode vie, donc on decremente
    	joueur.setScore(joueur.getScore()+1);
    }
    
    /** Compare des scores des joueurs
     * @param score1 le premier score
     * @param score2 le deuxieme score
     * @return 1 si score1 > score2
     *          0 s'il y a egalite
     *          -1 si score1 < score2
     */
    public int compareScore(int score1, int score2) {
    	if (score1 < score2)
    		return 1;
    	if (score1 == score2)
    		return 0;
    	
    	return -1;
    }
 
    /** Renvoie le nom du mode du temps*
     * @return String le nom du mode */
    public String getNom() {
    	return "Mode Temps";
    }
   
    /** Initialise les scores*/
    public void initialiserScore() {
    	j1.setChaineScore("mort");
    	j1.setScore(0);
    	j2.setChaineScore("mort");
    	j2.setScore(0);
    }
}
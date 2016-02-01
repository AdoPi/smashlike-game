package vue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.List;

import javax.swing.*;

import java.util.*;

import jeu.Jeu;
import jeu.Joueur;
import modele.*;

public class Vue extends JPanel {
	private boolean pause;
	private int nbPauses;
    private List<Afficheur> elementsAffichables;
    public Vue(int largeur, int hauteur){
        this.setPreferredSize(new Dimension(largeur,hauteur));
        elementsAffichables = new LinkedList<Afficheur>();
    }

    @Override
    public void paintComponent(Graphics g){
        	Iterator<Afficheur> it= elementsAffichables.iterator();
        	while(it.hasNext()) {
        		Afficheur element = it.next();
        		if (element.getDureeVie() == 0) {
            		it.remove();
            	} else {
            		//si pas de pause, on decremente la duree de vie
            		element.decrementerDureeVie();
                    element.afficher(g);
                }   		
        	}      		
    }

    /** Methode qui sert a forcer le rafraichissement
     */
    public void render() {
        //on supprime les afficheurs avec une duree de vie nulle
        //on ajoute les nouveaux afficheurs a la vue
        this.repaint();
    }

    /** Afficher la fin du jeu
     * @param vainqueur designe le vainquer du jeu
     */
    public void renderFinJeu(Joueur vainqueur) {
    	//on vide les affichables
    	elementsAffichables.clear();
    	//on ajoute l'affichage de fin de jeu
    	elementsAffichables.add(new FinJeuAfficheur(vainqueur));
    	this.repaint();
    }
    
    /** Mettre la partie en pause */
    public void togglePause() {
    	nbPauses = 0;
    	pause = !pause;
    }
    
    /** Ajouter un afficheur de joueur aux elements qui doivent etre affiches 
     * @param element le joueur a afficher
     */
    public void ajouter(Joueur element) {
    	elementsAffichables.add(new InformationsAfficheur(element));
    }
    
    /** Afficher le timer 
     * @param jeu le jeu pour lequel on veux voir le temps  
     */
    public void ajouterTimer(Jeu jeu) {
    	if (jeu.getDuree() > 0) {
    		elementsAffichables.add(new TimerAfficheur(jeu));   		
    	}
    }
    
    /** Ajouter un afficheur general
     * @param afficheur l'afficheur 
     **/     
    public void ajouter(Afficheur afficheur) {
        elementsAffichables.add(afficheur);
    }

    /** Ajouter un afficheur de personnage
     * @param element le personnage a ajouter
     */
    public void ajouter(Personnage element) {
        elementsAffichables.add(new PersonnageAfficheur(element));
    }

    /** Ajouter un afficheur de stage
     * @param stage le stage a ajouter
     */
    public void ajouter(Stage stage) {
        elementsAffichables.add(new StageAfficheur(stage));
        for(Plateforme p : stage.getPlateformes()) {
        	this.ajouter(p);
        }
    }

   /** Ajouter afficheur de mort
    * @param j le joueur dont le personnage est mort
    */
    public void ajouterMort(Joueur j) {
    	elementsAffichables.add(new MortAfficheur(j));
    }
    
    /** Ajouter afficheur de DoubleKO*/
    public void ajouterDoubleKO() {
    	elementsAffichables.add(new DoubleKoAfficheur());
    }
    
    /** Ajouter afficheur de projectile 
     * @param element le projectile a afficher
     **/
    public void ajouter(Attaque element) {
        elementsAffichables.add(new ProjectileAfficheur(element));
    }

    /** Ajouter afficher de plate-forme
     * @param element plateforme a afficher
     **/
    public void ajouter(Plateforme element) {
        elementsAffichables.add(new PlateformeAfficheur(element));
    }

}

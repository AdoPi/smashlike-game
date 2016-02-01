package menu;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import jeu.Jeu;
import jeu.Joueur;
import jeu.JoueurImmobile;
import vue.Vue;
import controller.Clavier;
import modele.Mai;
import modele.Personnage;
import modele.Rie;
import modele.Stage;
import modele.StageRue;
import modele.StageForet;


public class MenuTest {
	
	public static Dimension tailleFenetre = new Dimension(500,500);
	public static Dimension tailleIcones = new Dimension(150,150);
	
	public enum Mode{VIE,TEMPS,ENTRAINEMENT}
	
	public static Personnage[] personnagesDisponibles;
	public static Stage[] stagesDisponibles;
	
	public static JFrame fenetre = new JFrame("Mini-SmashBros");
	
	private static ContenuReinitialisable contenu1;
	private static ContenuReinitialisable contenu2;
	private static ContenuReinitialisable contenu3;
	private static ContenuReinitialisable contenu4;
	private static Container contenu5;
	private static ContainerChoixCommandes contenu6;
	
	//parametres du jeu
	private static Personnage personnage1;
	private static Personnage personnage2;
	private static Stage stage;
	private static Mode modeDeJeu;
	private static int[][] inputsJ1;
	private static int[][] inputsJ2;
	public static jeu.Moteur moteur;
	
	static{	
		//initialisation des personnages et de stages disponibles
				personnagesDisponibles = new Personnage[2];
				personnagesDisponibles[0] = new Mai();
				personnagesDisponibles[1] = new Rie();
				
				stagesDisponibles = new Stage[2];
				stagesDisponibles[0] = new StageRue();
				stagesDisponibles[1] = new StageForet();
				
				//initialisation des contenus
				contenu1 = new ContainerCommencer();
				contenu2 = new ContainerChoixMode();
				contenu3 = new ContainerChoixPersonnages();
				contenu4 = new ContainerChoixStage();
				contenu6 = new ContainerChoixCommandes();
	}
	
	public static void main (String[] args) {

		MenuTest.setContainer(1);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.pack();
		fenetre.setVisible(true);
	}
	
	public static void setContainer(int page) {
		boolean afficherJeu = false;
		ContenuReinitialisable contenu = null;
		switch (page) {
		case 1:
			contenu = MenuTest.contenu1;
			afficherJeu = false;
			break;
		case 2:
			contenu = MenuTest.contenu2;
			afficherJeu = false;
			contenu.reinitialiser();
			break;
		case 3:
			contenu = MenuTest.contenu3;
			afficherJeu = false;
			contenu.reinitialiser();
			break;
		case 4:
			contenu = MenuTest.contenu4;
			afficherJeu = false;
			contenu.reinitialiser();
			break;
		case 5:
			afficherJeu = true;
			break;
		case 6:
			afficherJeu = false;
			contenu = MenuTest.contenu6;
		default:
			break;
		}
		if (afficherJeu) {
			MenuTest.fenetre.setContentPane(MenuTest.contenu5);
		} else {
			MenuTest.fenetre.setContentPane(contenu);
		}
		MenuTest.fenetre.pack();
	}
	
	public static void setPersonnage1(Personnage p1) {
		personnage1 = p1;
	}
	public static void setPersonnage2(Personnage p2) {
		personnage2 = p2;
	}
	public static void setStage(Stage stage) {
		MenuTest.stage = stage;
	}
	public static void setMode (Mode mode) {
		MenuTest.modeDeJeu = mode;
	}
	public static void creerJeu() {
		//pour l'affichage
		//creation des joueurs
		Personnage premier = null;
		Personnage second = null;
		
		switch (personnage1.getNom()) {
		case "Rie":
			premier = new Rie();
			break;
		case "Mai":
			premier = new Mai();
			break;
		default:
			break;
		}
		switch (personnage2.getNom()) {
		case "Rie":
			second = new Rie();
			break;
		case "Mai":
			second = new Mai();
			break;
		default:
			break;
		}

		//on recupere les touches
		inputsJ1 = contenu6.getInputsJ1();
		inputsJ2 = contenu6.getInputsJ2();

		Joueur j1;		
		Joueur j2;
		//creation des joueurs en fonction du mode
		switch (MenuTest.modeDeJeu) {
		case ENTRAINEMENT:
			j1 = new Joueur(inputsJ1,premier);		
			j2 = new JoueurImmobile(second);
			break;
		default:
			j1 = new Joueur(inputsJ1,premier);		
			j2 = new Joueur(inputsJ2,second);
		//	j1 = new Joueur(premier);		
		//	j2 = new Joueur(second);
			break;
		}
		
		//creation d'un jeu (choix possible entre secondes et vie)
		Jeu jeu = new Jeu(j1,j2,stage);
		//creation d'une vue pour le jeu
		Vue vue = new Vue(jeu.getLargeur(),jeu.getHauteur());	
		vue.ajouter(stage);
		//creation d'un moteur de jeu
		moteur = new jeu.Moteur(jeu, vue, new Clavier());
		//lancement d'un frame avec la vue en panel principal
		contenu5 = new Container();
		contenu5.setLayout(new FlowLayout());
		contenu5.add(vue);
		//MenuTest.setContainer(5);
		JFrame fenetreJeu = new JFrame("Partie");
		fenetreJeu.setContentPane(contenu5);
		//fenetreJeu.setMinimumSize(new Dimension(Jeu.LARGEUR_PX,Jeu.HAUTEUR_PX));
		fenetreJeu.pack();
                fenetreJeu.setResizable(false);
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreJeu.setLocationRelativeTo(null);
		fenetreJeu.setVisible(true);


		vue.setFocusable(true);
		vue.requestFocus();
		vue.render();
		moteur.demarrer();
		fenetre.dispose();
	
	}

}
	

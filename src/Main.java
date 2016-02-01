import javax.swing.*;

import controller.Clavier;

import java.awt.*;

import modele.*;
import jeu.*;
import son.MakeSound;
import vue.*;

public class Main {
	/* TODO : ON DOIT POUVOIR TOUT PARAMETRER GRACE A DES MENUS */ 
	public static void main(String[] args) {
		//creation d'un stage
		Stage stage = new StageRue();
		//creation de deux personnages
		Personnage personnage1 = new Mai();
		personnage1.setPourcentage(0);
		Personnage personnage2 = new Rie();
		personnage2.setPourcentage(0);
		//creation des joueurs
		Joueur j1 = new Joueur(personnage1);
		Joueur j2 = new Joueur(personnage2);
		//creation d'un jeu (choix possible entre secondes et vie)
		Jeu jeu = new JeuTemps(j1,j2,stage);
		//creation d'une vue pour le jeu
		Vue vue = new Vue(jeu.getLargeur(),jeu.getHauteur());	
		vue.ajouter(stage);
		//creation d'un moteur de jeu
		jeu.Moteur moteur = new jeu.Moteur(jeu, vue, new Clavier());
		//lancement d'un frame avec la vue en panel principal
		JFrame fenetreJeu = new JFrame();
		fenetreJeu.setContentPane(vue);
		fenetreJeu.pack();
		fenetreJeu.setVisible(true);
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.render();
		moteur.demarrer();
	}
}

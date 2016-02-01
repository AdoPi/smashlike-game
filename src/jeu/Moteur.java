package jeu;

import java.util.Iterator;
import java.util.List;
import java.awt.event.*;

import javax.swing.*;

import collision.Collision;
import son.EffetsSonores;
import son.MakeSound;
import vue.*;
import modele.*;
import controller.*;

public class Moteur {
	public static final int TIMER_DELAY = 40; 
	public static final int H_SCREEN = 200;
	public static final int W_SCREEN = 70;
	private int frame;
	private Jeu jeu;
	private Clavier controlleur;
	private Vue vue;
	private boolean pauseDemandee;
	private physique.moteur.Moteur moteurPhysique;
	private Joueur j1,j2;	
	javax.swing.Timer timerFrame;
	public Moteur(Jeu jeu, Vue vue, Clavier controlleur) {
		frame = 0;
		this.jeu = jeu; 
		/* TODO : le jeu doit donner les positions de base */
		j1 = jeu.getJoueur(0);
		j2 = jeu.getJoueur(1);
		j1.getPersonnage().setPosition(Jeu.LARGEUR_PX/2 - 250,Jeu.HAUTEUR_PX/2);
		j1.getPersonnage().setOrientationDroite();
		j2.getPersonnage().setPosition(Jeu.LARGEUR_PX/2 + 250,Jeu.HAUTEUR_PX/2);
		j2.getPersonnage().setOrientationGauche();

		this.vue = vue;
		this.controlleur = controlleur;
		this.vue.addKeyListener(controlleur);
		this.vue.setFocusable(true);
		this.vue.requestFocus();
		moteurPhysique = new physique.moteur.Moteur(9.81*2*15/TIMER_DELAY,TIMER_DELAY/2);
		//ajout des elements dans la vue
		this.vue.ajouterTimer(jeu);
		this.vue.ajouter(j1.getPersonnage());
		this.vue.ajouter(j2.getPersonnage());
		this.vue.ajouter(j1);
		this.vue.ajouter(j2);
		EffetsSonores.jouer(jeu.getStage());
		vue.render();

	}
	
	/** Demarre le jeu*/
	public void demarrer() {
		timerFrame = new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!jeu.pause() && !jeu.estFini()) {
					avancer();
				} else {
					if (jeu.estFini()) {
						finir();
					} else {
						avancerPause();
					}
				}

			}
		});
		timerFrame.start();
	}

	/** Fini le jeu*/
	public void finir() {
		timerFrame.stop();
		//affichage de la fin du jeu
		Joueur vainqueur = jeu.getVainqueur();
		vue.renderFinJeu(vainqueur);
		//quitte le jeu apres un court delai
		try {
			new javax.swing.Timer(3000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);	
				}
			}).start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Gerer la pause */
	protected void avancerPause() {
		//on verifie les inputs pour savoir si on veut sortir de la pause
		controlleur.majInputsPressed();
		pauseDemandee = (pauseDemandee(j1) || pauseDemandee(j2));
		if (pauseDemandee) {
			jeu.togglePause();
			vue.togglePause();
			controlleur.viderBuffer();
		}
		controlleur.viderBufferPressed();
	}
	
	/** fonction permettant d'effectuer un tour de la boucle de jeu (frame)**/
	protected void avancer() {
		//on commence par mettre a jour les donnees du clavier
		controlleur.majInputs();
		if (pauseDemandee(j1) || pauseDemandee(j2)) {
			jeu.togglePause();
			vue.togglePause();
			controlleur.viderBufferPressed();
		} else  {
			executerFrame();
		}
	}
	
	
	/**Executer le contenu d'une frame  */
	private void executerFrame() {

		/* gestion des inputs */
		Attaque attaqueJ1 = effectuerAction(j1);
		Attaque attaqueJ2 = effectuerAction(j2);
		//on vide le buffer toutes les deux frames
		controlleur.viderBuffer();
		/* application de la physique et des regles du jeu */
		boolean attaqueLancee1 = j1.getPersonnage().lancerAttaque(attaqueJ1);
		boolean attaqueLancee2 = j2.getPersonnage().lancerAttaque(attaqueJ2);
		// si l'attaque est un projectile, on l'ajoute a la vue
		if (attaqueLancee1) {
			EffetsSonores.jouer(attaqueJ1);
			if (attaqueJ1.estProjectile()) {
				vue.ajouter(attaqueJ1);
			}
			jeu.ajouter(attaqueJ1);
		}

		if (attaqueLancee2) {
			EffetsSonores.jouer(attaqueJ2);
			if (attaqueJ2.estProjectile()) {
				vue.ajouter(attaqueJ2);
			}
			jeu.ajouter(attaqueJ2);
		}
		// on laisse le moteur physique effectuer les calculs de collisions
		List<Collision> collisions = moteurPhysique.majPhysique(jeu);

		// on applique les regles du jeu, apres calcul du moteur physique
		appliquerRegles(collisions);

		// decrementer duree de vie des objets
		supprimerObjetsMorts();

		// on decremente les etats des personnages
		decrementerEtats();

		// on met a jour l'affichage
		vue.render();

		// on incremente le numero de frame
		frame++;		
	}
	
	/** Supprimer les objets morts*/
	protected void supprimerObjetsMorts() {
		Iterator<Attaque> it = jeu.getAttaques().iterator();
		while(it.hasNext()) {
			Attaque attaque = it.next();
			if (attaque.getDureeDeVie() == 0) {
				attaque.finirAttaque();
				it.remove();
			}
		}
	}
	
	/** Appliquer les regle du jeu sur les objets en collision
	 * @param collisions la liste des collision 
	 */
	protected void appliquerRegles(List<Collision> collisions) {
		//si un personnage est touche par une atq, on applique les effets (etat)
		for(Collision collision : collisions) {
			int[] index = collision.getObjetPercutant();
			int[] indexPercute = collision.getObjetPercute();
			switch(index[0]) {
				case Jeu.ATTAQUES:
					Attaque atqCourante = jeu.getAttaques().get(index[1]);
					switch(indexPercute[0]) {
						case Jeu.ATTAQUES:
							
							//on applique l'effet de collison
							Point pointCollisions = collision.getEmplacementPoint();
							Attaque atqPercutee = jeu.getAttaques().get(indexPercute[1]);
							//en fonction de la priorite de l'attaque
							if (atqPercutee.getPriorite() >= atqCourante.getPriorite()) {
								atqPercutee.attaquer(atqCourante);
							} else {
								atqCourante.attaquer(atqPercutee);
							}
							//avec effet de destruction
							//vue.ajouter(new DestructionAfficheur());
							break;
						
						case Jeu.PLATEFORMES:
							atqCourante.attaquer(jeu.getPlateformes().get(indexPercute[1]));
							break;

						case Jeu.PERSONNAGES:
							//on inflige des degats au personnage
							if (!atqCourante.getProprietaire().equals(jeu.getPersonnages().get(indexPercute[1]))) {
								atqCourante.attaquer(jeu.getPersonnages().get(indexPercute[1]));
								EffetsSonores.jouer(jeu.getPersonnages().get(indexPercute[1]),frame);
							}
							break;
					}
					break;
				case Jeu.PLATEFORMES:
					switch(indexPercute[0]) {
						case Jeu.ATTAQUES:
							//on supprime l'attaque
							jeu.getAttaques().get(indexPercute[1]).attaquer(jeu.getPlateformes().get(index[1]));
							//ajout d'un effet de destruction
						break;
						case Jeu.PERSONNAGES:
						break;
						case Jeu.PLATEFORMES:
						break;
					}
					break;
				case Jeu.PERSONNAGES:
					switch(indexPercute[0]) {
						case Jeu.ATTAQUES:
							Attaque attaque = jeu.getAttaques().get(indexPercute[1]);
							if (!attaque.getProprietaire().equals(jeu.getPersonnages().get(index[1]))) {
								//on inflige des degats de l'attaque
								attaque.attaquer(jeu.getPersonnages().get(index[1]));
								EffetsSonores.jouer(jeu.getPersonnages().get(index[1]),frame);
							}
						break;
						case Jeu.PERSONNAGES:
						break;
						case Jeu.PLATEFORMES:
						break;	
					}
					break;
			}
		}
		//on verifie si un personnage sort d'une zone
		int nbMorts = 0;
		Personnage dernierMort = null;
		Point repop = new Point(jeu.getLargeur()/2,jeu.getHauteur()/2-250);
		for(Personnage p : jeu.getPersonnages()) {
			/* TODO : decaler le getX/Y? */
			Point position = p.getPosition();
			if (position.getX() > jeu.getLargeur() + W_SCREEN || 
				position.getY() > jeu.getHauteur() + H_SCREEN ||
				position.getX() < - W_SCREEN||
				position.getY() < - H_SCREEN) {
				//s'il est en dehors de la zone, on le fait mourir
				jeu.affecterScore(p.getProprietaire());
				//il repop au bon endroit
				p.repop(repop);
				repop.translater(50,0);
				dernierMort = p;
				nbMorts++;
			}
		}
		//on affiche la mort en fonction du nombre de mort
		switch(nbMorts) {
		case 1:
			vue.ajouterMort(dernierMort.getProprietaire());
			EffetsSonores.jouerMort();
			break;
		case 2:
			vue.ajouterDoubleKO();
			EffetsSonores.jouerMort();
			break;
		}
		
		for(Attaque a : jeu.getAttaques()) {
			Point position = a.getPosition();
			if (position.getX() > jeu.getLargeur() + W_SCREEN || 
				position.getY() > jeu.getHauteur() + H_SCREEN ||
				position.getX() < - W_SCREEN ||
				position.getY() < - H_SCREEN) {
				a.tuer();
			}
		}
	}

	/** Renvoie l'attaque a effectuer par le joueur
	 * @param joueur le joueur qui va effectuer l'attaque
	 * @return l'attaque
	 */
	private Attaque effectuerAction(Joueur joueur) {
			Attaque a = appliquerInputs(joueur);
			return a;	
	}

	/** Decremente la duree de vie d'une attaque*/
	protected void decrementerVies() {
		for(Attaque atq: jeu.getAttaques()) {
			//decrementer la vie de l'attaque
			atq.evoluerAttaque();
		}
	}

	/** Decremente de 1 les etats STUN , SUBIT_ATTAQUE , EN_ATTAQUE , INVULNERABLE 
	   * SAUT_EFFECTUE et EN_RECOVERY du personnage 
	   */
	protected void decrementerEtats() {
		for(Personnage p: jeu.getPersonnages()) {
			p.decrementerEtats();
		}
	}

	/** Indiquer si la pause est demandee par un joueur
	 * @param joueur le joueur qui demande une pause
	 */
	
	private boolean pauseDemandee(Joueur joueur) {
		boolean pause = false;
		if (controlleur.toucheAppuyee(joueur.getCodePause())) {
			pause = true;
		}
		return pause;
	}

	/** Retourne l'attaque lancee si elle existe en fopnction des inputs
	 * @param joueur le joueur qui va lancer l'attaque
	 * @return l'attaque lancee par le joueur si elle existe et null sinon
	 */
	protected Attaque appliquerInputs(Joueur joueur) {
		//on modifie le jeu en fonction de l'input donnee
		Personnage personnage = joueur.getPersonnage();	
		//on verifie si une touche de mouvement a ete selectionnee
		boolean mouvement = false;
		boolean attaque = false;
		Attaque attaqueExecutee = null;
		//detection saut
		if (controlleur.toucheAppuyee(joueur.getCodeMov(Mouvement.HAUT))) {
			attaque = true;
			if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.B))) {
				attaqueExecutee = personnage.getAttaque(Attaque.B_HAUT);
			} else {
				if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.A))) {
					attaqueExecutee = personnage.getAttaque(Attaque.A_HAUT);
				} else {
					//on est juste en mouvement
					personnage.effectuerMouvement(Mouvement.HAUT);
					mouvement = true;
				}
			}
		}

		if (!attaque && controlleur.toucheAppuyee(joueur.getCodeMov(Mouvement.BAS))) {
			attaque = true;
			//le joueur effectue un mouvement si aucune attaque est lancee
			if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.B))) {
				attaqueExecutee = personnage.getAttaque(Attaque.B_BAS);
			} else {
				if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.A))) {
					attaqueExecutee = personnage.getAttaque(Attaque.A_BAS);
				} else {
					//on est juste en mouvement
					personnage.effectuerMouvement(Mouvement.BAS);
					mouvement = true;
				}
			}
		}

		if (attaqueExecutee == null && (controlleur.toucheAppuyee(joueur.getCodeMov(Mouvement.GAUCHE)) ||
			controlleur.toucheAppuyee(joueur.getCodeMov(Mouvement.DROITE)))) {
			attaque = true;
			//le joueur effectue un mouvement si aucune attaque est lancee
			if (!mouvement && controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.B))) {
				//si on a appuye sur une attaque B
				attaqueExecutee = personnage.getAttaque(Attaque.B_COTE);
			} else {
				//si on veut faire une attaque A
				if (!mouvement && controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.A))) {
					attaqueExecutee = personnage.getAttaque(Attaque.A_COTE);
				} else {
					//on est juste en mouvement
					mouvement = true;
					if (controlleur.toucheAppuyee(joueur.getCodeMov(Mouvement.GAUCHE))) {
						personnage.effectuerMouvement(Mouvement.GAUCHE);
					} else {
					personnage.effectuerMouvement(Mouvement.DROITE);
					}
				}
			}
		}
		//si on est pas en mouvement, on execute une attaque normale
		if (!mouvement && !attaque) {
			if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.A))) {
				attaqueExecutee = personnage.getAttaque(Attaque.A);
				attaque = true;
			} else {
				if (controlleur.toucheAppuyee(joueur.getCodeAtq(Attaque.B))) {
					attaqueExecutee = personnage.getAttaque(Attaque.B);
					attaque = true;
				} else {
					//on ne fait rien 
				}
			}
		}

		if (attaque && joueur.getPersonnage().getEtat(Personnage.SUBIT_ATTAQUE) > 0)
			attaqueExecutee = null;
		if (joueur.getPersonnage().inactif())
			joueur.getPersonnage().setEtat(Personnage.EN_MOUVEMENT,0);
		
		return attaqueExecutee;
			
	}

}

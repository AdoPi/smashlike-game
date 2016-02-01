package controller;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * classe permettant de gerer les entrees tapees par un joueur
 * 
 * s'inspirer de ça :
 *       Keyboard keyboard = getKeyboard();
 *
 */
public class InputManager  {

//	private HashMap<String,Action> joueur1; // touches du joueur 1
//	private HashMap<String,Action> joueur2; // touches du joueur 2
//	private LinkedList<String> bufferJoueur1; //dernieres touches tappees par le joueur1
//	private LinkedList<String> bufferJoueur2; //dernieres touches tappees par le joueur2
//	public InputManager() {
//		bufferJoueur2 = new LinkedList<String>();
//		bufferJoueur1 = new LinkedList<String>();
//		joueur1 = new HashMap<String,Action>();
//		joueur2 = new HashMap<String,Action>();
//		//on remplit la table en fonction des inputs choisis
//	}
//
//	/* permet de recuperer les actions des joueurs */
//	public Action[] getActions() {
//		//on retourne les actions effectuees par chaque joueur
//		Action[] actionsJoueurs = new Action[2];
//		//on recupere toutes les touches du joueur 1
//		if (bufferJoueur1.empty()) {
//			actionsJoueurs[0] = new ActionRien();
//		} else {
//			actionsJoueurs[0] = getPremiereAction(joueur1);
//		}
//
//		//on recupere toutes les touches du joueur 2
//		if (bufferJoueur2.empty()) {
//			actionsJoueurs[1] = new ActionRien();
//		} else {
//			actionsJoueurs[0] = getPremiereAction(joueur2);
//		}
//		
//		//on vide les buffers
//		bufferJoueur1.clear();
//		bufferJoueur2.clear();
//		
//		return  actionsJoueurs;
//	}
//
//	private Action getPremiereAction(HashMap<String,Action> joueur) {
//		boolean actionTrouve = false;
//		Action actionEffectuee = new ActionRien();
//		Iterator<String> it = joueur.keySet().iterator();
//		String key = null;
//		String tmp = null;
//		while(!actionTrouve && it.hasNext()) {
//			//on recupere la touche associee
//			key = it.next();
//			/*
//			 si c'est une action valide, 
//			 on verifie si la touche suivante constitue une combinaison
//			*/
//			if (contientTouche(key,joueur)) {
//				//key est une action valide
//				actionTrouve = true;
//				String tmp = key;
//				key = it.next(); 
//				if (key != null) {
//					//on verifie si la touche suivante constitue une combinaison
//					boolean combinaison = contientTouche(key+""+tmp,joueur);
//					if (combinaison) {
//						actionEffectuee = joueur.get();
//					} else {
//						combinaison = contientTouche(tmp+""+key,joueur);
//						if (combinaison) {
//
//						} 
//						else {
//							//on a effectue une action simple
//							actionEffectuee = joueur.get();	
//						}
//					}
//				}
//			}
//		}
//
//		if (actionTrouve) {
//			return new ActionRien();
//		} else {
//			return actionEffectuee;	
//		}
//	}
//
//	public void keyTyped(KeyEvent e) {
//		
//	}
//
//	// public void keyTyped(KeyEvent e) {
//	// 	//on recupere la touche tapee 
//	// 	String toucheCourante = "" + e.getKeyChar();
//	// 	//et on l'ajoute dans le buffer du bon joueur
//	// 	if (contientTouche(toucheCourante,joueur1) {
//	// 		bufferJoueur1.add(toucheCourante);
//	// 	}
//		
//	// 	if (contientTouche(toucheCourante,joueur2)) {
//	// 		bufferJoueur2.add(toucheCourante);
//	// 	}
//	// }
//
//	// private boolean contientTouche(String touche, HashMap<String,Action> joueur) {
//	// 	Iterator<String> it = joueur.keySet().iterator();
//	// 	trouve = false;
//	// 	while(it.hasNext() && !trouve) {
//	// 		String combinaison = it.next();
//	// 		trouve = combinaison.toLowerCase().contains(touche.toLowerCase());			
//	// 	}
//
//	// 	return trouve;
//	// }
//
//	public void keyPressed(KeyEvent e) {}
//
//	public void keyReleased(KeyEvent e) {}

}

package modele;

import java.util.ArrayList;
import java.util.HashMap;

import jeu.Joueur;
import physique.Calculable;
import vue.Affichable;

public interface Personnage extends Affichable, Calculable {

	
	 /****** les etats possibles des personnages *****/
	  public final static int NB_ETATS = 8;
	  // le personnage peut se deplacer et attaquer
	  public final static int SUR_PLATEFORME = 0; //etat oppose a un etat en l'air
	  // le personnage ne peut ni attaquer ni se deplacer
	  public final static int STUN = 1;
	  // le personnage vient de sauter TODO : ne pas melanger duree saut et nbsaut 
	  public final static int SAUT_EFFECTUE = 2;
	  // le deplacement du personnage est reduit
	  public final static int SUBIT_ATTAQUE = 3;
	  // le personnage est en train d'attaquer
	  public final static int EN_ATTAQUE = 4;
	  // le personnage est invulnerable
	  public final static int INVULNERABLE = 5;
	  //le personnage est en mouvement
	  public final static int EN_MOUVEMENT = 6;
	  // le personnage ets en recovery
	  public final static int EN_RECOVERY = 7;

	
	
	
	  /** Renvoie le nombre de sauts disponibles du personnage, si c'est 0 le personnage ne peux plus sauter 
	   * @return le nombre de sauts disponible*/
	  public int getNbSauts();
	  
	  /** Decremente de 1 le nombre de sauts disopnibles pour le personnage*/
	  public void decrementerSaut();
	  
	  /** Renvoie le joueur qui controle le personnage
	   * @return le joueur qui controle le personnage
	   */
	  public Joueur getProprietaire();

	  /** Renvoie l'etat correspondant au code donne en parametre 
	   * @param codeEtat le code compris entre 0 et NB_ETATS
	   * @return l'etat correspondant au code donne en parametre 
	   **/
	  public int getEtat(int codeEtat);
	  
	  
	  /** Modifie l'etat correspondant au code donne en parametre
	   * @param codeEtat le code  compris entre 0 et NB_ETATS
	   * @param valeur la nouvelle valeure de l'etat 
	   **/
	  public void setEtat(int codeEtat, int valeur);

	  /**Renvoie la position du personnage
	   * @return la position du personnage
	   **/
	  public Point getPosition();
	  
	  /**Renvoi la position translatee du personnage utilisee pour l'affichage
	   * @return la position translatee du personnage
	   **/
	  public Point getPositionTranslatee();

	  /**Renvoie la vitesse courante du personnage*
	   * @return la vitesse courante du personnage
	   **/
	  public double[]  getVitesseCourante();

	  /**Modifie la vitesse courante du personnage
	   * @param x la vitesse horizentale
	   * @param y la vitesse verticale 
	   **/	  
	  public void setVitesseCourante ( double x , double y);

	  /**Renvoie le pourcentage du personnage
	   * @return le pourcentage du personnage*
	   **/
	  public double getPourcentage();

	  /**Indique si le peronnage est orientee vers la droite
	   * @return true si le personnage est orientee vers la droite false sinon
	   **/
	  public boolean estDroite();

	  /**Indique si le personnage est orientee vers la gauche
	   * @return true si le personnage est orientee vers la gauche false sinon 
	   **/
	  public boolean estGauche();
	  
	  /** Retourne true si estDroite() 
	   * @return true si le personnage est orientee vers la droite false sinon
	   **/
	  public boolean getOrientation();

	  /** Modifier le joueur qui controle le personnage */
	  public void setProprietaire(Joueur j);

	  /**Modifie la position du personnage
	   * @param x abcisse du personnage
	   * @param y ordonnee du personnage
	   */
	  public void setPosition( double x , double y);

	  
	  /**Modifie le pourcentage du personnage
	   * @param x le nouveau pourcentage
	   */
	  public void setPourcentage(double x);
	  
	  
	  /**Additioner une vitesse a la vitesse courante du personnage
	   * @param x vitesse horizontale 
	   * @param y vitesse verticale
	   */
	  public void additionnerVitesse( double x , double y);

	  /**Additionner un pourcentage au pourcentage du personnage
	   * @param x pourcentage a ajoutee
	   */
	  public void additionnerPourcentage ( double x);
	  
	  /**Modifie l'orientation du personnage en droite*/
	  public void setOrientationDroite();
	  
	  /**Modifie l'orientation du personnage en droite*/
	  public void setOrientationGauche();
	  
     
	  public boolean equals(Object obj) ; 

	  /** Lancer une attaque par un personnage
	   * @param attaque l'attaque qui va etre lancee
	   * @boolean indique si l'attaque a ete executee
	   */
	  public boolean lancerAttaque(Attaque attaque);

	  /** Effectue le mouvement correspendant au code Mvt
	   * @param codeMvt le code du mouvement a effectuer 0 < codeMvt < NB_MV
	   * @return true si la methode a ete bien executer et false sinon*/
	  public boolean effectuerMouvement(int codeMvt);
	  
	  /** Retourne l'attaque en cours du personnage
	   * @return l'attaque en cours */
	  public Attaque getAttaqueCourante();
	 
	  /**Retourne le code (Attaque.HAUT, ... ) de l'attaque getAttaqueCourante()
	   * @return le code de l'attaque en cours
	   */
	  public int getCodeAttaqueCourante();
	  
	  /** indique si un personnage est inactif (en fonction de ses etats)
	   * @return true si le personnage est inactif et false sinon
	   */
	  public boolean inactif();
	  
	  /**supprime une attaque de la liste des attaques courantes
	   * @param a l'attaque a supprimer de la liste des attaques courantes 
	   **/
	  public void finAttaque(Attaque a);
	  
	  

	  /**************** DEFINIR UN PERSONNAGE *********************/ 
	  /* methodes a redefinir, informations liees aux personnages */

	  //a redefinir pour creer un personnage avec des mvts differents
	  public Mouvement[] getMouvements();

	   /**Renvoie la masse du personnage
	    * @return la masse du personnage
	    **/
	   public double getMasse();
	   
	   /**Renvoie la vitesse maximale du personnage
	    * @return la vitesse maximale du personnage
	    **/
	   public double[]  getVitesseMax();

	   
	   /** Renvoie a partir de la liste des attaques possibles
	    * l'attaque du personnage correspandant a la commande recue
	    * @param attaque la commande recu */
	   public Attaque getAttaque(int codeAttaque);

	   /** Reinitialise le personnage au poit repop 
	    * @param repop la position dans laquelle le personnage sera initilalisee
	    */
	   public void repop(Point repop);

	  /** Decremente de 1 les etats STUN , SUBIT_ATTAQUE , EN_ATTAQUE , INVULNERABLE 
	   * SAUT_EFFECTUE et EN_RECOVERY du personnage 
	   */
	   public void decrementerEtats();
}

package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import jeu.Joueur;
import physique.Calculable;
import son.MakeSound;
import vue.*;


public abstract class PersonnageBase implements Personnage {


 

  /**** attributs prives ****/
  private LinkedList<Attaque> attaquesCourantes;
  private Attaque lastAttaque;
  private HashMap<Attaque,Integer> cooldowns;
  //private ArrayList<Attaque> attaquesDuPersonnage;
 

  private Point position;
  private double[] vitesseCourante = {0,0};
  private double pourcentage = 0;
  private int vies;
  private int[] etatsCourants;
  //nombre de sauts disponibles
  private int nbSautsDisponibles; //TODO a valider, raz au momment ou SUR_PLATEFORME revient
  //liste des etats du personnage
  private int[] durees;
  /* orientation du perconnsage (different du sens de deplacement)  */
  private boolean droite;
  private boolean gauche;
  private Joueur proprietaire;

  public PersonnageBase() {
	  nbSautsDisponibles = 2;
    /* on creer les informations qui ne dependant pas du personnage */
    attaquesCourantes = new LinkedList<Attaque>();
  //  attaquesDuPersonnage = new ArrayList<Attaque>();
    cooldowns = new HashMap<Attaque,Integer>();
    durees = new int[NB_ETATS];
    position = new Point(0,0);
    this.setVitesseCourante(0,0) ;
    this.setPourcentage(0);
    etatsCourants = new int[NB_ETATS];

}

  /* renvoie le nombre de sauts disponibles du personnage, si c'est 0 le personnage ne peux plus sauter */
  public int getNbSauts() {
    return nbSautsDisponibles;
  }
  
  public void decrementerSaut() {
	  nbSautsDisponibles = nbSautsDisponibles -1;
  }
  
  public Joueur getProprietaire() {
	  return proprietaire;
  }

  /* renvoie l'etat correspondant au code donne en parametre */
  public int getEtat(int codeEtat) {
    return etatsCourants[codeEtat];
  }
  /* modifie l'etat correspondant au code donne en parametre */
  public void setEtat(int codeEtat, int valeur) {
	  int valeurBis = valeur;
	  switch(codeEtat) {
	  	case SUR_PLATEFORME:
	  		if (valeur >0) {
	  			nbSautsDisponibles = 2;
	  		}
	  		break;
	  	case SUBIT_ATTAQUE: // si subit attaque et pas sur une plateforme; il a au moins un saut 
	  		if (valeur > 0) {
		  		if (getEtat(SUR_PLATEFORME) == 0) {
		  			if (nbSautsDisponibles <= 0) {
		  				nbSautsDisponibles = 1;
		  			}
		  		}	  			
	  		}

	  		break;
	  	case SAUT_EFFECTUE:
	  		if (valeur == -1) {
	  			nbSautsDisponibles = -1;
	  			valeurBis = 20;
	  		}
	  		break;
	  }
    	etatsCourants[codeEtat] = valeurBis;
    
  }

  /**Renvoie la position du personnage*/
  public Point getPosition(){
    return this.position;
  }
  
  /**Renvoi la position translatee du personnage*/
  public Point getPositionTranslatee() {
	  double trX = this.getCollider().getTailles()[0]/2;
	  double trY = this.getCollider().getTailles()[1]/2;
	  return new Point(position.getX()-trX,position.getY()-trY);
  }
  
  

  /**Renvoie la vitesse courante du personnage*/
  public double[]  getVitesseCourante(){
    return this.vitesseCourante;
  }

  /**Renvoie le pourcentage du personnage*/
  public double getPourcentage(){
    return this.pourcentage;
  }

  /**Indique si le peronnage est orientee vers la droite*/
  public boolean estDroite(){
    return this.droite;
  }

  /**Indique si le personnage est orientee vers la gauche*/
  public boolean estGauche(){
    return this.gauche;
  }

  public boolean getOrientation() {
      return estDroite();
  }

  public void setProprietaire(Joueur j) {
	  proprietaire = j;
  }
  /**Modifie la position du personnage
   * @param x abcisse du personnage
   * @param y ordonnee du personnage
   */
  public void setPosition( double x , double y){
	  this.position.setLocation(x, y);
  }

  
  /**Modifie le pourcentage du personnage
   * @param x le nouveau pourcentage
   */
  public void setPourcentage(double x){
	  this.pourcentage=x;
  }
  
  /**Modifie la vitesse courante du personnage
   * @param x vitesse horizontale
   * @param y vitesse verticale
   */
 
  public void setVitesseCourante ( double x , double y){
	  if (Math.abs(x)<=0.001) {
		  this.vitesseCourante[0] = 0;
	  } else {
		  this.vitesseCourante[0]=x;
	  }
	  if (Math.abs(y)<=0.001) {
		  this.vitesseCourante[1] = 0;
	  } else {
		  this.vitesseCourante[1]=y;
	  }
  }
  
  
  
  /**Additioner une vitesse a la vitesse courante du personnage
   * @param x vitesse horizontale 
   * @param y vitesse verticale
   */
  public void additionnerVitesse( double x , double y){
	  if(Math.abs(this.vitesseCourante[0]+x)<=0.001) {
		  this.vitesseCourante[0]=0; 
	  } else {
		  this.vitesseCourante[0]+=x;
	  }
	  if(Math.abs(this.vitesseCourante[1]+y)<=0.001) {
		  this.vitesseCourante[1]=0; 
	  } else {
		  this.vitesseCourante[1]+=y;
	  }
  }

  /**Additionner un pourcentage au pourcentage du personnage
   * @param x pourcentage a ajoutee
   */
  public void additionnerPourcentage ( double x) {
    this.pourcentage+=x;
  }
  
  /**Modifie l'orientation du personnage en droite*/
  public void setOrientationDroite(){
    this.droite = true  ;
    this.gauche = false ;
   }

  /**Modifie l'orientation du personnage en gauche*/
  public void setOrientationGauche(){
    this.droite = false ;
    this.gauche = true  ;
   }
  
  public void repop(Point repopPoint) {
	  setPosition(repopPoint.getX(),repopPoint.getY());
	  setVitesseCourante(0,0);
	  etatsCourants = new int [NB_ETATS];
	  setEtat(INVULNERABLE,10);
	  pourcentage = 0;
  }
  
  public void decrementerEtats() {
	  // decremente les etats a decrementer a chaque frame
	  if (getEtat(STUN)>0) {
		  setEtat(STUN,getEtat(STUN)-1);
	  }
	  if (getEtat(SUBIT_ATTAQUE)>0) {
		  setEtat(SUBIT_ATTAQUE,getEtat(SUBIT_ATTAQUE)-1);
	  }
	  if (getEtat(EN_ATTAQUE)>0) {
		  setEtat(EN_ATTAQUE,getEtat(EN_ATTAQUE)-1);
	  }
	  if (getEtat(INVULNERABLE)>0) {
		  setEtat(INVULNERABLE,getEtat(INVULNERABLE)-1);
	  }
	  
	  if (getEtat(SAUT_EFFECTUE)>0) {
		  setEtat(SAUT_EFFECTUE,getEtat(SAUT_EFFECTUE)-1);
	  }
	  if (getEtat(EN_RECOVERY)>0) {
		  setEtat(EN_RECOVERY,getEtat(EN_RECOVERY)-1);
	  }
	  
  }

  @Override
   public int hashCode() {
      return this.getNom().hashCode();
   }

   public boolean equals(Object obj) {
	   boolean egal;
    if (obj == null) {
    	egal = false;
    } else {
    	Personnage p = (Personnage) obj;
    	egal = p.getProprietaire().equals(this.getProprietaire());
    }
    return egal;
   }

   public void setNbVies(int nb) {
    this.vies = nb;
   }
   public int getNbVies() {
    return this.vies;
   }
   
   /** Renvoie la liste des attaques du personnage avec leurs cooldown */
   public HashMap<Attaque,Integer> getCooldown(){
	   return this.cooldowns;
   }
   

   /** Initialise un etat avec sa valeur initiale dependant du personnage 
    * @param codeEtat le code etat 
    **/
   public void intialise(int codeEtat) {
    this.etatsCourants[codeEtat] = durees[codeEtat];
   }



  /** Lancer une attaque par un joueur
   * @param attaque l'attaque qui va etre lancee
   * @boolean indique si l'attaque a ete executee
   */
  public boolean lancerAttaque(Attaque attaque){
	  boolean attaqueLancee;
    //seulement si possible
	  if ((getEtat(STUN) == 0) && (getEtat(EN_ATTAQUE) == 0) && (getEtat(EN_RECOVERY)==0) && (getEtat(SUBIT_ATTAQUE) == 0) && attaque!=null) {
		  setEtat(EN_ATTAQUE,attaque.getDureeDeVie());
		  setEtat(EN_RECOVERY,attaque.getDuree());
		  //ajout dans la liste des attaque courantes.
		  attaquesCourantes.add(attaque);
		  lastAttaque = attaque;
		  attaqueLancee = true;		

	  } else { 
		  attaqueLancee = false;
	  }
		return attaqueLancee;

  }

  /** Effectue le mouvement correspendant au code Mvt
   * @param codeMvt le code du mouvement a effectuer 0 < codeMvt < NB_MV
   * @return true si la methode a ete bien executer et false sinon*/
  public boolean effectuerMouvement(int codeMvt) {
    Mouvement[] mouvements = getMouvements();
    boolean mvOk = mouvements[codeMvt].appliquerMouvement(this);
    setEtat(Personnage.EN_MOUVEMENT, 1);
    /* TODO */
    return mvOk;
  }
  
  /** Retourne l'attaque en cours du personnage
   * @return l'attaque en cours */
  public Attaque getAttaqueCourante() {
		  return lastAttaque;
  }
  
  /**Retourne le code (Attaque.HAUT, ... ) de l'attaque getAttaqueCourante()
   * @return le code de l'attaque en cours
   */
  public int getCodeAttaqueCourante() {
 
	  Attaque attaqueCourante = getAttaqueCourante();
	  int i=0;
	  boolean trouve = false;
	  while (i < Attaque.NB_ATQ && !trouve) {
		  if (getAttaque(i).getNom().equals(attaqueCourante.getNom())) {
			  trouve = true;
		  } else {
			  i++;
		  }
	  }
	  	return i;
  }
  
  /** indique si un personnage est inactif (en fonction de ses etats)
   * @return true si le personnage est inactif et false sinon
   */
  public boolean inactif() {
	  boolean testEtats = (getEtat(STUN) == 0) && (getEtat(SUBIT_ATTAQUE)==0) && (getEtat(EN_ATTAQUE) == 0);
	  boolean testMvtSol = (getEtat(SUR_PLATEFORME)>0) && (getVitesseCourante()[0]==0);
	  boolean testMvtAir = (getEtat(SUR_PLATEFORME)==0) && (getEtat(SAUT_EFFECTUE)==0);
	  return (testEtats && (testMvtSol || testMvtAir));
  }
  
  /**supprime une attaque de la liste des attaques courantes
   * @param a l'attaque a supprimer de la liste des attaques courantes 
   **/
  public void finAttaque(Attaque a) {
	  attaquesCourantes.remove(a);
  }
  
  

  /**************** DEFINIR UN PERSONNAGE *********************/ 
  /* methodes a redefinir, informations liees aux personnages */

  //a redefinir pour creer un personnage avec des mvts differents
  public Mouvement[] getMouvements() {
    Mouvement[] mouvements = new Mouvement[Mouvement.NB_MV];
    mouvements[Mouvement.HAUT] = new Sauter();
    mouvements[Mouvement.BAS] = new NoMouvement();
    mouvements[Mouvement.GAUCHE] = new AvancerGauche();
    mouvements[Mouvement.DROITE] = new AvancerDroit();
  return mouvements;
  }

   /**Renoive la masse du personnage*/
   public abstract double getMasse();
   
   /**Renvoie la vitesse maximale du personnage*/
   public abstract double[]  getVitesseMax();

   
   /** Renvoie a partir de la liste des attaques possibles
    * l'attaque du personnage correspandant a la commande recue
    * @param attaque la commande recu */
   public abstract Attaque getAttaque(int codeAttaque);
   
}


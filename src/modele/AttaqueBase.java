package modele;


import java.util.LinkedList;

import physique.collider.Collider;

public abstract class AttaqueBase implements Attaque {

  private static int nbInstances = 0;
  protected Collider collider;
  protected Personnage proprietaire;
  protected double[] vitesse ;/*vitesse de l'attaque*/
  protected double degats ;/* le pourcentage rajoutee a l'impact*/
  protected double force ; /* carcterise la modification de vitesse du joueur touche*/
  protected int dureeDeVie;/* le nombre de frame restant pour l'attaque */
  protected Point position;
  protected int numInstance;
  protected int duree; /*la duree pendant laquelle un personnage lance est entrain d'executer une attaque*/
  protected int cooldown; /*la duree pendant laquelle un personnage ne peut pas relancer cette attaque */ 
  protected LinkedList<Personnage> personnagesTouches = new LinkedList<Personnage>();
  protected boolean orientation;

  public AttaqueBase(Personnage p, double damage,double str) {
	this.force = str;
	this.degats = damage;
	this.proprietaire = p;
	this.orientation = p.getOrientation();
    nbInstances++;
    this.numInstance = nbInstances;
  }
  /**Renvoie le collider de l'attaque
   * @return le collider de l'attaque
   **/
  public Collider getCollider() {
	  return this.collider;
  }
  
  /** Renvoie le personnage qui a lance l'attaque*/
  public Personnage getProprietaire() {
    return this.proprietaire ; 
  }

  /**Renvoie la vitesse de l'attaque*/
  public double[] getVitesseCourante() {
    return this.vitesse ; 
  }
  
  /** Renvoie la force de l'attaque : utilisee pour le calcule de l'ejection du personnage touche
   * par cette attaque
   * @return la force de l'attaque
   **/
  public double getDegats() {
	  return this.degats;
  }
  
  /** Renvoie la force de l'attaque : utilisee pour le calcule de l'ejection du personnage touche
   * par cette attaque
   * @return la force de l'attaque
   **/
  public double getForce() {
	  return this.force;
  }
  
  /** Renvoie la duree de vie d'une attaque
   * @return la duree de vie d'une attaque
   **/
  public int getDureeDeVie(){
	  return this.dureeDeVie;
  }
  
  /** Renvoie le personnage qui a lance l'attaque
   * @return le proprietaire de l'attaque
   **/
  public Point getPosition() {
	  return this.position;
  }
  
  /**Renvoi la position translatee de l'attaque*/
  public Point getPositionTranslatee() {
	  double trX = collider.getTailles()[0]/2;
	  double trY = collider.getTailles()[1]/2;
	  return new Point(position.getX()-trX,position.getY()-trY);
  }
  
  /**Renvoie la duree de l'attaque */
  public int getDuree(){
	  return this.duree;
  }
  
  /**Renvoie le cooldown de l'attaque */
  public int getCooldwon(){
	  return this.cooldown;
  }
  
  /**Renvoie l'orientation de l'attaque*/
  public boolean getOrientation() {
	  return this.orientation;
  }

  /**Modifier la vitesse de l'attaque**/
  public void setVitesse ( double x , double y ){
	  if (Math.abs(x)<=0.001) {
		  this.vitesse[0]=0;
	  } else {
		  this.vitesse[0]=x;
	  }
	  if (Math.abs(y)<=0.001) {
		  this.vitesse[1]=0;
	  } else {
		  this.vitesse[1]=y;
	  }
  }


  /**Modifie le collider de l'attaque 
   * @param c le collider
   **/
  public void setCollider(Collider c){
	  this.collider = c;
  }
  
  /**Modifie la position de l'attaque
   * @param x abcisse
   * @param y ordonnee
   */
    public void setPosition (double x,double y){
    	this.position.setLocation(x,y);
  }
    
    /**Modifie le proprietaire de l'attaque
     * @param p le nouveau proprietaire
     */
    public void setProprietaire(Personnage p) {
    	this.proprietaire = p;
    }
    
    /** modifie l orientation du personnage  
     * @param droite, true si oriente a droite
     * **/
    public void setOrientation(boolean droite) {
    	orientation = droite;
    }

    public boolean equals(Object obj) {
    	boolean egal = false;
    	Attaque a = (Attaque) obj;
    	if (a.getProprietaire().equals(this.getProprietaire())) {
    		if (a.getNom().equals(this.getNom())) {
    			egal = a.getDureeDeVie() == this.getDureeDeVie();
    		}
    	}
    return egal;
    } 
    
    /** Enleve l'attaque des attaques courantes de son proprietaire*/
    public void finirAttaque() {
		proprietaire.finAttaque(this);
	}
  
    /** Tuer l'attaque (duree de vie = 0)*/
	public void tuer() {
		finirAttaque();
		this.dureeDeVie = 0;
	}
	
	/** Dans le cas de d'une attaque qui en attaque une autre,
     * permet de savoir laquelle des deux doit l'emporter
     * @return -1 si pas prioritaire, a redefinir ensuite pour avoir des priorites plus elevees*
     */ 
	public int getPriorite() {
		return -1;
	}
}
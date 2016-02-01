package modele;

import physique.*;
import physique.collider.Collider;
import vue.*;

public abstract class PlateformeBase implements Plateforme {

private double largeur;
  private double hauteur;
  private boolean traversable;
  private Point position;
  private Collider collider;
  private String nom;
  
  public PlateformeBase(double largeur, double hauteur, boolean traversable, Point p, Collider collider,String nom) {
	  this.largeur = largeur;
	  this.hauteur = hauteur;
	  this.traversable = traversable;
	  this.position = p;
	  this.collider = collider;
	  this.nom = nom;
  }
  
  /**Renvoie la longueur de la platforme*/
  public double getLargeur(){
    return this.largeur;
  }

  /**Renvoie la largeur de la platforme*/
  public double getHauteur(){
    return this.hauteur;
  }

  /**Indique si la platfrome est traversable ou non*/
  public boolean estTraversable(){
    return this.traversable;
  }

  /**Renvoie la position du centre */
  public Point getPosition() {
	return this.position;
}
  
  /**Renvoi la position translatee de la plateforme*/
  public Point getPositionTranslatee() {
	  double trX = collider.getTailles()[0]/2;
	  double trY = collider.getTailles()[1]/2;
	  return new Point(position.getX()-trX,position.getY()-trY);
  }

  public int hashCode() {
    return this.nom.hashCode();
  }

  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (! (obj instanceof Plateforme)) {
      return false;
    }
      Plateforme compare = (Plateforme)obj;
      return compare.getNom().equals(this.nom);
   }


public String getNom() {
	return this.nom;
}


public double[] getVitesseCourante() {
	double[] vitesse = {0,0};
	return vitesse;
}


public Collider getCollider() {
	//on retourne toujours un rectangle ?
	return this.collider;
}

}



package modele;

import physique.Calculable;
import physique.collider.Collider;
import vue.Affichable;

public interface Plateforme extends Calculable, Affichable {
	 /**Renvoie la longueur de la platforme*/
	  public double getLargeur();

	  /**Renvoie la largeur de la platforme*/
	  public double getHauteur();

	  /**Indique si la platfrome est traversable ou non*/
	  public boolean estTraversable();

	  /**Renvoie la position du centre */
	  public Point getPosition();

}
package physique.collider;

import modele.Point;
/** Cercle est un collider 
 * Cette classe permet de savoir si un cercle est en contact avec un autre collider
 * @author Rochereau Luca
 */
public class Cercle implements Collider {
	double rayon;
	Point centre;
	
	/** Constructeur du collider Cercle
	 * @param centreCercle le Point2D centre du Cercle
	 * @param d le rayon du cercle
	 */
	public Cercle (Point centreCercle, double d) {
		this.rayon = d;
		this.centre = centreCercle;
	}
	
	/** Permet d'obtenir les tailles selons X et Y d'un collider
	 * @return un couple de double (X,Y)
	 */
	public double[] getTailles() {
		double[] taille={rayon*2,rayon*2};
		return taille;
	}
	
	/** Modifie la taile en X du collider, ici tX = rayon*2;
	 * @param tX la nouvelle taille.
	 */
	
	public void setTailleX(double tX) {
		this.rayon=tX/2;
	}
	
	/** Modifie la taile en Y du collider, ici tY = rayon*2
	 * @param tY la nouvelle taille.
	 */
	
	public void setTailleY(double tY) {
		this.rayon=tY/2;
	}

	/** Permet de savoir si le collider est en collision � l'autre collider
	 * @param collider le collider dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Collider collider) {
		return collider.collidesWith((Cercle)this);
	}

	/** G�re le cas sp�cifique d'une collision avec un collider Cercle
	 * @param collider le collider Cercle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Cercle cercle) {
		return this.rayon+cercle.getRayon() > this.centre.distance(cercle.getCentre());
	}
	
	/** G�re le cas sp�cifique d'une collision avec un collider Rectangle
	 * @param collider le collider Rectangle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Rectangle rectangle) {
		boolean collide;

		if (this.rayon+(rectangle.getTailleX()/2)<Math.abs(this.centre.getX()-rectangle.getCentre().getX())) {
			collide = false;
		} else if (this.rayon+(rectangle.getTailleY()/2)<Math.abs(this.centre.getY()-rectangle.getCentre().getY())) {
			collide = false;
		} else {
			collide = true;
		}

		return collide;
	}

    /** Retourne le point de collision du collider avec un collider
     * @param collider le collider avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
	public Point collidePoint(Collider collider) {
		return collider.collidePoint((Cercle)this);
	}
	
    /** G�re le cas sp�cifique d'une collision avec un collider Cercle
     * @param collider le collider Cercle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
	public Point collidePoint(Cercle colliderCercle) {
		double ponderation = this.getRayon() + colliderCercle.getRayon();
		double x = (colliderCercle.getRayon()*this.getCentre().getX() + this.getRayon()*colliderCercle.getCentre().getX())/ponderation;
		double y = (colliderCercle.getRayon()*this.getCentre().getY() + this.getRayon()*colliderCercle.getCentre().getY())/ponderation;
		return new Point(x,y);
	}
	
    /** G�re le cas sp�cifique d'une collision avec un collider Rectangle
     * @param collider le collider Rectangle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
	public Point collidePoint(Rectangle colliderRect) {
		double coeffX = this.getRayon()+(colliderRect.getTailleX()/2);
		double coeffY = this.getRayon()+(colliderRect.getTailleY()/2);
		double x = (this.getRayon()*colliderRect.getCentre().getX() + (colliderRect.getTailleX()/2)*this.getCentre().getX())/coeffX;
		double y = (this.getRayon()*colliderRect.getCentre().getY() + (colliderRect.getTailleY()/2)*this.getCentre().getY())/coeffY;
		return new Point(x,y);
	}
	
	/** Retourne le rayon du cercle.
	 * @return int retourne le rayon du cercle
	 */
	public double getRayon() {
		return this.rayon;
	}

	/** Retourne le centre du cercle.
	 * @return Point2D le centre du cercle.
	 */
	public Point getCentre() {
		return this.centre;
	}

}



package physique.collider;


import modele.Point;

/**
 * Rectangle est un collider 
 * Cette classe permet de savoir si un rectangle est en contact avec un autre collider
 * @author Adonis Najimi
 */


public class Rectangle implements Collider {
	private double tailleX;
	private double tailleY;
	private Point centre;

	/** Construit un collider Rectangle � partir de son centre et de sa largeur et longueur
	 * @param tX la longueur du rectangle par rapport � X
	 * @param tY la largeur du rectangle par rapport de Y
	 * @param c le centre du rectangle
	 */
	public Rectangle(double tX, double tY,Point c) {
		this.tailleX = tX;
		this.tailleY = tY;
		this.centre = c;
	}
	
	/** Permet d'obtenir les tailles selons X et Y d'un collider
	 * @return un couple de double (X,Y)
	 */
	public double[] getTailles() {
		double[] taille = {tailleX,tailleY};
		return taille;
	}
	
	/** Modifie la taile en X du collider
	 * @param tX la nouvelle taille.
	 */
	
	public void setTailleX(double tX) {
		tailleX = tX;
	}
	
	/** Modifie la taile en Y du collider
	 * @param tY la nouvelle taille.
	 */
	
	public void setTailleY(double tY) {
		tailleY = tY; 
	}

	/** Permet de savoir si le collider est en collision � l'autre collider
	 * @param collider le collider dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Collider collider) {
		return collider.collidesWith((Rectangle)this);
	}
	
	/** G�re le cas sp�cifique d'une collision avec un collider Cercle
	 * @param collider le collider Cercle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Cercle cercle) {
		boolean collide;
		if ((this.tailleX/2)+cercle.getRayon()<Math.abs(this.centre.getX()-cercle.getCentre().getX())) {
			collide=false;
		} else if ((this.tailleY/2)+cercle.getRayon()<Math.abs(this.centre.getY()-cercle.getCentre().getY())) {
			collide = false;
		} else {
			collide = true; 
		}
		return collide;
	}

	/** G�re le cas sp�cifique d'une collision avec un collider Rectangle
	 * @param collider le collider Rectangle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Rectangle rectangle) {
		boolean collide;

		if (this.tailleX*0.5+rectangle.getTailleX()*0.5<Math.abs(this.centre.getX()-rectangle.getCentre().getX())) {
			collide = false;
		} else if (this.tailleY*0.5+rectangle.getTailleY()*0.5<Math.abs(this.centre.getY()-rectangle.getCentre().getY())) {
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
		return collider.collidePoint((Rectangle)this);
	}

    /** G�re le cas sp�cifique d'une collision avec un collider Cercle
     * @param collider le collider Cercle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
	public Point collidePoint(Cercle colliderCercle) {
		return colliderCercle.collidePoint(this);
	}

    /** G�re le cas sp�cifique d'une collision avec un collider Rectangle
     * @param collider le collider Rectangle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
	public Point collidePoint(Rectangle colliderRect) {
		double coeffX = tailleX/(tailleX+colliderRect.getTailleX());
		double x = centre.getX();
		x = x+coeffX*(colliderRect.getCentre().getX() - x);
		
		double coeffY = tailleY/(tailleY+colliderRect.getTailleY());
		double y = centre.getY();
		y = y+coeffY*(colliderRect.getCentre().getY() - y);

		return new Point(x,y);
	}

	/** Retourne la longueur du rectangle, soit sa coordonn�e selon X
	 * @return int la taille selon X
	 */
	public double getTailleX() {
		return this.tailleX;
	}

	/** Retourne la largeur du rectangle, soit sa coordonn�e selon Y
	 * @return int la taille selon Y
	 */
	public double getTailleY() {
		return this.tailleY;
	}

	/** Retourne le centre du rectangle
	 * @return Point2D le centre du rectangle
	 */
	public Point getCentre() {
		return this.centre;
	}

}

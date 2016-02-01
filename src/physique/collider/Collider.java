package physique.collider;

import modele.Point;


/** 
 * -------------------------------------------------------------------------
 * NOTE: Collider est une hitbox generale (donc pas uniquement pour les coups) 
 * C'est un terme que j'ai deja vu dans plusieurs moteurs de jeux videos
 * a vous de voir si ça vous convient
 * exemple : http://answers.unity3d.com/questions/188775/having-more-than-one-collider-in-a-gameobject.html
 *
 * Le nom FR c'est Collisionneur apparemment
 * -------------------------------------------------------------------------
 * @author Adonis Najimi
 */

public interface Collider {
	
	/** Permet d'obtenir les tailles selons X et Y d'un collider
	 * @return un couple de double (X,Y)
	 */
	public double[] getTailles();
	
	/** Modifie la taile en X du collider
	 * @param tX la nouvelle taille.
	 */
	
	public void setTailleX(double tX);
	
	/** Modifie la taile en Y du collider
	 * @param tY la nouvelle taille.
	 */
	
	public void setTailleY(double tY);
	
	/** Permet de savoir si le collider est en collision � l'autre collider
	 * @param collider le collider dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Collider collider);
    
	/** G�re le cas sp�cifique d'une collision avec un collider Rectangle
	 * @param collider le collider Rectangle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Rectangle rectangle) ;

	/** G�re le cas sp�cifique d'une collision avec un collider Cercle
	 * @param collider le collider Cercle dont on veut savoir s'il est en contact
	 * @return boolean true si en contact, false sinon  
	 */
	public boolean collidesWith(Cercle cercle) ;

    /** Retourne le point de collision du collider avec un collider
     * @param collider le collider avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
    public Point collidePoint(Collider collider);
    
    /** G�re le cas sp�cifique d'une collision avec un collider Cercle
     * @param collider le collider Cercle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
    public Point collidePoint(Cercle colliderCercle);
    
    /** G�re le cas sp�cifique d'une collision avec un collider Rectangle
     * @param collider le collider Rectangle avec lequel le collider courant est en collision
     * @return Point le point de contact entre collider
     */
    public Point collidePoint(Rectangle colliderRect);
}

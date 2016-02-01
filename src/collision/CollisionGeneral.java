package collision;

import modele.Point;

/** Regroupe les informations concernant les collisions entre 2 calculables.
 * @author louis
 *
 */
public class CollisionGeneral implements Collision{

	private Point emplacementCollision;
	private int[] objetCalculablePercutant;
	private int[] objetCalculablePercute;
	
	/** Cr�e une collision.
	 * @param emplacement point o� se situe la collision
	 * @param objetPercutant calculable ayant provoqu� la collision (attaque � distance, coup ou personnage)
	 * @param objetPercute calculable subissant la collision (attaque � distance, personnage ou plateforme)
	 */
	public CollisionGeneral (Point emplacement, int[] objetPercutant, int[] objetPercute){
		this.emplacementCollision = emplacement;
		this.objetCalculablePercutant = objetPercutant;
		this.objetCalculablePercute = objetPercute;
	}
	
	/** Retourne l'emplacement de la collision 
	 * @return Point l'emplacement de la collision
	 */
	public Point getEmplacementPoint (){
		return this.emplacementCollision;
	}
	
	/** Retourne l'objet provoquant la collision
	 * @return Calculable le calculable ayant provoqu� la collision
	 */
	public int[] getObjetPercutant(){
		return this.objetCalculablePercutant;
	}
	
	/** Retourne l'objet subissant la collision
	 * @return Calculable le calculable subissant la collision
	 */
	public int[] getObjetPercute(){
		return this.objetCalculablePercute;
	}
	
	/** Permet de savoir si la collision est la m�me qu'une autre.
	 * @param collisionComparee la collision qu'on compare
	 * @return boolean vrai si la collision est la m�me, false sinon
	 */
	@Override
	public boolean equals(Collision collisionComparee){
		return (this.getObjetPercutant().equals(collisionComparee.getObjetPercutant()) && this.getObjetPercute().equals(collisionComparee.getObjetPercute()));
	}

	
	
}

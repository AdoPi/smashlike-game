package collision;

import modele.Point;

import physique.*;

public interface Collision {
	/** Retourne l'emplacement de la collision 
	 * @return Point l'emplacement de la collision
	 */
	public Point getEmplacementPoint();
	
	/** Retourne l'objet provoquant la collision
	 * @return Calculable le calculable ayant provoque la collision
	 */
	public int[] getObjetPercutant();
	
	/** Retourne l'objet subissant la collision
	 * @return Calculable le calculable subissant la collision
	 */
	public int[] getObjetPercute();
	
	/** Permet de savoir si la collision est la meme qu'une autre.
	 * @param collisionComparee la collision qu'on compare
	 * @return boolean vrai si la collision est la meme, false sinon
	 */
	public boolean equals(Collision collisionComparee);
	
}

package physique;

import physique.collider.*;
import modele.Point;

/** Interface des �l�ments calculables
 * On doit pouvoir obtenir la position d'un calculable, sa vitesse et son collider.
 * @author louis
 */
public interface Calculable {

	/** Retourne la position du calculable.
	 * @return Point la position du calculable.
	 */
	public Point getPosition();

	/** Retourne la vitesse du calculable.
	 * @return double[] la vitesse du calculable.
	 */
	public double[] getVitesseCourante();

	/** Retourne le collider du calculable.
	 * @return Collider le collider associ� au personnage.
	 */
	public Collider getCollider();

}

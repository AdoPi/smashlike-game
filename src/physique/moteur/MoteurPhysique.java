package physique.moteur;

import java.util.List;
import collision.*;
import modele.*;
import jeu.*;

/**Interface du moteur physique.*/
public interface MoteurPhysique {
	
  /** Met à jour les éléments physiques du jeu (position, vitesse, changements d'état ...)
   * @param Jeu le jeu à mettre à jour
   * @return List<Collision> la liste des collisions répertoriées après la mise à jour	
   */
  public List<Collision> majPhysique(Jeu jeu);
  
  /** Modifie la valeur de la gravité
   * @param g la nouvelle valeur de la gravité
   */
  public void setGravite (int g);
  public void setDureeFrame(double d);
  
}




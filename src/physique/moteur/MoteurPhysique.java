package physique.moteur;

import java.util.List;
import collision.*;
import modele.*;
import jeu.*;

/**Interface du moteur physique.*/
public interface MoteurPhysique {
	
  /** Met � jour les �l�ments physiques du jeu (position, vitesse, changements d'�tat ...)
   * @param Jeu le jeu � mettre � jour
   * @return List<Collision> la liste des collisions r�pertori�es apr�s la mise � jour	
   */
  public List<Collision> majPhysique(Jeu jeu);
  
  /** Modifie la valeur de la gravit�
   * @param g la nouvelle valeur de la gravit�
   */
  public void setGravite (int g);
  public void setDureeFrame(double d);
  
}




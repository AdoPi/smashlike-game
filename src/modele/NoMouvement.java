package modele;

public class NoMouvement implements Mouvement {
  /** Ne modifie pas la trajectoire du perso
   *  @param p le personnage qui va faire le mouvement
   **/
  public boolean appliquerMouvement(Personnage p ) {
	  return false;
  }
}

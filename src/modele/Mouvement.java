package modele;

public interface Mouvement  {

	public final static int HAUT = 0;
	public final static int BAS = 1;
	public final static int GAUCHE =2;
	public final static int DROITE =3;	
	public final static int NB_MV = 4;

	/* synonymes */

	public final static int SAUT = HAUT;


 /** applique le mouvement sur un joueur
  *  @param p le personnage qui va faire le mouvement
  */
 public boolean appliquerMouvement(Personnage p );
}


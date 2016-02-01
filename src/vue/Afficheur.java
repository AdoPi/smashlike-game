package vue;

import java.awt.Graphics;

/* un afficheur utilise des sprites pour afficher des elements graphiques */
public interface Afficheur {
	/** Retourne la duree de vie d'un afficheur
	 * @return int la duree de vie d'un afficheur*/
    	public int getDureeVie(); 
    
    /** Decremente la duree de vie d'un afficheur*/
    public void decrementerDureeVie();
    
    /** Affiche a l'aide du graphics le contenu de l'afficheur
     * @param g le graphics
     */
    public void afficher(Graphics g);
}

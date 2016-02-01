package modele;

import physique.Calculable;
import physique.collider.*;
import vue.Affichable;

/** L'attaque execute son pattern et le moteur physique fait juste le calcule
*/


public interface Attaque extends Calculable, Affichable {

    /* liste des coups d'attaque */
    public final static int A = 0;
    public final static int B = 1;
    public final static int A_COTE = 2;
    public final static int B_COTE = 3;
    public final static int A_BAS = 4;
    public final static int B_BAS = 5;
    public final static int A_HAUT = 6;
    public final static int B_HAUT = 7;

    /* synonymes */
    public final static int A_LATERAL = A_COTE;
    public final static int B_LATERAL = B_COTE;

    public final static int NB_ATQ = 8;
    
    /**Renvoie le collider de l'attaque
     * @return le collider de l'attaque
     **/
    public Collider getCollider() ;
       
    /** Renvoie la force de l'attaque : utilisee pour le calcule de l'ejection du personnage touche
     * par cette attaque
     * @return la force de l'attaque
     **/
    public double getForce() ;
    
    
     /**Renvoie les degats de l'attaque : utilisee pour le calcule du pourcentage ajoutee au personnage
      * touche par cette attauqe
     * @return les degats de l'attaque 
     **/
    public double getDegats() ;
    
    /** Renvoie la postion de l'attaque
     * @return la position de l'attaque 
     */
    public Point getPosition() ;
    
    /** Renvoie le personnage qui a lance l'attaque
     * @return le proprietaire de l'attaque
     **/
    public Personnage getProprietaire() ;
    
    
    /** Renvoie la vitesse de l'attaque
     * @return la vitesse de l'attaque 
     **/
    public double[] getVitesseCourante() ;
    
    /** Renvoie la duree de vie d'une attaque
     * @return la duree de vie d'une attaque
     **/
    public int getDureeDeVie();
    
    /** Retourne la duree pendant laquelle un personnage lance est entrain d'executer une attaque
     * @return la duree pendant laquelle un personnage lance est entrain d'executer une attaque
     */
    public int getDuree();
    
    
 
    /** Retourne si une attaque est un projectile
     * @return boolean une attaque est un projectile */
    public boolean estProjectile();

   
    /** modifie l orientation du personnage  
     * @param droite, true si oriente a droite
     * **/
    public void setOrientation(boolean droite);

    /** Retourne l orientation du personnage 
     * @return boolean valant true si droite, false si gauche */
    public boolean getOrientation();
    
    
    /** Dans le cas de d'une attaque qui en attaque une autre,
     * permet de savoir laquelle des deux doit l'emporter
     * @return la priorite de l'attaque
     */
	public int getPriorite();
    
    /**Modifier la vitesse de l'attaque
     * @param x vitesse verticale
     * @param y vitesse horizentale
     */
    public void setVitesse ( double x , double y );
    
    
    /**Modifie le collider de l'attaque 
     * @param c le collider de l'attaque
     */
    public void setCollider(Collider c);
    
    /**Modifie la position de l'attaque
     * @param x abcisse
     * @param y ordonnee
     */
    public void setPosition (double x,double y);
    
    /**Modifie le proprietaire de l'attaque
     * @param p le nouveau proprietaire
     */
    public void setProprietaire(Personnage p);
   

    /** Evoluer la durée de vie attaque
     * @return boolean true si le moteur physique doit modifier l'attaque 
    */
    public boolean evoluerAttaque();
      
    /**affecter d'une maniere particuliere la physique d'un personnage. Pour le Moteur Physique.
     * @param personnage le personnage a affecter.
     */
    public void affecter(Personnage personnage);
    

    /** Modifie les attributs du personnage touché par l'attaque
     *  et indique le devenu de l'attaque
     *  @param personnage le personnage qui 
     */
    public void attaquer(Personnage personnage);


    /** Indique le devenu de l'attaque suite a une collision avec une plateforme
     *  @param platforme la plateforme touchee
     */ 
    public void attaquer(Plateforme  plateforme);
    
    
    /** Indique le devenu de l'attaque suite a une collision avec une autre attaque
     *  @param attaque l attaque touchee
     */ 
    public void attaquer(Attaque attaque);
    

    
    /** Enleve l'attaque des attaques courantes de son proprietaire*/
    public void finirAttaque();
    
    /** Tuer l'attaque (duree de vie = 0)*/
    public void tuer();
    

}

package modele;
import physique.*;
import physique.collider.*;

public class Mai extends PersonnageBase {

	
	
	/**constructeur du personnage */
  public Mai ( ) {
	    super();
  }

  public Attaque getAttaque(int code) {
	  switch(code) {
	  case Attaque.A :
		    return new MaiAttaquePoing(this,3,0);

	  case Attaque.B :
		    return new MaiAttaqueLancerEventail(this,4,0);

	  case Attaque.A_COTE :
		    return new MaiAttaquePied(this,6,50);

	  case Attaque.B_COTE :
		    return new MaiAttaqueDash(this,11,60);

	  case Attaque.A_BAS :
		    return new MaiAttaqueCoupRobe(this,5,40);

	  case Attaque.B_BAS :
		    return new MaiAttaqueStripStun(this,0,0);

	  case Attaque.A_HAUT :
		    return new MaiAttaqueCoupPiedVertical(this,8,60);

	  case Attaque.B_HAUT :
		    return new MaiAttaqueSalto(this,10,60); 
	  }
	return null;
  }
  
  public String getNom() {
	  return "Mai";
  }

  public double getMasse() {
 	return 55;
  }
  
    
  /**Renvoie la vitesse maximale du personnage*/
  public double[]  getVitesseMax(){
	  double[] vitesseMax = {40,10};
	  return vitesseMax;
  }
   
 
   public Collider getCollider() {
	   return new Rectangle(40,100, getPosition());
   }
   
   
}



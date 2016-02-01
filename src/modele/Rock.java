package modele;
import physique.collider.*;

public class Rock extends PersonnageBase {

	
	
	/**constructeur du personnage */
  public Rock() {
	    super();
  }

  public Attaque getAttaque(int code) {
	  switch(code) {
	  case Attaque.A :
		    return new RockAttaqueBouleMagique(this,2,10);

	  case Attaque.B :
		    return new RieAttaqueHadoken(this,4,20);

	  case Attaque.A_COTE :
		    return new RieCoupPuissant(this,8,90);

	  case Attaque.B_COTE :
		    return new RieAttaqueDash(this,10,80);

	  case Attaque.A_BAS :
		    return new RieAttaqueNormalBas(this,3,30);

	  case Attaque.B_BAS :
		    return new RieForceField(this,2,120);

	  case Attaque.A_HAUT :
		    return new RieAttaqueShoryuken(this,9,60);

	  case Attaque.B_HAUT :
		    return new RieAttaqueAirPunch(this,10,80);
	  }
	return null;
  }
  
  public String getNom() {
	  return "Rock";
  }

  public double getMasse() {
 	return 70;
  }
  
    
  /**Renvoie la vitesse maximale du personnage*/
  public double[]  getVitesseMax(){
	  double[] vitesseMax = {40,10};
	  return vitesseMax;
  }

 
   public Collider getCollider() {
	   return new Rectangle(40, 100, getPosition());
   }
   
   public boolean getOrientation() {
	   return super.getOrientation();
   }
   
}
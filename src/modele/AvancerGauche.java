package modele;

public class AvancerGauche implements Mouvement {
  /** applique le mouvement sur un joueur
   *  @param p le personnage qui va faire le mouvement
   **/
  public boolean appliquerMouvement(Personnage p ) {
    double v = p.getVitesseCourante()[0];
    double vMax = p.getVitesseMax()[0];
    p.setOrientationGauche();
    boolean mouvementApplique = false;
    if (p.getEtat(Personnage.SUR_PLATEFORME)!=0){
    	if (p.getEtat(Personnage.STUN)==0) {
    		if (p.getEtat(Personnage.SUBIT_ATTAQUE) == 0) {
    			if (v<=-vMax) {
    			} else if (v<=0 && (v>=-vMax/5 || v<=-4*vMax/5)) {
    				p.additionnerVitesse(-vMax/150,0);
    				mouvementApplique = true;
    			} else {
    				p.additionnerVitesse(-vMax/100, 0);
    				mouvementApplique = true;
    			}
    		} else {
    			if (v<=-vMax) {
    			} else if (v<=0 && (v>=-vMax/5 || v<=-4*vMax/5)) {
    				p.additionnerVitesse(-vMax/180,0);
    				mouvementApplique = true;
    			} else {
    				p.additionnerVitesse(-vMax/90, 0);
    				mouvementApplique = true;
    			}
    		}
    	} else {}
    	
    	} else {
    		if (p.getEtat(Personnage.STUN)==0) {
    			if (p.getEtat(Personnage.SUBIT_ATTAQUE)==0) {
    				if (v<=-vMax) {} 
    				else {
    					p.additionnerVitesse(-vMax/120, 0);
    					mouvementApplique = true;
    				}
    			} else {
    				if (v>=vMax) {}
    				else {
    					p.additionnerVitesse(-vMax/180,0);
    					mouvementApplique = true;
    				}
    			}
    		} else {}
    		}
    return mouvementApplique;
  }
}
  
  


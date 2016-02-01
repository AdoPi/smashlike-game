package jeu;

import modele.Personnage;

public class JoueurImmobile extends Joueur {

	public JoueurImmobile(Personnage p) {
		super(p);
	}

	public void configurerInputs() {
		for(int i = 0; i < inputs.length; i++) {
			for(int j=0;j<inputs[i].length;j++)
				this.inputs[i][j] = 0;
		}
	}

}

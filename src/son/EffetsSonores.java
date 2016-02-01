package son;

import java.util.HashMap;

import collision.Collision;
import modele.Attaque;
import modele.Personnage;
import modele.Stage;
import vue.SpriteSheet;

public class EffetsSonores {
	
	public static HashMap<String,String> SONS = new HashMap<String,String>();
	
	private static int framesSansSonApresAttaque = 7;
	
	private static int numeroDerniereFrameAvecSon = 0;
	
	static {
		
		//Pour personnages qui subissent une attaque
		SONS.put("Mai", "NBC_mai_00012.wav");
		SONS.put("Rie", "09_ryo_00007.wav");
		SONS.put("Rock", "17_ramon_00004.wav");
		
		//Attaques de mai
		SONS.put("Eventail","NBC_mai_00000.wav");
		SONS.put("MaiAttaqueCoupPiedVertical","NBC_mai_00036.wav");
		SONS.put("MaiAttaquePied","NBC_mai_00037.wav");
		SONS.put("MaiAttaquePoing","NBC_mai_00007.wav");
		SONS.put("MaiAttaqueDash","NBC_mai_00025.wav");
		SONS.put("MaiAttaqueCoupRobe","NBC_mai_00004.wav");
		SONS.put("MaiAttaqueStripStun","NBC_mai_00032.wav");
		SONS.put("MaiAttaqueSalto","NBC_mai_00034.wav");
		

		//Attaques de Rie
		SONS.put("RieAttaqueCoupBasique","09_ryo_00004.wav");
		SONS.put("RieAttaqueBasse","09_ryo_00002.wav");
		SONS.put("Hadoken","46d301_SFIV_Ryu_Hadouken_Sound_Effect.wav");
		SONS.put("RieAttaqueDash","09_ryo_00013.wav");
		SONS.put("RieCoupPuissant","09_ryo_00033.wav");
		SONS.put("RieAttaqueShoryuken","09_ryo_00015.wav");
		SONS.put("RieAttaqueAirPunch","09_ryo_00028.wav");
		SONS.put("Force_Field","09_ryo_00019.wav");
		
		//Attaques de Rie
		SONS.put("BouleMagique", "17_ramon_00020.wav");
		
		
		//autre
		SONS.put("KO","Synth_nrt_119_wav.aax_0000.wav");
		
		//sons stage
		SONS.put("StageForet","gerudo.wav");
		SONS.put("StageRue","duelo.wav");

		
	}
	
	public static void jouer(Attaque a) {
		String path = SONS.get(a.getNom());
		if (path != null)
			MakeSound.jouerSonParallele(path);
	}
	
	public static void jouer(Stage stage) {
		String path = SONS.get(stage.getNom());
		if (path != null) {
			MakeSound.jouerSonParallele(path);			
		}
	}
	
	public static void jouerMort() {
		MakeSound.jouerSonParallele(SONS.get("KO"));
	}
	
	public static void jouer(Personnage p) {
		String path = SONS.get(p.getNom());
		if (path != null)
			MakeSound.jouerSonParallele(path);
	}
	public static void jouer(Attaque a, int frameActuelle) {
		if (EffetsSonores.jouerSon(frameActuelle)){
			String path = SONS.get(a.getNom());
			if (path != null) {
				MakeSound.jouerSonParallele(path);
				numeroDerniereFrameAvecSon = frameActuelle;				
			}
		}
	}
	public static void jouer(Personnage p, int frameActuelle) {
		if (EffetsSonores.jouerSon(frameActuelle)){
			String path = SONS.get(p.getNom());
			if (path != null) {
				MakeSound.jouerSonParallele(path);
				numeroDerniereFrameAvecSon = frameActuelle;				
			}
		}
	}
	
	private static boolean jouerSon(int frameActuelle){
		boolean resultat;
		if (frameActuelle - EffetsSonores.numeroDerniereFrameAvecSon >= EffetsSonores.framesSansSonApresAttaque) {
			resultat = true;
		} else {
			resultat = false;
		}
		
		return resultat;
		
	}
}

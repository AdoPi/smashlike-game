package vue;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.*;

import modele.Attaque;
import modele.Mai;
import modele.Personnage;
import modele.Plateforme;
import modele.Stage;

import javax.imageio.ImageIO;
import java.net.*;
import jeu.Jeu;
/* 
	classe permettant de definir les sprites utilises dans le jeu 
    CONVENTION pour personnage: 
    numero ligne de sprite < NB_ETATS = numero etat personnage
    numero ligne de sprite > NB_ETATS = numero d'attaque  
*/


// permet d'obtenir les informations sur les sprites 
public class SpriteSheet {
	
	
	public static HashMap<String,SpriteSheet> PERSONNAGES = new HashMap<String,SpriteSheet>();
	
	public static HashMap<String,SpriteSheet> PROJECTILES = new HashMap<String,SpriteSheet>();
	
	public static HashMap<String,SpriteSheet> PLATEFORMES = new HashMap<String,SpriteSheet>();

	public static HashMap<String,SpriteSheet> STAGES = new HashMap<String,SpriteSheet>();
	/* creation des tables de sprites 
	 *
	 * (le mieux serait de tout lire dans un fichier avec la methode charger)
	 */

	static {
		/* creation des liasons objet -> feuille */
		SpriteSheet 
		spritesBouleFeu,
		spritesMai,
		spritesRie,
		spritesRock
		;
		
		// les sprites de Mai
		int [] tabMai = {24,10,4,16,8,7,13,6,9,6,10,9,9};
		int [] decMai = {-60,-75};
		int [][] tabDecMai = {decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai,decMai};
		spritesMai = new SpriteSheet("Mai.png", tabMai, 640, 512,13,tabDecMai);
		int[] decalage = {240,220};
		spritesMai.setScale(1.5,0.8);
		spritesMai.setDecalage(decalage);
		PERSONNAGES.put("Mai",spritesMai);
		
		int [] tabRock = {14,10,5,14,8,9,9,9,9,9,9,9,9};
		// les sprites de Rock
		spritesRock = new SpriteSheet("rock.png",tabRock,640,512,13,tabDecMai);
		spritesRock.setScale(1.5,0.8);
		spritesRock.setDecalage(decalage);
		PERSONNAGES.put("Rock",spritesRock);
		
	
		//plateforme de base
		int[] nbseqPlateforme = {1};
				
		
		String nom = "PlateformeCarrePetite";
		SpriteSheet spritePlateforme = new SpriteSheet("StageForetBloc-long.png",nbseqPlateforme, 50, 50);
		spritePlateforme.setScale(0.25, 0.25);
		PLATEFORMES.put(nom, spritePlateforme);
//			
		nom = "PlateformeRectangleDeBase";
		spritePlateforme = new SpriteSheet("StageForetBloc-long.png",nbseqPlateforme, 796, 30);
		PLATEFORMES.put(nom, spritePlateforme);
//			
		//nom = "PlateformeRectangleMoyenne";
		//spritePlateforme = new SpriteSheet("StageForetBloc-long.png",nbseqPlateforme, 200, 30);
	//	PLATEFORMES.put(nom, spritePlateforme);
		
		
		// les sprites de Rie
		int [] tabRie = {32,16,8,32,16,10,6,12,15,8,7,9,4};
		int [] decRie = {-60,-60};
		int [][] tabDecRie = {decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie,decRie};
		spritesRie = new SpriteSheet("Riesbyfe_Stridberg.png", tabRie, 256, 256,13,tabDecRie);
		int[] decalageRie = {40,50};
		spritesRie.setDecalage(decalageRie);
		spritesRie.setScale(0.95,0.95);
		spritesRie.setOrientationSprite(true); //Rie est oriente vers la droite
		PERSONNAGES.put("Rie", spritesRie);
		
		
		
		int[] port= {1};
		SpriteSheet spritesRue = new SpriteSheet("port.png",port,950,470);
		spritesRue.setScale(1.5,1.5);
		STAGES.put("StageRue",spritesRue);

		int[] foret= {1};
		SpriteSheet spritesForet = new SpriteSheet("foret.jpg",port,1280,720);
		STAGES.put("StageForet",spritesForet);
		
		
		/* creation des sprites plateformes */
		//penser a la distinction entre traversable ou non
//		PLATEFORMES.put(new Plateforme1(),new SpriteSheet());
		
		//--------------------------------------------------------------------//
		// les sprites de projectiles
		
		int[] nbSeqsEventail = {8};
		SpriteSheet spritesEventail = new SpriteSheet("eventail.png",nbSeqsEventail,640,512);
		int[] decalageEventail = {290,365}; 
		spritesEventail.setDecalage(decalageEventail);
		spritesEventail.setScale(1.6, 0.5);
		/* creation des sprites projectiles */
		PROJECTILES.put("Eventail",spritesEventail);
		
		// les sprites de la boule de feu
		
		int[] nbSeqsBouleFeu = {10};
		SpriteSheet spritesBoule = new SpriteSheet("Fire_ball.png", nbSeqsBouleFeu, 640, 512);
		int[] decalageBoule = {230,300};
		int[] calageBoule = {-40,-30};
		spritesBoule.setDecalage(decalageBoule);
		spritesBoule.setScale(1, 0.3);
		int[][] tabCalageBoule = {calageBoule};
		spritesBoule.setCalage(tabCalageBoule);
		spritesBoule.setOrientationSprite(false);
		PROJECTILES.put("Hadoken", spritesBoule);
		
		// les sprites du force field de Rie
		int[] nbSeqsForce = {13};
		int[] calageForce = {-10,-10};
		int[][] tabForce = {calageForce};
		SpriteSheet spriteForce = new SpriteSheet("Force_Field.png", nbSeqsForce, 256, 256);
		spriteForce.setCalage(tabForce);
		spriteForce.setScale(0.7, 0.7);
		PROJECTILES.put("Force_Field", spriteForce);
		
		//les sprites de la boule magique
		int [] nbSeqsBoule = {4};
		SpriteSheet spriteFeu = new SpriteSheet("ball.png", nbSeqsBoule, 160, 144);
		spriteFeu.setScale(0.3, 0.3);
		PROJECTILES.put("BouleMagique", spriteFeu);
		
	}


	private String path;
	private int[] nbSequences;
	private int nbTuiles;
	private BufferedImage sheet;
	private int largeurTuile;
	private int hauteurTuile;
	private double scaleHauteur;
	private double scaleLargeur;
	private int[] decalage;
	private int[][] calageSprite;
	private boolean orientationSprite; //false : gauche, true : droite
	
	
	//pour les plateformes qui n'ont qu'une tuile
	public SpriteSheet(String path, int[] nbSequences, int largeurTuile, int hauteurTuile) {
		this.decalage = new int[2];
		int [] zeroCalage = new int [2];
		int [][] zeroCalageTab = {zeroCalage};
		this.calageSprite = zeroCalageTab;
		this.largeurTuile = largeurTuile;
		this.hauteurTuile = hauteurTuile;
		this.path = path;
		//chargement de la feuille de sprites
		this.sheet = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("data/"+path);
           // URL defaultSound = getClass().getResource("data/"+path);
            //File imageFile = new File(defaultSound.toURI());
       //                 this.sheet = ImageIO.read(imageFile);
			this.sheet = ImageIO.read(input);
//			this.sheet = ImageIO.read(new File("data/"+path));	
		} catch(Exception e) {
			e.printStackTrace();
                        System.out.println("Impossible de charger "+ path);
		}
		this.nbSequences = nbSequences;
		this.nbTuiles = 1;
		this.scaleHauteur = 1;
		this.scaleLargeur = 1;
	}

	public void setOrientationSprite(boolean gauche) {
		this.orientationSprite = gauche;
	}
	
	public void setDecalage(int[] decalage) {
		this.decalage = decalage;
	}
	
	/**precondition : le tableau de sequences max est de la taille nbTuiles*/
	//pour les personnages, qui ont un nombre de tuiles dependant du nombre d'etats et d'attaques
	public SpriteSheet (String path, int[] nbSequences,int largeurTuile,int hauteurTuile, int nbTuiles, int[][] tableauCalage) {
		this(path,nbSequences,largeurTuile,hauteurTuile);
		this.calageSprite = tableauCalage;
		this.nbTuiles = nbTuiles;
	}

	public int getCalageX (int tuile){
		return this.calageSprite[tuile-1][0];
	}
	
	public int getCalageY (int tuile){
		return this.calageSprite[tuile-1][1];
	}
	
	public int getNombreTuiles() {
		return nbTuiles;
	}
	public int getNombreSequences(int tuile) {
		return nbSequences[tuile-1];
	}
	
	public int getNombreSequences() {
		return nbSequences[0];
	}
	
	private void setScale(double h, double w) {
		this.scaleHauteur = h;
		this.scaleLargeur = w;
	}
	
	private void setCalage(int[][] tabCalage){
		this.calageSprite = tabCalage;
	}
	
	public double getScaleHauteur() {
		return scaleHauteur;
	}
	
	public double getScaleLargeur() {
		return scaleLargeur;
	}
	
	/* charger des sprites a partir d'un fichier de configuration */
	public void charger(String pathToFile) {
		//creation des tables a l'aide du fichier 	
	}
	//creation d'une image a afficher a partir d'une feuille de sprite pour une plateforme, d'o� l'absence d'orientation	
		public BufferedImage getSprite(int sequence) {
			return this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
		}

	//creation d'une image a afficher a partir d'une feuille de sprite	
	public BufferedImage getSprite(int sequence, boolean orientation) {
		//orientation == false => gauche
		BufferedImage img = null; //= this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
		if (orientation) {
			if (orientationSprite) {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
			} else {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile, this.decalage[1], largeurTuile - decalage[0], hauteurTuile - this.decalage[1]);
				img = miroir(img);				
			}

		} else {
			if (orientationSprite) {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile, this.decalage[1], largeurTuile - decalage[0], hauteurTuile - this.decalage[1]);
				img = miroir(img);	
			} else {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
			}
		}
		return img;
	
	
	
	
	}
	/* 
	 *creation d'une image a afficher a partir d'une feuille de sprite
	 * d'une tuile et d'un numero de sequence 
	 */
	public BufferedImage getSprite(int sequence,int tuile,boolean orientation) {
		//orientation == false => gauche
		BufferedImage img = null; //= this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
		if (orientation) {
			if (orientationSprite) {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
			} else {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile, (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - decalage[0], hauteurTuile - this.decalage[1]);
				img = miroir(img);				
			}

		} else {
			if (orientationSprite) {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile, (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - decalage[0], hauteurTuile - this.decalage[1]);
				img = miroir(img);	
			} else {
				img = this.sheet.getSubimage((sequence - 1) * largeurTuile + this.decalage[0], (tuile - 1) * hauteurTuile + this.decalage[1], largeurTuile - this.decalage[0], hauteurTuile - this.decalage[1]);
		
			}
		}
		return img;
	
	}
	
	
	private BufferedImage miroir(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}

}

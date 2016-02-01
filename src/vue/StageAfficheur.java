package vue;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import modele.*;
import modele.Point;

public class StageAfficheur implements Afficheur {
        
    private Stage stage;
    private SpriteSheet sheet;
    public StageAfficheur(Stage stage) {
        this.stage = stage;
    	this.sheet = SpriteSheet.STAGES.get(stage.getNom());
    }


    public void afficher(Graphics g) {
    	if (sheet != null) {
        	BufferedImage img = sheet.getSprite(1,false);
        	int w = (int)(img.getWidth()*sheet.getScaleLargeur());
        	int h = (int)(img.getHeight()*sheet.getScaleHauteur());
            Point position = stage.getPositionTranslatee();
            //g.drawImage(img,(int)position.getX(),(int)position.getY(),null);
        	g.drawImage(img, (int) Math.floor(position.getX()), (int) Math.floor(position.getY()),w,h,null);
    	}

    }


	@Override
	public int getDureeVie() {
		return -1;
	}


	@Override
	public void decrementerDureeVie() {
	}

}

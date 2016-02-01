package menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

class JPanelDessine extends JPanel{		
		private String path;		
		
		public JPanelDessine(String path){
			super();
			this.path = path;
		}
		public void paintComponent(Graphics g) {
			//recupere imageJPanel imageHaut = new JPanel() {
			try {
			//	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//	InputStream input = classLoader.getResourceAsStream(this.path);
//				BufferedImage input = ImageIO.read(new File("data/"+path));

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("data/"+path);
			BufferedImage img  = ImageIO.read(input);
				g.drawImage(img,0,0,null);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		public void setPath(String path) {
			this.path = path;
		}
}

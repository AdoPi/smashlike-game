package menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.Main.Mode;


public class ContainerChoixMode extends ContenuReinitialisable{
	
	private static boolean modeVieDisponible = true;
	private static boolean modeTempsDisponible = true;
	
	public ContainerChoixMode() {
		
	JPanel englobant = new JPanel();
	englobant.setPreferredSize(Main.tailleFenetre);
	
	this.setPreferredSize(Main.tailleFenetre);
	this.setLayout(new FlowLayout());

	englobant.setLayout(new BorderLayout());
	
	JPanelDessine vie = new JPanelDessine("choixVieIcon.jpg");
	Dimension tailleBoutons = new Dimension((int)Main.tailleFenetre.getWidth(),(int)Main.tailleFenetre.getHeight()/3);
	vie.setPreferredSize(tailleBoutons);
	vie.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (modeVieDisponible) {
				Main.setMode(Mode.VIE);
				Main.setContainer(3);
			}
		}
	});
	englobant.add(vie,BorderLayout.NORTH);
	
	JPanelDessine entrainement = new JPanelDessine("choixEntrainementIcon.jpeg");
	entrainement.setPreferredSize(tailleBoutons);
	entrainement.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Main.setMode(Mode.ENTRAINEMENT);
			Main.setContainer(3);
		}
	});
	
	englobant.add(entrainement,BorderLayout.CENTER);
	
	JPanelDessine temps = new JPanelDessine("choixTempsIcon.png");
	temps.setPreferredSize(tailleBoutons);
	temps.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (modeTempsDisponible) {
				Main.setMode(Mode.TEMPS);
				Main.setContainer(3);
			}
		}
	});
	
	englobant.add(temps,BorderLayout.SOUTH);
	
	this.add(englobant);
	
	}

	@Override
	public void reinitialiser() {
		// TODO Auto-generated method stub
		
	}

}

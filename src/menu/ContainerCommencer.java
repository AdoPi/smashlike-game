package menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ContainerCommencer extends ContenuReinitialisable {

	
	public ContainerCommencer () {

		super();
		
		JPanelDessine englobant = new JPanelDessine("CommencerFond.jpg");
		englobant.setPreferredSize(Main.tailleFenetre);
		
		this.setPreferredSize(Main.tailleFenetre);
		this.setLayout(new BorderLayout());

		englobant.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(2);	
			}
		});
		this.add(englobant,BorderLayout.CENTER);
		

	}

	@Override
	public void reinitialiser() {}


	
}

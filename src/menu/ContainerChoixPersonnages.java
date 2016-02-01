package menu;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Personnage;


public class ContainerChoixPersonnages extends ContenuReinitialisable{
	
	//nombre de personnages choisis
	private int nbPersoChoisis = 0;
	private String pathParDefaut = "imageParDefaut.png";
	
	//Pour pouvoir modifier les images
	JPanelDessine personnage1;
	JPanelDessine personnage2;
	
	//pour le bouton
	JButton boutonSuivant;
	
	public ContainerChoixPersonnages() {
		
		this.setPreferredSize(Main.tailleFenetre);
		
		int nbPersonnages = Main.personnagesDisponibles.length;
		JPanelDessine[] boutonPersonnage = new JPanelDessine[nbPersonnages];
		
		this.setLayout(new BorderLayout());
		
		//centre : grille des personnages
		JPanelDessine personnages = new JPanelDessine("fondSimple.jpg");

		//personnages.setLayout(new GridLayout(3,(int) (nbPersonnages/3)));
		personnages.setLayout(new FlowLayout());
		//personnages.setPreferredSize(new Dimension(3*(int)MenuTest.tailleIcones.getHeight(),(int) (nbPersonnages/3)));
		
		for(int i = 0; i<nbPersonnages; i++){
			boutonPersonnage[i] = new JPanelDessine(Main.personnagesDisponibles[i].getNom() + "Icon.png");
			boutonPersonnage[i].addMouseListener(new SelectionPersonnage(i));
			boutonPersonnage[i].setMaximumSize(Main.tailleIcones);
			boutonPersonnage[i].setPreferredSize(Main.tailleIcones);
			personnages.add(boutonPersonnage[i]);
		}
		
		this.add(personnages,BorderLayout.CENTER);
		
		//est : boutons retour, suivant et options
		JPanelDessine boutons = new JPanelDessine("bandePetite.jpeg");
		boutons.setLayout(new FlowLayout());
		this.boutonSuivant = new JButton("Suivant");

		boutonSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(4);
			}
		});
		boutonSuivant.setEnabled(false);
		boutons.add(boutonSuivant);

		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(2);
			}
		});
		boutons.add(boutonRetour);
	
		JButton boutonOptions = new JButton("Options");
		boutonOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(6);
			}
		});
		boutons.add(boutonOptions);

		this.add(boutons,BorderLayout.NORTH);
		
		//sud : personnages choisis, dynamique
				
				JPanelDessine personnagesChoisis = new JPanelDessine("bandeGrande.jpeg");
			//	JPanel personnagesChoisis = new JPanel();
			//	personnagesChoisis.setBackground(Color.RED);
				
				personnagesChoisis.setLayout(new FlowLayout());

				JLabel joueur1 = new JLabel("Joueur1");
				JLabel joueur2 = new JLabel("Joueur2");
				personnagesChoisis.add(joueur1);
				
				this.personnage1 = new JPanelDessine(pathParDefaut);
				personnage1.setPreferredSize(Main.tailleIcones);
				personnagesChoisis.add(personnage1);
				this.personnage2 = new JPanelDessine(pathParDefaut);
				personnage2.setPreferredSize(Main.tailleIcones);
				personnagesChoisis.add(personnage2);
				
				personnagesChoisis.add(joueur2);
				
				this.add(personnagesChoisis,BorderLayout.SOUTH);
	}

	class SelectionPersonnage extends MouseAdapter {
		private int indicePersonnage;
		
		public SelectionPersonnage (int i) {
			this.indicePersonnage = i;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			Personnage personnageChoisi = Main.personnagesDisponibles[this.indicePersonnage];
			String path = personnageChoisi.getNom() + "Icon.png";
			
			switch (ContainerChoixPersonnages.this.nbPersoChoisis) {
			case 0:
				//Mise a jour affichage
				ContainerChoixPersonnages.this.personnage1.setPath(path);
				//Informer le menu du choix
				Main.setPersonnage1(personnageChoisi);
				break;
			case 1:
				//Mise a jour affichage
				ContainerChoixPersonnages.this.personnage2.setPath(path);
				//Informer le menu du choix
				Main.setPersonnage2(personnageChoisi);
				ContainerChoixPersonnages.this.boutonSuivant.setEnabled(true);
				break;
			default:
				break;
			}			
			ContainerChoixPersonnages.this.nbPersoChoisis = ContainerChoixPersonnages.this.nbPersoChoisis + 1;
			ContainerChoixPersonnages.this.repaint();
		}
	}

	@Override
	public void reinitialiser() {
		this.personnage1.setPath(this.pathParDefaut);
		this.personnage2.setPath(this.pathParDefaut);
		nbPersoChoisis = 0;
		this.boutonSuivant.setEnabled(false);
		
	}
	
}

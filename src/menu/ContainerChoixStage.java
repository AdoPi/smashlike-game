package menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.ContainerChoixPersonnages.SelectionPersonnage;
import modele.Stage;



public class ContainerChoixStage extends ContenuReinitialisable{
	
	//pour afficher l'image du stage
	private JPanelDessine stage;
	private String pathParDefaut = "imageParDefaut.png";
	
	//pour le bouton
	JButton boutonLancer;
	
	public ContainerChoixStage() {
		
		this.setPreferredSize(Main.tailleFenetre);

		int nbStages = Main.stagesDisponibles.length;
		JPanelDessine[] boutonStage = new JPanelDessine[nbStages];
		
		this.setLayout(new BorderLayout());
		
		//centre : grille des stages
		JPanelDessine stages = new JPanelDessine("fondSimple.png");
		
		stages.setLayout(new FlowLayout());
		for(int i = 0; i<nbStages; i++){
			boutonStage[i] = new JPanelDessine(Main.stagesDisponibles[i].getNom() + "Icon.png");
			boutonStage[i].addMouseListener(new SelectionStage(i));
			boutonStage[i].setPreferredSize(Main.tailleIcones);
			stages.add(boutonStage[i]);
		}
		
		this.add(stages,BorderLayout.CENTER);
		
		//sud : stage choisi, dynamique
		
		JPanelDessine stageChoisi = new JPanelDessine("bandeGrande.jpeg");
		stageChoisi.setLayout(new FlowLayout());	
		this.stage = new JPanelDessine(this.pathParDefaut);
		this.stage.setPreferredSize(Main.tailleIcones);
		stageChoisi.add(stage);
		this.add(stageChoisi,BorderLayout.SOUTH);
		
		//nord : boutons retour et lancement
		JPanelDessine boutons = new JPanelDessine("bandePetite.jpeg");
		boutons.setLayout(new FlowLayout());
		
		this.boutonLancer = new JButton("Lancer");
		boutonLancer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.creerJeu();
			}
		});
		this.boutonLancer.setEnabled(false);
		boutons.add(boutonLancer);
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(3);
			}
		});
		boutons.add(boutonRetour);
		
		this.add(boutons,BorderLayout.NORTH);		
	}
	
	
	class SelectionStage extends MouseAdapter {
		private int indiceStage;
		
		public SelectionStage(int i) {
			this.indiceStage = i;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Stage stageChoisi = Main.stagesDisponibles[indiceStage];
			String path = stageChoisi.getNom() + "Icon.png";
			//Mise a jour affichage
			ContainerChoixStage.this.stage.setPath(path);
			//Informer du choix
			Main.setStage(stageChoisi);
			ContainerChoixStage.this.repaint();
			//activer le bouton
			ContainerChoixStage.this.boutonLancer.setEnabled(true);
		}
	}

	@Override
	public void reinitialiser() {
		this.stage.setPath(this.pathParDefaut);
		this.boutonLancer.setEnabled(false);
		
	}
}


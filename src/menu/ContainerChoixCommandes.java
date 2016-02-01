package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Attaque;
import modele.Mouvement;


public class ContainerChoixCommandes extends ContenuReinitialisable{
	
	private final static int[][] inputsJ1ParDefaut;
	private final static int[][] inputsJ2ParDefaut;
	private int[][] inputsJ1;
	private int[][] inputsJ2;
	
	
	static{
		
		inputsJ1ParDefaut = new int[3][];
		inputsJ1ParDefaut[0] = new int[2];
		inputsJ1ParDefaut[1] = new int[4];
		inputsJ1ParDefaut[2] = new int[1];
		inputsJ1ParDefaut[2][0] = KeyEvent.VK_SPACE;
		inputsJ1ParDefaut[1][Mouvement.HAUT] = KeyEvent.VK_UP;
		inputsJ1ParDefaut[1][Mouvement.BAS] = KeyEvent.VK_DOWN;
		inputsJ1ParDefaut[1][Mouvement.DROITE] = KeyEvent.VK_RIGHT;
		inputsJ1ParDefaut[1][Mouvement.GAUCHE] = KeyEvent.VK_LEFT;
		inputsJ1ParDefaut[0][Attaque.A] = KeyEvent.VK_P;
		inputsJ1ParDefaut[0][Attaque.B] = KeyEvent.VK_O; 
	
	
		inputsJ2ParDefaut = new int[3][];
		inputsJ2ParDefaut[0] = new int[2];
		inputsJ2ParDefaut[1] = new int[4];
		inputsJ2ParDefaut[2] = new int[1];
		inputsJ2ParDefaut[2][0] = KeyEvent.VK_SPACE;
		inputsJ2ParDefaut[1][Mouvement.GAUCHE] = KeyEvent.VK_Q;
		inputsJ2ParDefaut[1][Mouvement.BAS] = KeyEvent.VK_S;
		inputsJ2ParDefaut[1][Mouvement.DROITE] = KeyEvent.VK_D; 
		inputsJ2ParDefaut[1][Mouvement.HAUT] = KeyEvent.VK_Z;
		inputsJ2ParDefaut[0][Attaque.A] = KeyEvent.VK_A;
		inputsJ2ParDefaut[0][Attaque.B] = KeyEvent.VK_E;
		
	}
		
	
	public ContainerChoixCommandes() {
		
	//par defaut
	inputsJ1 = new int[3][];
	inputsJ1[0] = new int[2];
	inputsJ1[1] = new int[4];
	inputsJ1[2] = new int[1];
	inputsJ2 = new int[3][];
	inputsJ2[0] = new int[2];
	inputsJ2[1] = new int[4];
	inputsJ2[2] = new int[1];
	recopierInputs();
	this.maj();
		
	
	}
	public void recopierInputs() {


		for(int i = 0; i < inputsJ1.length; i++) {
			for(int j=0;j<inputsJ1[i].length;j++)
				this.inputsJ1[i][j] = inputsJ1ParDefaut[i][j];
		}
		for(int i = 0; i < inputsJ2.length; i++) {
			for(int j=0;j<inputsJ2[i].length;j++)
				this.inputsJ2[i][j] = inputsJ2ParDefaut[i][j];
		}


	}
	
	public void maj() {
		this.removeAll();
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//entete
		JLabel option = new JLabel("Commandes");
		option.setForeground(Color.GRAY);
		this.add(option);
		//colonnes du tableau
		JPanel tableau = new JPanel();
		tableau.setLayout(new GridLayout());
		tableau.setForeground(Color.GRAY);
		JLabel action = new JLabel("Action");
		JLabel joueur1 = new JLabel("Joueur1");
		JLabel joueur2 = new JLabel("Joueur2");
		tableau.add(action);
		tableau.add(joueur1);
		tableau.add(joueur2);
		
		this.add(tableau);
		
		//creation des lignes
		
		this.ajouterLigne("Saut",1,Mouvement.HAUT);
		this.ajouterLigne("Bas",1,Mouvement.BAS);
		this.ajouterLigne("Droite",1,Mouvement.DROITE);
		this.ajouterLigne("Gauche",1,Mouvement.GAUCHE);
		this.ajouterLigne("Attaque Simple",0,Attaque.A);
		this.ajouterLigne("Attaque Speciale",0,Attaque.B);
		this.ajouterLigne("Pause",2,0);

		//ajout des boutons
		JPanel ligne = new JPanel();
		ligne.setLayout(new GridLayout());
		
		JButton reinitialise = new JButton("Reinitialise");
		reinitialise.addMouseListener(new ActionReinitialiser(this));
		ligne.add(reinitialise);
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.setContainer(3);
			}
		});
		
		ligne.add(boutonRetour);
		
		this.add(ligne);
		Main.fenetre.setContentPane(this);
		Main.fenetre.repaint();
		
	}

	private void ajouterLigne(String intituleAction, int codeMouvement, int mouvement) {
		//la ligne du tableau
		JPanel ligne = new JPanel();
		ligne.setLayout(new GridLayout());
		//les cases du tableau
		JLabel action = new JLabel(intituleAction);
		JLabel joueur1 = new JLabel(this.getCharModifie(inputsJ1[codeMouvement][mouvement]));
		JLabel joueur2 = new JLabel(this.getCharModifie(inputsJ2[codeMouvement][mouvement]));
		ligne.add(action);
		ligne.add(joueur1);
		ligne.add(joueur2);
		//les actions associees
		joueur1.addMouseListener(new OuvrirFenetreChangementTouche(this.inputsJ1,codeMouvement,mouvement,this));
		joueur2.addMouseListener(new OuvrirFenetreChangementTouche(this.inputsJ2,codeMouvement,mouvement,this));
			
		this.add(ligne);
		
	}
	
	public String getCharModifie(int keyCode) {
		String resultat;
		switch (keyCode) {
		//espace
		case KeyEvent.VK_SPACE:
			resultat = "espace";
			break;
		case KeyEvent.VK_KP_DOWN:
			resultat = "fleche bas";
			break;
		case KeyEvent.VK_KP_RIGHT:
			resultat = "->";
			break;
		case KeyEvent.VK_KP_LEFT:
			resultat = "<-";
			break;
		case KeyEvent.VK_KP_UP:
			resultat = "fleche haut";
			break;
		default :
			resultat = KeyEvent.getKeyText(keyCode);
			break;		
		}
		return resultat;
	}
	
	@Override
	public void reinitialiser() {
		//par defaut
		this.recopierInputs();
		this.maj();
	}
	
	public int[][] getInputsJ1() {
		return this.inputsJ1;
	}

	public int[][] getInputsJ2() {
		return this.inputsJ2;
	}
	
	class OuvrirFenetreChangementTouche extends MouseAdapter{
		private int[][] inputs;
		private int codeMouvement;
		private int mouvement;
		private ContainerChoixCommandes fenetreCommandes;
		
		public OuvrirFenetreChangementTouche(int[][] inputs, int codeMouvement, int mouvement, ContainerChoixCommandes fenetreCommandes){
			this.codeMouvement = codeMouvement;
			this.inputs = inputs;
			this.mouvement = mouvement;
			this.fenetreCommandes = fenetreCommandes;
		}
		
		@Override
		public void mouseClicked(MouseEvent e){
			//la nouvelle fenetre
			JFrame fenetre = new JFrame();
			Container contenu = fenetre.getContentPane();
			JPanel pourClavier = new JPanel();
			pourClavier.setLayout(new FlowLayout());
			contenu.add(pourClavier);
			pourClavier.addKeyListener(new RecevoirTouche(inputs, codeMouvement, mouvement, fenetre, fenetreCommandes));
			//les deux touches
			JLabel invite = new JLabel("Appuyez sur une touche");
			pourClavier.add(invite);
			JButton retour = new JButton("Retour");
			retour.addMouseListener(new ActionRetour(fenetre));
			pourClavier.add(retour);
			//affichage de la fenetre
			pourClavier.setFocusable(true);
			fenetre.pack();
			fenetre.setLocationRelativeTo(null);
			fenetre.setVisible(true);
		}
	}
	class ActionReinitialiser extends MouseAdapter {
		private ContainerChoixCommandes contenu;
		public ActionReinitialiser(ContainerChoixCommandes contenu) {
			this.contenu = contenu;
		}
		@Override
		public void mouseClicked(MouseEvent e){
				contenu.reinitialiser();
		}
	}
	
	class ActionRetour extends MouseAdapter {
		private JFrame fenetre;
		public ActionRetour(JFrame fenetre) {
			this.fenetre = fenetre;
		}
		@Override
		public void mouseClicked(MouseEvent e){
			this.fenetre.dispose();
		}
	}
	class RecevoirTouche implements KeyListener{
		private int codeMouvement;
		private int mouvement;
		private int[][] inputs;
		private JFrame fenetre;
		private ContainerChoixCommandes fenetreCommandes;
		
		public RecevoirTouche(int[][] inputs, int codeMouvement, int mouvement, JFrame fenetre, ContainerChoixCommandes fenetreCommandes){
			this.codeMouvement = codeMouvement;
			this.mouvement = mouvement;
			this.inputs = inputs;
			this.fenetre = fenetre;
			this.fenetreCommandes = fenetreCommandes;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			int code = arg0.getKeyCode();
			this.inputs[this.codeMouvement][this.mouvement] = code;
			this.fenetre.dispose();
			this.fenetreCommandes.maj();
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
	
		}
		
	}
}

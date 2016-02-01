package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Clavier implements KeyListener {
	private LinkedList<KeyEvent> buffer; //touches recues a traiter
	private final static int KEY_CODE_MAX = 256; // nombre total de touches
	private boolean touches[]; // la combinaison rendue de touches

	public Clavier() {
		touches = new boolean[KEY_CODE_MAX];
		buffer = new LinkedList<KeyEvent>();
	}

	/** Permet de savoir si la touche correspondant au code est appuyee
	 *@param code le code  pour lequel on veut verifier si la touche est appuyer 
	 **/
	public boolean toucheAppuyee(int code) {
		return touches[code];
	}
	
	/** Mise a jour des input a partir de ce qui est recu dans buffer*/
	public void majInputs() {
		for(KeyEvent e : buffer) {
			if(e.getID() == KeyEvent.KEY_PRESSED) {
				int code = e.getKeyCode();
				if (code >= 0 && code < KEY_CODE_MAX) {
                	touches[code] = true;
				}
        	} 

        	if (e.getID() == KeyEvent.KEY_RELEASED) {
        		int code = e.getKeyCode();
        		if (code >= 0 && code < KEY_CODE_MAX) {
        			touches[code] = false;
        		}
        	}
		}
	}
	
	
	/** Mise a jour des input uniquement a paritr des bouton appuyee*/
	public void majInputsPressed() {
		for(KeyEvent e : buffer) {
			if(e.getID() == KeyEvent.KEY_PRESSED) {
				int code = e.getKeyCode();
				if (code >= 0 && code < KEY_CODE_MAX) {
                	touches[code] = true;
				}
        	} 
		}
	}
	
	
	/** Vider le Buffer*/
	public void viderBuffer() {
		touches = new boolean[KEY_CODE_MAX];
	}
	
	/** Vide le buffer des bouton appuyer*/
	public void viderBufferPressed() {
		touches = new boolean[KEY_CODE_MAX];
		buffer = new LinkedList<KeyEvent>();
	}

	/** Ajoute la touche appuyee au buffer
	 * @param e la touche appuyee
	 */
	public void keyPressed(KeyEvent e) {
		buffer.add(e);
	}
	
	/** Ajoute la touche relachee au buffer
	 * @param e la touche relachee
	 */
	public void keyReleased(KeyEvent e) {
		buffer.add(e);
	}
	
	/** Ajoute une touche tapper au buffer
	 * @param e la touche tapper
	 */
	public void keyTyped(KeyEvent e) {
		buffer.add(e);
	}
}

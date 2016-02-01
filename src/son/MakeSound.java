package son;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.net.*;

public class MakeSound {

    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private boolean stop;
    /**
     * Jouer un son dans le thread courant.
     * @param file Le fichier qui va etre joue
     */
    
    
    public void jouerSon(String file){
        String filename =  "test" + File.separator + "son" + File.separator + file;

        try {
            soundFile = new File(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            // chargement des data dans le jar
	    //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            //URL defaultSound = classLoader.getResource(file);
            //File soundFile = new File(defaultSound.toURI());
            //audioStream = AudioSystem.getAudioInputStream(defaultSound);
           // audioStream = AudioSystem.getAudioInputStream(soundFile);

	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource(file);
            audioStream = AudioSystem.getAudioInputStream(url);


        } catch (Exception e){
            e.printStackTrace();
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1 && !stop) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
    
    /**
     * Jouer un son en parallele du thread courant.
     * @param file Le fichier qui va etre joue
     */
    public static void jouerSonParallele(String file) {
    	final String str = "data/"+file;
    	new Thread(new Runnable() {
		    public void run() {
		    	MakeSound music = new MakeSound();
		    	music.jouerSon(str);
		    }
		}).start();
    }

    
    public static void jouerMusiqueEnBoucle(String file) {
    	final String str = "data/"+file;
    	new Thread(new Runnable() {
		    public void run() {
		    	while (true) {
			    	MakeSound music = new MakeSound();
			    	music.jouerSon(str);    		
		    	}
		    }
		}).start();
    }

    

}


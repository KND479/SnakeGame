/** required package class namespace */
package media;

/**
 * required imports
 */
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * MediaPlayer.java - a collection of useful methods for playing media files
 *
 * @author Raden Pablo
 * @since 19-December-2019
 */
public class MediaPlayer {

    private AudioInputStream audioStream;
    private Clip clip;

    /**
     * Plays the passed file object WAV file as audio
     *
     * @param file the File object (*.wav) to play
     * @return the status of what occurred when trying to play the file
     */
    public String playWAV(File file) {
        try {
            if (clip != null) {
                if (clip.isRunning()) {
                    return "Sound file already playing";
                }
            }
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            return "playing sound file " + file.getName();
        } catch (UnsupportedAudioFileException error) {
            return "Cannot play this file type!";
        } catch (LineUnavailableException error) {
            return "Cannot play any audio files!";
        } catch (IOException error) {
            return "File error";
        }
    }

    /**
     * Stops any playing audio clip
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * Plays the passed filename (relative class path *.wav file) as audio
     *
     * @param filename the relative class path to the file (*.wav) to play
     * @return
     */
    public String playWAV(String filename) {
        try {
            URL url = getClass().getResource(filename);
            URI uri = url.toURI();
            File file = new File(uri);
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audiostream);
            clip.start();
            return "playing sound file";
        } catch (UnsupportedAudioFileException error) {
            return "Cannot play this file type!";
        } catch (LineUnavailableException error) {
            return "Cannot play any audio files!";
        } catch (IOException error) {
            return "File error";
        } catch (URISyntaxException error) {
            return "File URI error";
        }
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

}

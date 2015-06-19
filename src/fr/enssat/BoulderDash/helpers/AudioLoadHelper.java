package fr.enssat.boulderdash.helpers;

import fr.enssat.boulderdash.bridges.SoundJLayerBridge;

/**
 * AudioLoadHelper
 *
 * Manages audio
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class AudioLoadHelper {
    private static String pathToAudioStore = "res/audio";

    private SoundJLayerBridge soundToPlay;

    /**
     * Gets music storage path
     *
     * @param   musicId  Music identifier
     * @return  Music path, with file extension
     */
    private String getMusicPathInAudioStore(String musicId) {
        return this.pathToAudioStore + "/music/" + musicId + ".mp3";
    }

    /**
     * Gets sound storage path
     *
     * @param   soundId  Sound identifier
     * @return  Sound path, with file extension
     */
    private String getSoundPathInAudioStore(String soundId) {
        return this.pathToAudioStore + "/sounds/" + soundId + ".mp3";
    }

    /**
     * Starts game music
     *
     * @param  musicId  Music identifier
     */
    public void startMusic(String musicId) {
        this.soundToPlay = new SoundJLayerBridge(
                this.getMusicPathInAudioStore(musicId)
        );

        soundToPlay.play();
    }

    /**
     * Stops game music
     */
    public void stopMusic() {
        soundToPlay.stop();
    }

    /**
     * Plays a sound
     *
     * @param  soundId  Sound identifier
     */
    public void playSound(String soundId) {
        // TODO
    }
}

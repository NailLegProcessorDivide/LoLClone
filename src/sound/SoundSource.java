package sound;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_LOOPING;
import static org.lwjgl.openal.AL10.AL_PLAYING;
import static org.lwjgl.openal.AL10.AL_SOURCE_RELATIVE;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.AL_TRUE;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alSourcePause;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceStop;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;

public class SoundSource {

    private final int sourceId;

    public SoundSource(boolean loop, boolean relative) {
        int sid;
        try {
            sid = alGenSources();
        }
        catch (Exception e) {
            sid = -1;
        }
        sourceId = sid;
        if(sourceId != -1) {
            if (loop) {
                alSourcei(sourceId, AL_LOOPING, AL_TRUE);
            }
            if (relative) {
                alSourcei(sourceId, AL_SOURCE_RELATIVE, AL_TRUE);
            }
        }
    }

    public void setBuffer(int bufferId) {
        if(sourceId != -1) {
            stop();
            alSourcei(sourceId, AL_BUFFER, bufferId);
        }
    }
    
    public void setGain(float gain) {
        if(sourceId != -1) {
            alSourcef(sourceId, AL_GAIN, gain);
        }
    }

    public void setProperty(int param, float value) {
        if(sourceId != -1) {
            alSourcef(sourceId, param, value);
        }
    }
    
    public void play() {
        if(sourceId != -1) {
            alSourcePlay(sourceId);
        }
    }

    public boolean isPlaying() {
        if(sourceId != -1) {
            return alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_PLAYING;
        }
    	return false;
    }

    public void pause() {
        if(sourceId != -1) {
            alSourcePause(sourceId);
        }
    }

    public void stop() {
        if(sourceId != -1) {
            alSourceStop(sourceId);
        }
    }

    public void cleanup() {
        if(sourceId != -1) {
            stop();
            alDeleteSources(sourceId);
        }
    }
}

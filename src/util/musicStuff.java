package util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class musicStuff {
    public Clip clip;
    public void playMusic(String musicLocation)
    {
        try
        {
            File musicPath = new File(musicLocation);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {
                System.out.println("音乐文件不存在");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void startMusic()
    {
        clip.start();
    }
    public void stopMusic()
    {
        clip.stop();
    }

}

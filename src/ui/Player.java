package ui;

import state.LockedState;
import state.State;
import util.musicStuff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private State state;
    private boolean playing = false;
    private final List<String> playlist = new ArrayList<>();
    private int currentTrack = 0;
    private int music_num;
    String filepath = "";
    public musicStuff musicObject = new musicStuff();


    public Player() {
        this.state = new LockedState(this);
        music_num = find_music_num();
        for (int i = 1; i <= music_num; i++) {
            playlist.add("Track " + i);
        }
    }

    public int find_music_num(){
        int count = 0;
        File file = new File("src/music");	// 需要查找的文件目录
        // 把所有目录、文件放入数组
        File[] files = file.listFiles();
        // 遍历数组每一个元素
        assert files != null;
        for (File f : files) {
            // 判断文件是不是以.txt结尾的文件，并且count++（注意：文件要显示扩展名）
            if (f.getName().endsWith(".wav")) {
                count++;
            }
        }
        return count;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPlaying() {
        return playing;
    }

    public String startPlayback() {
        if(musicObject.clip!=null){
            musicObject.startMusic();
        }else {
            filepath = "src/music/"+currentTrack+".wav";
            musicObject.playMusic(filepath);
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String nextTrack() {
        musicObject.stopMusic();
        currentTrack++;
        if (currentTrack > playlist.size() - 1) {
            currentTrack = 0;
        }
        filepath = "src/music/"+currentTrack+".wav";
        musicObject.playMusic(filepath);
        return "Playing " + playlist.get(currentTrack);
    }

    public String previousTrack() {
        musicObject.stopMusic();
        currentTrack--;
        if (currentTrack < 0) {
            currentTrack = playlist.size() - 1;
        }
        filepath = "src/music/"+currentTrack+".wav";
        musicObject.playMusic(filepath);
        return "Playing " + playlist.get(currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        musicObject.stopMusic();
        this.currentTrack = 0;
    }
}
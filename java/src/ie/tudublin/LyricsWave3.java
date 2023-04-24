package ie.tudublin;

import java.util.ArrayList;

import ddf.minim.*;
import processing.core.PApplet;

public class LyricsWave3 extends PApplet {
    String songFileName = "Moby Duck.mp3";
    String lyricsFileName = "MobyDuck.txt";

    Minim m;
    AudioPlayer ap;
    String[] lyrics;
    int[] timestamps;

    ArrayList<LyricLine> lyricLines = new ArrayList<LyricLine>();

    boolean isPlaying = false;

    float xOffset = 0;
    float xIncrement = 0.1f;
    float scrollSpeed = 0.3f;
    float charSpacing = 15;

    float waveOffset = 0;
    float waveHeight = 50;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        m = new Minim(this);
        ap = m.loadFile(songFileName, 1024);
        ap.play();

        lyrics = loadStrings(lyricsFileName);
        timestamps = new int[lyrics.length];

        for (int i = 0; i < lyrics.length; i++) {
            timestamps[i] = parseTimeStamp(lyrics[i]);
        }

        isPlaying = true;
    }

    private int parseTimeStamp(String line) {
        int timestamp = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")));
        return timestamp;
    }

    public void draw() {
        background(0);
        strokeWeight(2);
        noFill();
    
        beginShape();
        for (float x = 0; x < width; x += 10) {
            float y = height / 2 + sin(radians(x + xOffset)) * height / 45;
            vertex(x, y);
        }
        endShape();
    
        if (ap.isPlaying()) {
            float currentTime = ap.position();
            while (lyricLines.size() < lyrics.length && currentTime >= timestamps[lyricLines.size()]) {
                String currentLyric = lyrics[lyricLines.size()].replaceAll("\\(.*?\\) ?", "");
                lyricLines.add(new LyricLine(currentLyric, currentTime));
            }
    
            float lastLineEnd = -charSpacing; // initialize to a value that ensures first line will always be drawn
            for (int i = 0; i < lyricLines.size(); i++) {
                LyricLine currentLyricLine = lyricLines.get(i);
                String currentLyric = currentLyricLine.lyric;
                float lineStartTime = currentLyricLine.startTime;
                int currentLyricLength = currentLyric.length();
    
                fill(255);
                textAlign(LEFT, CENTER);
                textSize(32);
                float totalTextWidth = textWidth(currentLyric);
                float xStart = width;
    
                if (currentTime >= lineStartTime) {
                    xStart -= scrollSpeed * (currentTime - lineStartTime);
                }
    
                if (xStart > lastLineEnd) {
                    for (int j = 0; j < currentLyricLength; j++) {
                        char currentChar = currentLyric.charAt(j);
                        float x = xStart + j * charSpacing;
                        float y = height / 2 + sin(radians(x + xOffset)) * height / 45;
    
                        pushMatrix();
                        translate(x, y);
                        text(currentChar, 0, 0);
                        popMatrix();
                    }
    
                    lastLineEnd = xStart + totalTextWidth;
                }
    
                currentLyricLine.isDrawn = true;
            }
    
            // remove lines that have already been drawn and are offscreen
            for (int i = 0; i < lyricLines.size(); i++)
            {
                LyricLine currentLyricLine = lyricLines.get(i);

                if (currentLyricLine.isDrawn && (charSpacing * currentLyricLine.lyric.length() +100000000 < -xOffset))
                {
                    lyricLines.remove(i);
                    i--; // decrement i to account for the removed element
                }
            }
        }
    
        xOffset += xIncrement;
    }
      
    
    
    class LyricLine
    {
        String lyric;
        float startTime;
        boolean isDrawn;
        
        LyricLine(String lyric, float startTime)
        {
            this.lyric = lyric;
            this.startTime = startTime;
            this.isDrawn = false;
        }
    }
}

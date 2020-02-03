package shop.audio;

import lombok.Getter;

@Getter
public class Track {
    String name;
    long duration;

    public Track(String name, long duration) {
        this.name = name;
        this.duration = duration;
    }
}

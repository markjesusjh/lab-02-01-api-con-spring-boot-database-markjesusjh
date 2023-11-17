package com.example.demo.playlist.domain;

import com.example.demo.song.domain.Song;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@SequenceGenerator(
        name = "PlaylistDB",
        sequenceName = "PlayList_sequence",
        initialValue = 100,
        allocationSize = 1
)

@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinTable(
            name = "playlist_song",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )

    private List<Song> songs;

    private String coverImage;

    public Playlist() {}

    public Playlist(Long id, String title, List<Song> songs, String coverImage) {
        this.id = id;
        this.title = title;
        this.songs = songs;
        this.coverImage = coverImage;
    }

    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public List<Song> getSongs(){
        return this.songs;
    }
    public String getCover_image(){
        return this.coverImage;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title){
        if(title==null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }
    public void setSongs(List<Song> songs){
        if(songs==null){
            throw new IllegalArgumentException("Release date cannot be null");
        }
        this.songs=songs;
    }
    public void setCover_image(String coverImage){
        if(coverImage==null || coverImage.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.coverImage = coverImage;
    }


    @Override
    public String toString() {
        return "Playlist{" + "id=" + this.id + ", title='" + this.title + '\'' + ", songs='" + this.songs + '\'' + ", cover_image='" + this.coverImage + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Playlist))
            return false;
        Playlist playlist = (Playlist) o;
        return this.id.equals(playlist.id);
    }

}

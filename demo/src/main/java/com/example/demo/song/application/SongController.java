package com.example.demo.song.application;


import com.example.demo.song.domain.Song;
import com.example.demo.song.domain.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songRepository.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> song(@RequestBody Song song) {
        songRepository.save(song);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> song(@PathVariable ("id") Long id,@RequestBody Song pe){
        Song song = songRepository.findById(id).orElse(null);

        if(song == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        song.setId(id);
        song.setTitle(pe.getTitle());
        song.setArtist(pe.getArtist());
        song.setAlbum(pe.getAlbum());
        song.setReleaseDate(pe.getReleaseDate());
        song.setGenre(pe.getGenre());
        song.setDuration(pe.getDuration());
        song.setCoverImage(pe.getCoverImage());
        song.setSpotifyUrl(pe.getSpotifyUrl());

        songRepository.save(song);

        return new ResponseEntity<>(song,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()) {
            songRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        Song song = songRepository.findById(id).orElse(null);

        if (song == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                Field field = Song.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(song, fieldValue);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        songRepository.save(song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
}

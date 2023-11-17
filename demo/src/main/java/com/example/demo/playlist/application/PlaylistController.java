package com.example.demo.playlist.application;
import com.example.demo.playlist.domain.Playlist;
import com.example.demo.playlist.domain.PlaylistRepository;

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
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping
    public ResponseEntity<List<Playlist>> playlist() {
        List<Playlist> playlist = playlistRepository.findAll();
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> playlist(@RequestBody Playlist playlist) {
        playlistRepository.save(playlist);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> playlist(@PathVariable ("id") Long id,@RequestBody Playlist pe){
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if(playlist == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        playlist.setId(id);
        playlist.setTitle(pe.getTitle());
        playlist.setSongs(pe.getSongs());
        playlist.setCover_image(pe.getCover_image());

        playlistRepository.save(playlist);

        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()) {
            playlistRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if (playlist == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                Field field = Playlist.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(playlist, fieldValue);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        playlistRepository.save(playlist);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }
}

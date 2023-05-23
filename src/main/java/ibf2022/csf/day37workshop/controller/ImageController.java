package ibf2022.csf.day37workshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.csf.day37workshop.service.PostsService;

@RestController
public class ImageController {

    // @Autowired
    // PostsService postsService;
    
    // @GetMapping(path = "/uploaded/{post_id}")
    // public ResponseEntity<byte[]> getImage(@PathVariable String post_id) {
    //     Optional<byte[]> image = postsService.getPicture(post_id);

    //     if (image.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     } else {
    //         return ResponseEntity.ok().header("Content-Type", "image/*").body(image.get());
    //     }
    // }
}

package ibf2022.csf.day37workshop.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.csf.day37workshop.service.PostsService;

@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    String contentType = "";
    
    @PostMapping(path = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(@RequestPart String title, @RequestPart String content, @RequestPart MultipartFile picture, Model model) throws IOException {
        String post_id = "";

        try {
            post_id = postsService.upload(title, content, picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contentType = picture.getContentType();
        model.addAttribute("filename", picture.getOriginalFilename());
        model.addAttribute("size", picture.getSize());
        model.addAttribute("contentType", picture.getContentType());
        model.addAttribute("uploaded_id", post_id);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("picture", "/uploaded/" + post_id);

        // Embed image data directly, see upload.html for info (base64)
        StringBuilder sb = new StringBuilder();
        String encoded = Base64.getEncoder().encodeToString(picture.getBytes());
        System.out.println(encoded.substring(0, 100));
        String imageData = sb.append("data:").append(contentType).append(";base64,").append(encoded).toString();
        model.addAttribute("pic", imageData);
        model.addAttribute("pic2", encoded);
        
        return "uploaded";
    }

    @GetMapping(path = "/uploaded/{post_id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String post_id) {
        Optional<byte[]> image = postsService.getPicture(post_id);

        if (image.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(image.get());
        }
    }

}

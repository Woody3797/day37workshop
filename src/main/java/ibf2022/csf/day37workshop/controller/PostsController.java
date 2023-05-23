package ibf2022.csf.day37workshop.controller;

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

import ibf2022.csf.day37workshop.model.Post;
import ibf2022.csf.day37workshop.service.PostsService;

@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;
    
    @PostMapping(path = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(@RequestPart String title, @RequestPart String content, @RequestPart MultipartFile picture, Model model) {
        try {
            boolean results = postsService.upload(title, content, picture);
            model.addAttribute("filename", picture.getOriginalFilename());
            model.addAttribute("contentType", picture.getContentType());
            model.addAttribute("uploaded", results);
            model.addAttribute("title", title);
            model.addAttribute("content", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "uploaded";
    }

    @GetMapping(path = "/article/{post_id}")
    @ResponseBody
    public ResponseEntity<byte[]> getArticle(@PathVariable String post_id) {
        Optional<Post> post = null;
        return null;
    }

}

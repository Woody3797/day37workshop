package ibf2022.csf.day37workshop.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.csf.day37workshop.repository.ImageRepository;
import ibf2022.csf.day37workshop.repository.PostsRepository;

@Service
public class PostsService {
    
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional(rollbackFor = IOException.class)
    public String upload(String title, String content, MultipartFile multipart) throws IOException {
        
        String post_id = UUID.randomUUID().toString().substring(0, 8);
        
        // MySQL will rollback if MongoDB operation throws an exception
        imageRepository.savePicture(post_id, multipart.getBytes());

        // MongoDB
        postsRepository.upsertPost(post_id, title, content);

        return post_id;
    }

    public Optional<byte[]> getPicture(String post_id) {
        Optional<byte[]> picture = imageRepository.getPicture(post_id);

        return picture;
    }
}

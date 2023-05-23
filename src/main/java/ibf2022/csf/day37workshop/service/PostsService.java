package ibf2022.csf.day37workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.csf.day37workshop.repository.PostsRepository;

@Service
public class PostsService {
    
    @Autowired
    private PostsRepository postsRepository;

    public boolean upload(String title, String content, MultipartFile multipart) {
        try {
            return postsRepository.savePicture(title, content, multipart.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

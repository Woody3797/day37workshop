package ibf2022.csf.day37workshop.repository;

import java.util.Optional;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.csf.day37workshop.model.Post;

@Repository
public class PostsRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean savePicture(String title, String content, byte[] picture) {
        String post_id = UUID.randomUUID().toString().substring(0, 8);
        Integer sqlrows = jdbcTemplate.update(DBQueries.SQL_SAVE_PHOTO, post_id, picture);

        Document doc = new Document();
        doc.put("_id", post_id);
        doc.put("title", title);
        doc.put("content", content);

        Query query = new Query(Criteria.where("post_id").is(post_id));
        Update update = new Update();
        update.set("title", title).set("content", content);

        mongoTemplate.upsert(query, update, Document.class, "posts");

        return sqlrows > 0 ? true: false;
    }

}

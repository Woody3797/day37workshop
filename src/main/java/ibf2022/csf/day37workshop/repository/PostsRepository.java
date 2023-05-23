package ibf2022.csf.day37workshop.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PostsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void upsertPost(String post_id, String title, String content) {

        // Document doc = new Document();
        // doc.put("_id", post_id);
        // doc.put("title", title);
        // doc.put("content", content);

        Query query = new Query(Criteria.where("_id").is(post_id));
        Update update = new Update();
        update.set("_id", post_id).set("title", title).set("content", content);

        mongoTemplate.upsert(query, update, "posts");
    }

}

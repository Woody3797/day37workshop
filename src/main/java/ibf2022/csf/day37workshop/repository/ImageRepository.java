package ibf2022.csf.day37workshop.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SAVE_PHOTO = """
            INSERT into posts(post_id, picture) values (?, ?)
            """;
    
    public boolean savePicture(String post_id, byte[] picture) {

        Integer sqlrows = jdbcTemplate.update(SQL_SAVE_PHOTO, post_id, picture);

        return sqlrows > 0 ? true: false;
    }


    private static final String SQL_FIND_PICTURE_BY_ID = """
            SELECT picture from posts WHERE post_id = ?
            """;

    public Optional<byte[]> getPicture(String post_id) {
        Optional<byte[]> picture = jdbcTemplate.query(SQL_FIND_PICTURE_BY_ID, rs -> {
            if (rs.next()) {
                byte[] pic = rs.getBytes("picture");
                return Optional.of(pic);
            } else {
                return Optional.empty();
            }
        }, post_id);

        return picture;
    }
    
}

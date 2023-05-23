package ibf2022.csf.day37workshop.repository;

public class DBQueries {
    
    public static final String SQL_SAVE_PHOTO = """
            INSERT into posts(post_id, picture) values (?, ?)
            """;

    public static final String SQL_FIND_PICTURE_BY_ID = """
            SELECT * from posts WHERE post_id = ?
            """;
}

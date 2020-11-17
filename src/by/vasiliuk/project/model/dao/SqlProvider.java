package by.vasiliuk.project.model.dao;

public class SqlProvider {
    public static final String SQL_FIND_ALL_ADDS = "SELECT advertId, advertTitle, advertText FROM adverts";
    public static final String SQL_FIND_ALL_USERS = "SELECT userId, userName, userAverageRating, userRole, userStatus FROM users";
    public static final String SQL_INNER_JOIN_ADVERT_USER = "SELECT from users users.userId ON adverts INNER JOIN adverts.userId=users.userId";
    public static final String SQL_FIND_ADVERT_BY_ID = "SELECT advertId, advertTitle, advertText FROM adverts WHERE advertId = ?";
    public static final String SQL_FIND_USERS_ADVERTISEMENTS = "SELECT advertId, advertTitle, advertText FROM adverts INNER JOIN users ON users.userId = ? WHERE ";
    public static final String SQL_ADVERT_SAVE = "INSERT INTO adverts (advertTitle, advertText, users_userId, sections_sectionId) VALUES (?, ?, ?, ?)";
    public static final String SQL_ADVERT_DELETE = "DELETE FROM adverts WHERE advertId = ?";
    public static final String SQL_FIND_BY_SECTION_ID = "SELECT a.advertId, a.advertTitle, a.advertText,\" +\n" +
            "                    \" a.userId FROM adverts a WHERE a.sectionId = ?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT userId, userName, userAverageRating FROM users WHERE userId = ?";
    public static final String SQL_FIND_PASS_BY_USER_NAME = "SELECT userPass, userId, userRole, userStatus FROM users WHERE userName = ?";
    public static final String SQL_FIND_BY_USER_NAME = "SELECT FROM users userId WHERE username = ?";
    public static final String SQL_USER_SAVE = "INSERT INTO users (userName, email, userPass, userAverageRating) VALUES (?,?,?,?)";
    public static final String SQL_USER_ARCHIVE = "UPDATE users SET userStatus = '-1' WHERE userName = ?";
    public static final String SQL_ADVERTISEMENT_UPDATE = "UPDATE adverts SET advertTitle = ?, advertText = ? WHERE advertId = ?";
    public static final String SQL_GET_SECTIONS = "SELECT sectionId, sectionTitle FROM sections";
    public static final String SQL_CREATE_SECTIONS = "INSERT INTO sections sectionTitle VALUES  (?)";
    public static final String SQL_REMOVE_SECTIONS = "DELETE FROM comments WHERE commentId = ?";
    public static final String SQL_CHECK_BAN = "SELECT FROM users userId WHERE userStatus = ?";
}
// select
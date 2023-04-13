package tfip.day26workshop.model;

import java.time.LocalDateTime;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Game {

    private String gameId;
    // private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public Game() {
    }

    public Game(String gameId, String name, Integer year, Integer ranking, Integer usersRated, String url,
            String image) {
        this.gameId = gameId;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.usersRated = usersRated;
        this.url = url;
        this.image = image;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    // public Integer getGid() {
    // return gid;
    // }

    // public void setGid(Integer gid) {
    // this.gid = gid;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Game [game_id=" + gameId + ", name=" + name + ", year=" + year + ", ranking=" + ranking
                + ", usersRated="
                + usersRated + ", url=" + url + ", image=" + image + "]";
    }

    // marshalling

    public JsonObject toJSON() {
        return toJSONObjectBuilder()
                .build();
    }

    private JsonObjectBuilder toJSONObjectBuilder() {
        return Json.createObjectBuilder()
                .add("game_id", getGameId())
                .add("name", getName())
                .add("year", getYear())
                .add("ranking", getRanking())
                .add("users_rated", getUsersRated())
                .add("url", getUrl())
                .add("image", getImage());
    }

    public JsonObject toJSONTimeStamp() {
        return toJSONObjectBuilder()
                .add("timestamp", LocalDateTime.now().toString())
                .build();
    }

    public JsonObject toJSONSummary() {
        return Json.createObjectBuilder()
                .add("game_id", getGameId())
                .add("name", getName())
                .build();
    }

    // unmarshalling
    public static Game create(Document d) {
        return new Game(
                d.getObjectId("_id").toString(),
                d.getString("name"),
                d.getInteger("year"),
                d.getInteger("ranking"),
                d.getInteger("users_rated"),
                d.getString("url"),
                d.getString("image"));
    }

}

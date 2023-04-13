package tfip.day26workshop.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Games {

    private List<Game> games;
    private Integer offset;
    private Integer limit;
    private LocalDateTime timeStamp;

    public Games() {
    }

    public Games(List<Game> games, Integer offset, Integer limit, LocalDateTime timeStamp) {
        this.games = games;
        this.offset = offset;
        this.limit = limit;
        this.timeStamp = timeStamp;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Games [games=" + games + ", offset=" + offset + ", limit=" + limit + ", timeStamp=" + timeStamp + "]";
    }

    // marshalling
    public JsonObject toJSON() {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        getGames().forEach(g -> ab.add(g.toJSONSummary()));

        return Json.createObjectBuilder()
                .add("games", ab)
                .add("offset", getOffset())
                .add("limit", getLimit())
                .add("total", getGames().size())
                .add("timeStamp", getTimeStamp().toString())
                .build();
    }
}

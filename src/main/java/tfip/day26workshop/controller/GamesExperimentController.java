package tfip.day26workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import tfip.day26workshop.model.Game;
import tfip.day26workshop.model.Games;
import tfip.day26workshop.service.GamesService;

@RestController
@RequestMapping(path = "/api")
public class GamesExperimentController {

    @Autowired
    GamesService svc;

    @GetMapping("/games/timestamp")
    public ResponseEntity<String> getAllGamesTimeStamp() {
        Games result = svc.getGames(0, 3);

        JsonArrayBuilder ab = Json.createArrayBuilder();
        result.getGames().forEach(g -> ab.add(g.toJSON()));

        JsonObjectBuilder ob = Json.createObjectBuilder()
                .add("games", ab)
                .add("offset", result.getOffset())
                .add("limit", result.getLimit())
                .add("total", result.getGames().size())
                .add("timeStamp", result.getTimeStamp().toString());

        JsonObject obj = ob.build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(obj.toString());
    }

    @GetMapping("/games/object/timestamp")
    public ResponseEntity<String> getAllGamesAsObjectWithTimeStamp() {
        Games result = svc.getGames(0, 3);

        JsonObjectBuilder ob = Json.createObjectBuilder();
        Integer i = 1;
        for(Game g : result.getGames()) {
            ob.add("object"+i, g.toJSON());
            i++;
        }

        ob.add("offset", result.getOffset());
        ob.add("limit", result.getLimit());
        ob.add("total", result.getGames().size());
        ob.add("timeStamp", result.getTimeStamp().toString());

        JsonObject obj = ob.build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(obj.toString());

    }

    @GetMapping("/games")
    public ResponseEntity<String> getAllGames() {
        List<Game> result = svc.getGames(0, 3).getGames();

        JsonArrayBuilder ab = Json.createArrayBuilder();
        result.forEach(g -> ab.add(g.toJSON()));

        JsonArray arr = ab.build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(arr.toString());
    }

    @GetMapping("/games/object")
    public ResponseEntity<String> getAllGamesAsObject() {
        List<Game> result = svc.getGames(0, 3).getGames();

        JsonObjectBuilder ob = Json.createObjectBuilder();
        Integer i = 1;
        for(Game g : result) {
            ob.add("object"+i, g.toJSON());
            i++;
        }

        JsonObject obj = ob.build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(obj.toString());

    }
    
    @GetMapping("/games/list")
    public ResponseEntity<String> getAllGamesAsList() {
        List<Game> result = svc.getGames(0, 3).getGames();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping("/games/return")
    public ResponseEntity<String> getAllGamesReturnLoop() {
        List<Game> games = svc.getGames(0, 3).getGames();

        for (Game g : games) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(g.toJSON().toString());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Not found");
    }
}

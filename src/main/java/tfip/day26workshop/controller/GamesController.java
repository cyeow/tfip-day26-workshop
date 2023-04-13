package tfip.day26workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import tfip.day26workshop.model.Game;
import tfip.day26workshop.model.Games;
import tfip.day26workshop.service.GamesService;

@RestController
public class GamesController {

    @Autowired
    GamesService svc;

    // task a. Browsing the games by name
    @GetMapping(path = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> browseGamesByName(@RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "25") Integer limit) {

        Games result = svc.getGames(offset, limit);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toJSON().toString());
    }

    // task b. browse the games by rank
    @GetMapping(path = "/games/rank", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> browseGamesByRank(@RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "25") Integer limit) {

        Games result = svc.getGamesByRank(offset, limit);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toJSON().toString());
    }

    // task c. get game details
    @GetMapping(path = "/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGameDetails(@PathVariable String gameId) {
        Game result = svc.getGameById(gameId);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(generateMsgJsonString("No game found with id " + gameId + "."));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toJSONTimeStamp().toString());
    }

    private String generateMsgJsonString(String msg) {
        return Json.createObjectBuilder()
                .add("message", msg)
                .build()
                .toString();
    }

}

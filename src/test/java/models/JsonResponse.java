package models;

import lombok.Data;
import kong.unirest.JsonNode;

@Data
public class JsonResponse {
    private int statusCode;
    private JsonNode body;

    public JsonResponse(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
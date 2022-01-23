package utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import models.JsonResponse;
import java.io.File;

public class ApiUtils {
    static {
        Unirest.config().defaultBaseUrl(Parser.parseConfig("methodUrl"));
    }

    public static JsonResponse post(String requestPath) {
        HttpResponse<JsonNode> response = Unirest.post(requestPath).asJson();
        Unirest.shutDown();
        return new JsonResponse (response.getStatus(),response.getBody());
    }

    public static JsonResponse upLoad(String requestPath, String filePath, String typeOfFile){
        HttpResponse<JsonNode> response = Unirest.post(requestPath).field(typeOfFile, new File(filePath)).asJson();
        Unirest.shutDown();
        return new JsonResponse (response.getStatus(),response.getBody());
    }
}
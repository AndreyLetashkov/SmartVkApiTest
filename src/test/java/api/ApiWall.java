package api;

import aquality.selenium.browser.AqualityServices;
import constants.VkRequest;
import models.JsonResponse;
import utils.ApiUtils;
import utils.Parser;
import java.util.LinkedList;
import java.util.List;

public class ApiWall {
    protected static final String OWNER_ID = Parser.parseTestData("ownerId");
    protected static final String ACCESS_TOKEN = Parser.parseTestData("accessToken");
    protected static final String API_VERSION = Parser.parseTestData("apiVersion");

    public static String postTextMsgToTheWall(String text) {
        String request = String.format("%s?owner_id=%s&message=%s&access_token=%s&v=%s", VkRequest.WALL_POST.getVkRequests(), OWNER_ID, text, ACCESS_TOKEN, API_VERSION);
        JsonResponse jsonResponse = ApiUtils.post(request);
        System.out.println();
        return jsonResponse.getBody().toPrettyString().replaceAll("[^0-9]", "");
    }

    public static String createCommentPostOnTheWall(String text, String postId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&access_token=%s&v=%s", VkRequest.WALL_CREATE_COMMENT.getVkRequests(), OWNER_ID, postId, text, ACCESS_TOKEN, API_VERSION);
        JsonResponse jsonResponse = ApiUtils.post(request);
        return jsonResponse.getBody().toPrettyString().replaceAll("[^0-9]", "");
    }

    public static void editPostOnTheWall(String text, String postId, String photoId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&attachments=photo%s_%s&access_token=%s&v=%s",
                VkRequest.WALL_EDIT.getVkRequests(), OWNER_ID, postId, text, OWNER_ID, photoId, ACCESS_TOKEN, API_VERSION);
        ApiUtils.post(request);
    }

    public static List<String> uploadPhotoOnTheWall(String uploadUrl) {
        JsonResponse jsonResponse = ApiUtils.upLoad(uploadUrl, Parser.parseConfig("pathPhoto"), Parser.parseTestData("typeOfFile"));
        List<String> photoObj = new LinkedList<>();
        photoObj.add(jsonResponse.getBody().getObject().getString("photo"));
        photoObj.add(jsonResponse.getBody().getObject().getString("server"));
        photoObj.add(jsonResponse.getBody().getObject().getString("hash"));
        AqualityServices.getLogger().info(jsonResponse.getBody().toPrettyString());
        return photoObj;
    }

    public static void deletePostFromTheWall(String postId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&access_token=%s&v=%s", VkRequest.WALL_DELETE.getVkRequests(), OWNER_ID, postId, ACCESS_TOKEN, API_VERSION);
        ApiUtils.post(request);
    }

    public static boolean isPostLiked(String postId) {
        String request = String.format("%s?owner_id=%s&user_id=%s&type=%s&item_id=%s&access_token=%s&v=%s",
                VkRequest.IS_LIKED.getVkRequests(), OWNER_ID, OWNER_ID, Parser.parseTestData("objectType"), postId, ACCESS_TOKEN, API_VERSION);
        JsonResponse jsonResponse = ApiUtils.post(request);
        return jsonResponse.getBody().toPrettyString().replaceAll("[^0-9]", "").contains(Parser.parseTestData("isPostLiked"));
    }

    public static String getWallPhotoUploadServer() {
        String request = String.format("%s?user_id=%s&access_token=%s&v=%s", VkRequest.GET_WALL_PHOTO_UPLOAD_SERVER.getVkRequests(), OWNER_ID, ACCESS_TOKEN, API_VERSION);
        JsonResponse jsonResponse = ApiUtils.post(request);
        AqualityServices.getLogger().info(jsonResponse.getBody().toPrettyString());
        return jsonResponse.getBody().getObject().getJSONObject("response").getString("upload_url");
    }

    public static String saveUploadWallPhoto(String photo, String server, String hash) {
        String request = String.format("%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s", VkRequest.SAVE_WALL_PHOTO.getVkRequests(), OWNER_ID, photo, server, hash, ACCESS_TOKEN, API_VERSION);
        JsonResponse jsonResponse = ApiUtils.post(request);
        AqualityServices.getLogger().info(jsonResponse.getBody().toPrettyString());
        return jsonResponse.getBody().getObject().getJSONArray("response").getJSONObject(0).getString("id");
    }

}
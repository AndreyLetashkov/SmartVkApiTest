package constants;

public enum VkRequest {
    WALL_EDIT("wall.edit"),
    WALL_POST("wall.post"),
    WALL_DELETE("wall.delete"),
    WALL_CREATE_COMMENT("wall.createComment"),
    GET_WALL_PHOTO_UPLOAD_SERVER("photos.getWallUploadServer"),
    SAVE_WALL_PHOTO("photos.saveWallPhoto"),
    IS_LIKED("likes.isLiked");

    private final String vkRequests;

    VkRequest(String vkRequests) { this.vkRequests = vkRequests; }

    public String getVkRequests() { return vkRequests; }
}
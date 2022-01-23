package utils;

import api.ApiWall;
import aquality.selenium.browser.AqualityServices;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import lombok.SneakyThrows;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PhotoUtils {
    public static String getPhotoId() {
        AqualityServices.getLogger().info("Get wall photo upload Server.");
        String uploadUrl = ApiWall.getWallPhotoUploadServer();
        AqualityServices.getLogger().info("Upload photo on the wall.");
        List<String> photoObj = ApiWall.uploadPhotoOnTheWall(uploadUrl);
        String photo = photoObj.get(0);
        String server = photoObj.get(1);
        String hash = photoObj.get(2);
        AqualityServices.getLogger().info("Save upload wall photo.");
        return ApiWall.saveUploadWallPhoto(URLEncoder.encode(photo, StandardCharsets.UTF_8),server,hash);
    }

    @SneakyThrows
    public static void savePhoto(String path) {
        URL url = new URL(path);
        BufferedImage img = ImageIO.read(url);
        File file = new File(Parser.parseConfig("pathSavedPhoto"));
        ImageIO.write(img, "png", file);
    }

    public static ImageComparisonResult runComparison() {
        return new ImageComparison(
                ImageComparisonUtil.readImageFromResources(Parser.parseConfig("pathPhoto")),
                ImageComparisonUtil.readImageFromResources(Parser.parseConfig("pathSavedPhoto"))
        )
                .compareImages();
    }
}
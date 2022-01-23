package test;

import api.ApiWall;
import aquality.selenium.browser.AqualityServices;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import forms.PostForm;
import helper.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.*;

public class SmartVkApiTest extends BaseTest {
    @Test()
    void test() {
        AuthenticationPage authenticationPage = new AuthenticationPage();
        AqualityServices.getLogger().info("Authorization passed.");
        authenticationPage.authentication(Parser.parseTestData("username"), Parser.parseTestData("password"));

        NewsPage newsPage = new NewsPage();
        AqualityServices.getLogger().info("Navigate my page.");
        newsPage.navigateMyPage();

        AqualityServices.getLogger().info("Create a post with randomly generated text on the wall and get the id.");
        String postId = ApiWall.postTextMsgToTheWall(RandomUtils.setRandomText());

        PostForm postForm = new PostForm(postId);
        AqualityServices.getLogger().info("Get randomly generated text from a post on the wall.");
        String textFromPost = postForm.getTextFromPost();
        AqualityServices.getLogger().info("Compare text.");
        Assert.assertEquals(
                textFromPost,
                RandomUtils.getRandomText(),
                "Different text."
        );
        AqualityServices.getLogger().info("Get hrefAuthor from a post on the wall.");
        String authorHref = postForm.getAuthorFromPost(postId, Parser.parseTestData("attribute"));
        AqualityServices.getLogger().info("Compare author.");
        Assert.assertEquals(
                authorHref,
                Parser.parseTestData("authorHref"),
                "Different author."
        );

        AqualityServices.getLogger().info("Get PhotoId.");
        String photoId = PhotoUtils.getPhotoId();
        AqualityServices.getLogger().info("Edit text and upload picture.");
        ApiWall.editPostOnTheWall(RandomUtils.setRandomText(), postId, photoId);

        AqualityServices.getLogger().info("Get randomly generated text from a post on the wall.");
        String textFromEditedPost = postForm.getTextFromPost();
        AqualityServices.getLogger().info("Compare text.");
        Assert.assertEquals(
                textFromEditedPost,
                RandomUtils.getRandomText(),
                "Different text."
        );
        AqualityServices.getLogger().info("Save photo from a post on the wall.");
        PhotoUtils.savePhoto(postForm.getPhotoLink("style"));
        AqualityServices.getLogger().info("Compare photo.");
        Assert.assertEquals(ImageComparisonState.MATCH, PhotoUtils.runComparison().getImageComparisonState());

        AqualityServices.getLogger().info("Add a comment to a post with random text.");
        String postCommentId = ApiWall.createCommentPostOnTheWall(RandomUtils.setRandomText(), postId);

        AqualityServices.getLogger().info("Open comments.");
        postForm.openComments();
        AqualityServices.getLogger().info("Get randomly generated text from comment.");
        String textFromComment = postForm.getTextFromComment(postCommentId);
        AqualityServices.getLogger().info("Compare text.");
        Assert.assertEquals(
                textFromComment,
                RandomUtils.getRandomText(),
                "Different text."
        );
        AqualityServices.getLogger().info("Get hrefAuthor from a comment.");
        String commentAuthorHref = postForm.getAuthorFromPost(postCommentId, Parser.parseTestData("attribute"));
        AqualityServices.getLogger().info("Compare author.");
        Assert.assertEquals(
                commentAuthorHref,
                Parser.parseTestData("authorHref"),
                "Different author."
        );

        AqualityServices.getLogger().info("Like post.");
        postForm.likePost();

        AqualityServices.getLogger().info("Did post liked?");
        Assert.assertTrue(ApiWall.isPostLiked(postId),"Post wasn't liked.");

        AqualityServices.getLogger().info("Delete post.");
        ApiWall.deletePostFromTheWall(postId);
        AqualityServices.getLogger().info("Is post deleted?");
        Assert.assertTrue(
                postForm.isPostDeleted(),
                "Post wasn't deleted."
        );
    }
}
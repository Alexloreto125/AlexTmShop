package AlexSpring.AlexTmShop.services;

import java.util.UUID;

public class ResponseUser {
    private String accessToken;
    private UUID userId;

    public ResponseUser(String accessToken, UUID userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UUID getUserId() {
        return userId;
    }
}

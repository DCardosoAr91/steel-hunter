package com.steelhunter.smoke;

import com.steelhunter.auth.TokenManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectSetupTest {

    @Test
    void shouldRunProjectSuccessfully(){
        assertTrue(true);
    }

    @Test
    void shouldGenerateSpotifyToken(){
        assertNotNull(TokenManager.getToken());
    }

}

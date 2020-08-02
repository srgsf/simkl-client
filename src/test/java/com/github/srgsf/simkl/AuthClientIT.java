package com.github.srgsf.simkl;

import com.github.srgsf.simkl.auth.AccessToken;
import com.github.srgsf.simkl.auth.DeviceCode;
import com.github.srgsf.simkl.auth.SimklAuthClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

class AuthClientIT {

    private static SimklAuthClient authClient;

    @BeforeAll
    static void setup() {
        authClient = new SimklAuthClient.Builder()
                .clientCredentials(System.getProperty("client_id"),
                        System.getProperty("client_secret"), System.getProperty("redirect_uri"))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();
    }

    @Test
    void deviceCode() throws IOException {
        Response<DeviceCode> response = authClient.deviceCode();
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        DeviceCode code = response.body();

        Response<AccessToken> tokenResp = authClient.accessToken(SimklAuthClient.GrantType.device_code, code.userCode);
        Assertions.assertFalse(tokenResp.isSuccessful());
        Assertions.assertNotNull(tokenResp.errorBody());
        String error = authClient.error(tokenResp.errorBody());
        Assertions.assertTrue(SimklAuthClient.ERR_AUTHORIZATION_PENDING.equals(error) ||
                SimklAuthClient.ERR_SLOW_DOWN.equals(error));
    }

}
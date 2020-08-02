package com.github.srgsf.simkl;

import com.github.srgsf.simkl.auth.AccessToken;
import com.github.srgsf.simkl.auth.DeviceCode;
import com.github.srgsf.simkl.auth.SimklAuthClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

class AuthClientTest {

    private static MockWebServer webServer;
    private static final String CLIENT_ID = "API_CLIENT_USER";
    private static final String CLIENT_SECRET = "API_CLIENT_SECRET";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static SimklAuthClient client;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = new SimklAuthClient.Builder()
                .clientCredentials(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI)
                .baseUrl(webServer.url("/").toString())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }


    @Test
    void authUrl() {
        String random = "123String";
        HttpUrl url = SimklAuthClient.authorizationRequestUrl("clientId", REDIRECT_URI, random);
        Assertions.assertEquals("clientId", url.queryParameter("client_id"));
        Assertions.assertEquals(REDIRECT_URI, url.queryParameter("redirect_uri"));
        Assertions.assertEquals(random, url.queryParameter("state"));
        Assertions.assertEquals("/oauth/authorize", url.encodedPath());
        Assertions.assertEquals("simkl.com", url.host());
        Assertions.assertEquals("https", url.scheme());
    }

    @Test
    void testAuthCode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/access_token.json")));
        Response<AccessToken> response = client.accessToken(SimklAuthClient.GrantType.authorization_code, "123");
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        AccessToken token = response.body();
        Assertions.assertEquals("a5cd48f1529a2d794f1912337c626326c7dddfsa559a6d794w1943237c622329", token.accessToken);
    }

    @Test
    void testPinCode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/pin_access_token.json")));
        Response<AccessToken> response = client.accessToken(SimklAuthClient.GrantType.device_code, "123");
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        AccessToken token = response.body();
        Assertions.assertEquals("ACCESS_TOKEN", token.accessToken);
    }


    @Test
    void testDeviceCode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/device_code.json")));
        Response<DeviceCode> response = client.deviceCode();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());
        DeviceCode code = response.body();
        Assertions.assertEquals("USER_CODE", code.userCode);
        Assertions.assertEquals(900, code.expiresIn);
        Assertions.assertEquals(5, code.interval);
    }

    @Test
    void testError() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/auth_pending.json")));
        Response<AccessToken> response = client.accessToken(SimklAuthClient.GrantType.device_code, "234");
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertNotNull(response.errorBody());
        Assertions.assertEquals(418, response.code());
        String error = client.error(response.errorBody());
        Assertions.assertEquals(SimklAuthClient.ERR_AUTHORIZATION_PENDING, error);
    }
}

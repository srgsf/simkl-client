package com.github.srgsf.simkl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class Utils {


    static Buffer readFromFile(String path) {
        Buffer rv = new Buffer();
        try (InputStream is = Utils.class.getClassLoader().getResourceAsStream(path)) {
            rv.readFrom(Objects.requireNonNull(is));
        } catch (IOException ex) {
            Assertions.fail("Unable to read " + path);
        }
        return rv;
    }

    static boolean checkAuthorization(RecordedRequest request) {
        String retVal = request.getHeader("Authorization");
        if (retVal == null) {
            return false;
        }
        Assertions.assertEquals("Bearer TOKEN", retVal);
        return true;
    }

    static Simkl initClient() {
        final String token = System.getProperty("token");
        return new Simkl.Builder()
                .clientId(System.getProperty("client_id"))
                .tokenProvider(() -> token)
                .build();
    }

    static Simkl initClient(String baseUrl) {

        return new Simkl.Builder()
                .clientId("client_id")
                .tokenProvider(() -> "TOKEN")
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)).build())

                .build();
    }
}

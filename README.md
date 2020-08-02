# simkl-client
![Tests](https://github.com/srgsf/simkl-client/workflows/Tests/badge.svg?branch=master&event=push)

A Java wrapper around https://simkl.com [API](https://simkl.docs.apiary.io/#introduction/about-simkl-api) using Retrofit2 and Moshi

Pull requests are welcome.

## Usage

Add the following dependency to your Gradle project:

```groovy
implementation 'com.github.srgsf:simkl-client:0.1'
```

Or for Maven:

```xml
<dependency>
  <groupId>com.github.srgsf</groupId>
  <artifactId>simkl-client</artifactId>
  <version>0.1</version>
</dependency>
```


### Authorization
Some of SIMKL API methods can only be accessed via OAuth 2.0.

Current version supports Web Application flow and Limited Device flow. See `SimklAuthClient` for details.

Example of Web Application flow token request.
```java
            HttpUrl url = SimklAuthClient.authorizationRequestUrl("<clientId>", 
                    "<redirectUrl>", 
                    "1234");
            
            // let user to sign in using url, then use <code> to get access token
            
            SimklAuthClient authClient = new SimklAuthClient.Builder()
                    .clientCredentials("<clientId>", "<clientSecret>", "<redirectUrl>")
                    .build();
            
            AccessToken token = authClient.accessToken(SimklAuthClient.GrantType.authorization_code, "<code>")
                    .body();

```
### Example

Use like any other retrofit2 based service.  
Optionally you can share OkHttp client and Retrofit instances to keep single request pooling, disk cache, routing logic, etc.

```java
        Simkl simkl = new Simkl.Builder()
                .clientId("<clientId>")
                .tokenProvider(() -> "<token>")
                .build();
        Users usersApi = simkl.users();
        usersApi.info().enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful()) {
                    UserInfo info = response.body();
                    //use info
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                //handle
            }
        });
```

See test cases in `src/test/` for more examples.

### Known API Issues

* [Add to Collection](https://simkl.docs.apiary.io/#reference/sync/add-to-collection/add-items-to-collection) and [Remove from Collection](https://simkl.docs.apiary.io/#reference/sync/remove-from-collection/delete-items-from-collection) methods always response with `null`.

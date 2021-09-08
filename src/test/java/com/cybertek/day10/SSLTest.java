package com.cybertek.day10;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SSLTest {

    @Test
    public void test(){
        /*
        with the code below we cannot pass unsecure websites but there is one way we can do ti
        when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
        //javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException:
        // PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
        // unable to find valid certification path to requested target

         */
        // relaxedHTTPSValidation()--> this method will allow us to pass information insecure websites
        given().relaxedHTTPSValidation().when()
                .get("https://untrusted-root.badssl.com/")
                .prettyPrint();


    }

}

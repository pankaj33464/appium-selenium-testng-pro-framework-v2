package framework.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import framework.core.ConfigManager;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockBootstrap {
    private static WireMockServer server;

    public static void startIfEnabled() {
        if (!ConfigManager.cfg().wiremockEnabled()) return;
        if (server != null && server.isRunning()) return;

        server = new WireMockServer(WireMockConfiguration.options().port(ConfigManager.cfg().wiremockPort()));
        server.start();

        // Example stub
        server.stubFor(get(urlEqualTo("/health"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type","application/json")
                        .withBody("{\"status\":\"UP\"}")));

        // Example POST stub
        server.stubFor(post(urlEqualTo("/login"))
                .withRequestBody(matchingJsonPath("$.username"))
                .withRequestBody(matchingJsonPath("$.password"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type","application/json")
                        .withBody("{\"token\":\"fake-token\"}")));
    }

    public static void stopIfRunning() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }
}

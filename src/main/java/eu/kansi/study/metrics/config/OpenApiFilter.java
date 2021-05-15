package eu.kansi.study.metrics.config;

import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.info.Info;
import org.eclipse.microprofile.openapi.models.info.License;
import org.eclipse.microprofile.openapi.models.responses.APIResponse;

import java.util.Collections;

public class OpenApiFilter implements OASFilter {

    @Override
    public APIResponse filterAPIResponse(APIResponse apiResponse) {
        if ("Missing description".equals(apiResponse.getDescription())) {
            apiResponse.setDescription("Invalid hostname or the system service may not "
                    + "be running on the particular host.");
        }
        return apiResponse;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        openAPI.setInfo(
                OASFactory.createObject(Info.class).title("Hello App").version("1.0")
                        .description(
                                "Hello app for metrics demo.")
                        .license(
                                OASFactory.createObject(License.class)
                                        .name("Eclipse Public License - v 1.0").url(
                                        "https://www.eclipse.org/legal/epl-v10.html")));

        openAPI.addServer(
                OASFactory.createServer()
                        .url("http://localhost:{port}")
                        .description("Simple Open Liberty.")
                        .variables(Collections.singletonMap("port",
                                OASFactory.createServerVariable()
                                        .defaultValue("8080")
                                        .description("Server HTTP port."))));
    }

}
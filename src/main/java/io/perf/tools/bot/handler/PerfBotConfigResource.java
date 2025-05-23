package io.perf.tools.bot.handler;

import io.perf.tools.bot.model.ProjectConfig;
import io.perf.tools.bot.service.ConfigService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/config")
public class PerfBotConfigResource {

    @Inject
    ConfigService configService;

    /**
     * Load a new project configuration
     * @param projectConfig project configuration
     */
    @POST
    @ResponseStatus(201)
    @Produces(MediaType.APPLICATION_JSON)
    public void loadProjectConfig(ProjectConfig projectConfig) {
        Log.info("Loading project config for " + projectConfig.repository);
        configService.loadConfig(projectConfig);
    }

    @GET
    @ResponseStatus(200)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectConfig> getConfigs() {
        return new ArrayList<>(configService.getConfigs().values());
    }

}

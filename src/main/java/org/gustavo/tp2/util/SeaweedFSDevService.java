package org.gustavo.tp2.util;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

/**
 * Dev Service para SeaweedFS.
 * Inicia um container SeaweedFS (master + volume) automaticamente ao subir o Quarkus no perfil dev.
 */
@ApplicationScoped
@IfBuildProfile("dev")
public class SeaweedFSDevService {

    private static final Logger LOG = Logger.getLogger(SeaweedFSDevService.class);

    private static final String IMAGE = "chrislusf/seaweedfs:latest";
    private static final int MASTER_CONTAINER_PORT = 9333;
    private static final int VOLUME_CONTAINER_PORT = 8080;
    private static final int MASTER_HOST_PORT = 9333;
    private static final int VOLUME_HOST_PORT = 8088;

    @Inject
    @ConfigProperty(name = "seaweedfs.devservice.enabled", defaultValue = "true")
    boolean enabled;

    private GenericContainer<?> container;

    void onStart(@Observes StartupEvent ev) {
        if (!enabled) {
            LOG.info("[SeaweedFS DevService] desabilitado via seaweedfs.devservice.enabled=false");
            return;
        }

        LOG.info("[SeaweedFS DevService] iniciando container...");
        configureDockerSocketForWindows();

        @SuppressWarnings("resource")
        GenericContainer<?> c = new GenericContainer<>(IMAGE);
        c.withCommand(
                "server",
                "-dir=/data",
                "-master.port=" + MASTER_CONTAINER_PORT,
                "-volume.port=" + VOLUME_CONTAINER_PORT,
                "-volume.publicUrl=localhost:" + VOLUME_HOST_PORT
        );
        c.withExposedPorts(MASTER_CONTAINER_PORT, VOLUME_CONTAINER_PORT);
        c.withStartupTimeout(Duration.ofSeconds(90));
        c.waitingFor(Wait.forHttp("/cluster/status").forPort(MASTER_CONTAINER_PORT).withStartupTimeout(Duration.ofSeconds(90)));
        c.setPortBindings(List.of(
                MASTER_HOST_PORT + ":" + MASTER_CONTAINER_PORT,
                VOLUME_HOST_PORT + ":" + VOLUME_CONTAINER_PORT
        ));
        container = c;

        try {
            container.start();
            LOG.infof("[SeaweedFS DevService] online — master: localhost:%d | volume publicUrl: localhost:%d",
                    MASTER_HOST_PORT, VOLUME_HOST_PORT);
        } catch (Exception e) {
            LOG.warnf("[SeaweedFS DevService] falhou ao iniciar o container: %s", e.getMessage());
            container = null;
        }
    }

    private void configureDockerSocketForWindows() {
        String dockerHost = System.getenv("DOCKER_HOST");
        if (dockerHost != null && !dockerHost.isBlank()) {
            LOG.infof("[SeaweedFS DevService] DOCKER_HOST detectado: %s", dockerHost);
            return;
        }

        String osName = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (osName.contains("windows")) {
            String override = "npipe:////./pipe/dockerDesktopLinuxEngine";
            LOG.infof("[SeaweedFS DevService] usando Docker Desktop NPipe override: %s", override);
            System.setProperty("testcontainers.docker.socket.override", override);
            System.setProperty("TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE", override);
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        if (container != null && container.isRunning()) {
            LOG.info("[SeaweedFS DevService] parando container...");
            container.stop();
        }
    }
}

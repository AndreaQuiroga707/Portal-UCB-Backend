package bo.edu.ucb.backend.api;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/logs")
@CrossOrigin(origins = "http://localhost:3000") // Permitir peticiones desde el frontend
public class LogController {

    private static final String LOGS_DIR = "./"; // Directorio donde están los logs

    @GetMapping("/")
    public List<String> listLogFiles() throws IOException {
        List<String> logFiles = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(LOGS_DIR), "*.log")) {
            for (Path entry : stream) {
                logFiles.add(entry.getFileName().toString());
            }
        }
        return logFiles;
    }

    @GetMapping("/{filename}")
    public Resource getLogFile(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(LOGS_DIR).resolve(filename).normalize();
        if (!Files.exists(filePath)) {
            throw new RuntimeException("Archivo no encontrado: " + filename);
        }
        return new UrlResource(filePath.toUri());
    }
}

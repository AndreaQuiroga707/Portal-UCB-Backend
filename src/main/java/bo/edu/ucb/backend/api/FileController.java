package bo.edu.ucb.backend.api;

import bo.edu.ucb.backend.dto.ResponseDTO;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpHeaders;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
    private static final Logger appLogger = LoggerFactory.getLogger("APP_LOGGER");
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.access-key}")
    private String minioAccessKey;

    @Value("${minio.secret-key}")
    private String minioSecretKey;

    @Value("${minio.bucket}")
    private String minioBucket;

    @PostMapping("/api/v1/file/upload")
    public ResponseEntity<ResponseDTO> uploadFileURL(@RequestPart MultipartFile file) {
        ResponseDTO response = new ResponseDTO();
        try {
            // Configurar el cliente de Minio
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("http://127.0.0.1:9000")
                    .credentials("academico-access-key", "academico-secret-key")
                    .build();

            // Subir el archivo al bucket de Minio
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("academico-bucket")
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // Generar la URL prefirmada con expiración de 7 días y token de seguridad
            String presignedUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET) // Cambiar a Method.GET si estás obteniendo la URL para descargar
                            .bucket("academico-bucket")
                            .object(file.getOriginalFilename())
                            .expiry(7, TimeUnit.DAYS) // Establecer el tiempo de expiración a 7 días
                            .build()
            );

            response.setStatus(200);
            response.setMessage("Archivo subido exitosamente");
            response.setData(presignedUrl);
            appLogger.info("Archivo subido exitosamente: {}", file.getOriginalFilename());
            return ResponseEntity.ok(response);
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            response.setStatus(400);
            response.setMessage("Error al subir el archivo");
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}

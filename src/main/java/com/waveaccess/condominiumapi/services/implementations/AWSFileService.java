package com.waveaccess.condominiumapi.services.implementations;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.waveaccess.condominiumapi.services.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AWSFileService implements FileService {

    @Value("${condominium.storage.bucket.name}")
    private String bucketName;

    private final String STORAGE_PATH = System.getProperty("user.home") + "/Downloads/";
    private final AmazonS3 client;

    @SneakyThrows
    @Transactional
    @Override
    public String save(MultipartFile multipartFile) {

        var fileName = UUID.randomUUID().toString();
        var path = STORAGE_PATH + fileName;
        var targetFile = new File(path);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("originalName", Objects.requireNonNull(multipartFile.getOriginalFilename())
                .replaceAll(" ", ""));
        ObjectMetadata providedMetadata = new ObjectMetadata();
        providedMetadata.setUserMetadata(metadata);

        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), targetFile);

        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, targetFile).withMetadata(providedMetadata);
        client.putObject(request);

        return fileName;
    }

    @SneakyThrows
    @Override
    public Resource download(String fileName) {

        S3Object responseObject = client.getObject(bucketName, fileName);
        String originalFileName = responseObject.getObjectMetadata()
                .getUserMetadata()
                .get("originalName");
        String path = STORAGE_PATH + originalFileName;

        var content = responseObject.getObjectContent();
        File file = new File(path);
        if (file.exists()) {
            file.renameTo(new File(path.replace(path, STORAGE_PATH
                    + "(" + UUID.randomUUID().toString().substring(35)) + ")" + originalFileName));
        }

        FileOutputStream out = new FileOutputStream(file);
        content.transferTo(out);
        out.flush();
        out.close();

        return new FileSystemResource(file);
    }
}

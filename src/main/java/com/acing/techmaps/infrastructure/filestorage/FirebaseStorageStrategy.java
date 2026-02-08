package com.acing.techmaps.infrastructure.filestorage;

import com.acing.techmaps.domain.entities.filestorage.StorageType;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.PostConstruct;
import com.fasterxml.uuid.Generators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Service
public class FirebaseStorageStrategy implements StorageStrategy {

    @Value("${file.firebase.config-path}")
    private String firebaseConfigPath;

    @Value("${file.firebase.bucket-name}")
    private String bucketName;

    private static final Set<String> SUPPORTED_TYPES = Set.of(
        "audio/mpeg", // Standard MP3
        "audio/mp3",  // Common MP3 variant
        "video/mp4",
        "image/jpeg",
        "image/png",
        "image/gif",
        "image/wbep",
        "image/svg+xml"
    );

    @PostConstruct
    public void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setStorageBucket(bucketName)
                        .build();
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

    @Override
    public boolean supports(String mimeType) {
        return SUPPORTED_TYPES.contains(mimeType);
    }
    
    @Override
    public boolean canHandle(String fileName) {
        return !fileName.contains("/");
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();
            UUID id = Generators.timeBasedEpochGenerator().generate();
             String fileName = id.toString() + "-" + file.getOriginalFilename();
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
            
            storage.create(blobInfo, file.getBytes());
            return fileName; 
        } catch (IOException e) {
             throw new RuntimeException("Failed to upload to Firebase", e);
        }
    }

    @Override
    public Resource download(String fileName) {
        Storage storage = StorageClient.getInstance().bucket(bucketName).getStorage();
        Blob blob = storage.get(BlobId.of(bucketName, fileName));
        if (blob == null) {
            throw new RuntimeException("File not found in Firebase: " + fileName);
        }
        return new ByteArrayResource(blob.getContent());
    }
    
    @Override
    public StorageType getStorageType() {
        return StorageType.FIREBASE;
    }
}

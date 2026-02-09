package com.acing.techmaps.web.controller;

import com.acing.techmaps.usecases.filestorage.FileStorageCRUD;
import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.domain.entities.filestorage.StorageType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URLConnection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.acing.techmaps.domain.entities.user.User;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File Storage", description = "Endpoints for file upload and download")
public class FileController {

    private final FileStorageCRUD fileStorageCRUD;

    public FileController(FileStorageCRUD fileStorageCRUD) {
        this.fileStorageCRUD = fileStorageCRUD;
    }

    @PreAuthorize("hasRole('EDUCATIONAL')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Uploads a file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); 
        String fileName = fileStorageCRUD.uploadFile(file, user.getId());
        return ResponseEntity.ok(fileName);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(params = "path")
    @Operation(summary = "Downloads a file")
    public ResponseEntity<Resource> downloadFile(@RequestParam("path") String path) {
        Resource resource = fileStorageCRUD.downloadFile(path);
        
        String contentType = URLConnection.guessContentTypeFromName(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path + "\"")
                .body(resource);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    @Operation(summary = "Get all files metadata")
    public ResponseEntity<List<FileMetadata>> getFiles() {
        
        List<FileMetadata> files = fileStorageCRUD.getAllFiles();

        return ResponseEntity.ok(files);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{id}")
    @Operation(summary = "Get file metadata by ID")
    public ResponseEntity<FileMetadata> getFileById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileStorageCRUD.getFileById(id));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/originalName")
    @Operation(summary = "Get files by original name")
    public ResponseEntity<List<FileMetadata>> getFilesByOriginalName(@RequestParam String originalName) {
        return ResponseEntity.ok(fileStorageCRUD.getFilesByOriginalName(originalName));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/creatorId/{creatorId}")
    @Operation(summary = "Get files by creator ID")
    public ResponseEntity<List<FileMetadata>> getFilesByCreatorId(@PathVariable UUID creatorId) {
        return ResponseEntity.ok(fileStorageCRUD.getFilesByCreatorId(creatorId));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/storageType/{storageType}")
    @Operation(summary = "Get files by storage type")
    public ResponseEntity<List<FileMetadata>> getFilesByStorageType(@PathVariable StorageType storageType) {
        return ResponseEntity.ok(fileStorageCRUD.getFilesByStorageType(storageType));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete file by ID")
    public ResponseEntity<Void> deleteFile(@PathVariable UUID id) {
        fileStorageCRUD.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}

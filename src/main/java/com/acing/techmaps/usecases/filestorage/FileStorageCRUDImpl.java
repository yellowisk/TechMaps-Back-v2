package com.acing.techmaps.usecases.filestorage;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.domain.entities.filestorage.StorageType;
import com.acing.techmaps.usecases.filestorage.gateway.FileMetadataDAO;
import com.acing.techmaps.usecases.filestorage.gateway.FileStorageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FileStorageCRUDImpl implements FileStorageCRUD {
    
    private final FileStorageGateway fileStorageGateway;
    private final FileMetadataDAO fileMetadataDAO;

    @Autowired
    public FileStorageCRUDImpl(FileStorageGateway fileStorageGateway, FileMetadataDAO fileMetadataDAO) {
        this.fileStorageGateway = fileStorageGateway;
        this.fileMetadataDAO = fileMetadataDAO;
    }

    @Override
    public List<FileMetadata> getAllFiles() {
        return fileMetadataDAO.findAllOrderedByCreatedAt();
    }

        @Override
    public FileMetadata getFileById(UUID id) {
        return fileMetadataDAO.findById(id);
    }

    @Override
    public List<FileMetadata> getFilesByOriginalName(String originalName) {
        return fileMetadataDAO.findByOriginalFileName(originalName);
    }

    @Override
    public List<FileMetadata> getFilesByCreatorId(UUID creatorId) {
        return fileMetadataDAO.findByCreatorId(creatorId);
    }

    @Override
    public List<FileMetadata> getFilesByStorageType(StorageType storageType) {
        return fileMetadataDAO.findByStorageType(storageType);
    }

    @Override
    public String uploadFile(MultipartFile file, UUID creatorId) {
        return fileStorageGateway.upload(file, creatorId);
    }

    @Override
    public Resource downloadFile(String fileName) {
        return fileStorageGateway.download(fileName);
    }

    @Override
    public void deleteFile(UUID id) {
        fileStorageGateway.delete(id);
    }
}

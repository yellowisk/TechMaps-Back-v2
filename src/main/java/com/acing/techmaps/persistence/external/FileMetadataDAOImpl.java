package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.filestorage.FileMetadata;
import com.acing.techmaps.domain.entities.filestorage.StorageType;
import com.acing.techmaps.usecases.filestorage.gateway.FileMetadataDAO;
import com.acing.techmaps.web.exception.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class FileMetadataDAOImpl implements FileMetadataDAO {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.file-metadata-dao.insert.file-metadata}")
    private String insertFileMetadataQuery;

    @Value("${queries.sql.file-metadata-dao.select.file-metadata-by-id}")
    private String selectFileMetadataByIdQuery;

    @Value("${queries.sql.file-metadata-dao.select.file-metadata-by-path}")
    private String selectFileMetadataByPathQuery;

    public FileMetadataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FileMetadata save(FileMetadata fileMetadata) {
        jdbcTemplate.update(insertFileMetadataQuery,
                fileMetadata.getId(),
                fileMetadata.getCreatorId(),
                fileMetadata.getFileName(),
                fileMetadata.getOriginalFileName(),
                fileMetadata.getContentType(),
                fileMetadata.getSize(),
                fileMetadata.getStorageType().name(),
                fileMetadata.getPath(),
                fileMetadata.getCreatedAt()
        );
        return fileMetadata;
    }

    @Override
    public FileMetadata findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectFileMetadataByIdQuery, this::mapperFileMetadataFromRs, id);
        } catch (EmptyResultDataAccessException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find file metadata with id: " + id);
        }
    }

    @Override
    public FileMetadata findByPath(String path) {
        try {
            return jdbcTemplate.queryForObject(selectFileMetadataByPathQuery, this::mapperFileMetadataFromRs, path);
        } catch (EmptyResultDataAccessException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find file metadata with path: " + path);
        }
    }

    private FileMetadata mapperFileMetadataFromRs(ResultSet rs, int rowNum) throws SQLException {
        return new FileMetadata(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("creator_id"),
                rs.getString("file_name"),
                rs.getString("original_file_name"),
                rs.getString("content_type"),
                rs.getLong("size"),
                StorageType.valueOf(rs.getString("storage_type")),
                rs.getString("path"),
                rs.getTimestamp("created_at")
        );
    }
}

package org.tierlistapp.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tierlistapp.dto.StoredFileDTO;
import org.tierlistapp.dto.StoredFileWithBytesDTO;
import org.tierlistapp.enums.TierEntityType;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class FileStorageService {
    private static final String MAP_IMAGE_PREFIX = "images/map";
    private static final String ROW_IMAGE_PREFIX = "images/row";
    private static final String ITEM_IMAGE_PREFIX = "images/item";

    private final S3StorageService s3StorageService;
    private final StoredFileService storedFileService;

    public FileStorageService(S3StorageService s3StorageService, StoredFileService storedFileService) {
        this.s3StorageService = s3StorageService;
        this.storedFileService = storedFileService;
    }

    public StoredFileDTO saveTierEntityFile(String ownerId, TierEntityType tierEntityType, MultipartFile file) throws IOException {
        String fileHash = sha256Hex(file.getBytes());
        String fileExt = getFileExt(file.getOriginalFilename());

        String objectKey = switch (tierEntityType) {
            case MAP -> ownerId + "/" + MAP_IMAGE_PREFIX + "/" + fileHash + fileExt;
            case ROW -> ownerId + "/" + ROW_IMAGE_PREFIX + "/" + fileHash + fileExt;
            case ITEM -> ownerId + "/" + ITEM_IMAGE_PREFIX + "/" + fileHash + fileExt;
        };
        s3StorageService.uploadFileBytes(objectKey, file.getBytes());

        StoredFileDTO storedFileDTO = storedFileService.create(ownerId, file.getOriginalFilename(), objectKey);
        return storedFileDTO;
    }

    public StoredFileWithBytesDTO getTierEntityFile(String fileId) {
        StoredFileDTO storedFileDTO = storedFileService.getById(fileId);
        byte[] fileBytes = s3StorageService.downloadFileBytes(storedFileDTO.objectKey());
        return new StoredFileWithBytesDTO(storedFileDTO, fileBytes);
    }

    public StoredFileWithBytesDTO getTierEntityFileByIdAndOwnerId(String fileId, String ownerId) {
        StoredFileDTO storedFileDTO = storedFileService.getByIdAndOwnerId(fileId, ownerId);
        byte[] fileBytes = s3StorageService.downloadFileBytes(storedFileDTO.objectKey());
        return new StoredFileWithBytesDTO(storedFileDTO, fileBytes);
    }

    private static String sha256Hex(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data);
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getFileExt(String fileName) {
        String ext = StringUtils.getFilenameExtension(fileName);
        return (ext == null) ? "" : "." + ext.toLowerCase();
    }
}

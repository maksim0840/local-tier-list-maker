package org.tierlistapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stored_files")
public class StoredFile {
    @Id
    private String id;
    private String ownerId;
    private String uploadedFileName;
    private String objectKey;

    @CreatedDate
    private Instant createdAt;

    public StoredFile(String ownerId, String uploadedFileName, String objectKey) {
        this.ownerId = ownerId;
        this.uploadedFileName = uploadedFileName;
        this.objectKey = objectKey;
    }
}

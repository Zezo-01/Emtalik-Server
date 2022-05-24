package org.emtalik.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Entity
@Table(name = "profile_picture")
public class ProfilePicture {
    @Id
    private Integer id;
    @Column(name = "content_type" , length = 25)
    private String contentType;
    @Lob
    @Column(name = "content")
    private byte[] content;

    ProfilePicture(MultipartFile file) throws IOException {
        this.content = file.getBytes();
        this.contentType = file.getContentType();
    }
}

package org.emtalik.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "profile_pictures")
@NoArgsConstructor
public class ProfilePicture {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "content_type", length = 25)
    private String contentType;
    @Lob
    @Column(name = "content")
    private byte[] content;

    public ProfilePicture(MultipartFile file) throws IOException {
        this.content = file.getBytes();
        this.contentType = file.getContentType();
    }



}

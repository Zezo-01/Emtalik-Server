package org.emtalik.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
    @SequenceGenerator
	(name = "profile_picture_sequence",
	sequenceName = "profile_picture_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "profile_picture_sequence")
    private int id;
    @Column(name = "content_type", length = 25)
    private String contentType;
    @Lob
    @Column(name = "content")
    private byte[] content;

    @OneToOne(mappedBy = "picture")
    private User user;

    public ProfilePicture(MultipartFile file) throws IOException {
        this.content = file.getBytes();
        this.contentType = file.getContentType();
    }

    

}

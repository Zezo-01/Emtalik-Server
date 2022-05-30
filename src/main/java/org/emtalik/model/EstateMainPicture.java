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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estate_main_pictures")
public class EstateMainPicture 
{
    @Id
    @SequenceGenerator
	(name = "estate_picture_sequence",
	sequenceName = "estate_picture_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "estate_picture_sequence")
    private int id;
    @Column
    @Lob
    private byte[] content;
    @Column(name = "content_type", length = 25)
    private String contentType;
    @OneToOne(mappedBy = "mainPicture")
    private Estate estate;

    public EstateMainPicture(MultipartFile picture) throws IOException {
        this.content = picture.getBytes();
        this.contentType = picture.getContentType();
    }
}

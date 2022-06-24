package org.emtalik.model;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estate_media")
@Builder
public class EstateMedia {
    @Id
    @SequenceGenerator
	(name = "estate_media_sequence",
	sequenceName = "estate_media_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "estate_media_sequence")
    private int id;
    @Column
    @Lob
    private byte[] content;
    @Column(name = "content_type", length = 25)
    private String contentType;
    @ManyToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        optional = false
    )
    @JoinColumn(
        name = "estate_id",
        referencedColumnName = "id"
    )
    private Estate estate;

    public EstateMedia(MultipartFile picture) throws IOException {
        this.content = picture.getBytes();
        this.contentType = picture.getContentType();
    }
}

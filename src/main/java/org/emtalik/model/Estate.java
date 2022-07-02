package org.emtalik.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estate {
    @Id
    @SequenceGenerator
	(name = "estate_sequence",
	sequenceName = "estate_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "estate_sequence")
    private int id;
   
    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = false
    )
    @JoinColumn(
        name = "owner_id",
        referencedColumnName = "id"
    )
    private User owner;
    
    @Column(length = 35)
    private String name;
    @Column(length = 45)
    private String address;
    @Column(columnDefinition = "enum(\"nablus\",\"jenin\",\"ramallah\",\"jerusalem\",\"jericho\",\"bethleem\",\"hebrone\",\"selfeet\",\"tubas\",\"qalqilya\",\"tulkarem\")")
    @Enumerated(EnumType.STRING)
    private Province province;
    @Column(length = 9)
    private String type;
    @Column(length = 150)
    private String description;
    @Column(length = 3)
    private Double size;
    @Column(name = "made_on", insertable = false , updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
	private Timestamp madeOn;
    private boolean approved;
    @OneToOne(
        cascade = CascadeType.ALL,
        optional = false,
        fetch = FetchType.LAZY

    )
    @JoinColumn(
        referencedColumnName = "picture_id",
        name = "id"
    )
    private EstateMainPicture mainPicture;
    @OneToMany(
        mappedBy = "estate",

        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<EstateMedia> media;

    @OneToMany(mappedBy = "estate",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Offer> offers;

    public void setMainPicture(EstateMainPicture picture){
        this.mainPicture = picture;
    }

    public void setMainPictureWithFile(MultipartFile picture) throws IOException{
        this.mainPicture = new EstateMainPicture(picture);
    }
    public void setMedia(List<EstateMedia> media){
        this.media = media;
    }
   

}

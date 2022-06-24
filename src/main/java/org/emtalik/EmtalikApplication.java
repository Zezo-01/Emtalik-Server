package org.emtalik;


import org.emtalik.Repositroy.*;
import org.emtalik.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class EmtalikApplication  {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context =
		 	SpringApplication.run(EmtalikApplication.class, args);

		UsersRepo userRepo = context.getBean(UsersRepo.class);
		OfferRepo offerRepo = context.getBean(OfferRepo.class);
		ApartmentRepo apartmentRepo = context.getBean(ApartmentRepo.class);
		HouseRepo houseRepo = context.getBean(HouseRepo.class);
		LandRepo landRepo = context.getBean(LandRepo.class);
		StoreRepo storeRepo = context.getBean(StoreRepo.class);
		ParkingRepo parkingRepo = context.getBean(ParkingRepo.class);

		userRepo.deleteAll();
		offerRepo.deleteAll();
		apartmentRepo.deleteAll();
		houseRepo.deleteAll();
		landRepo.deleteAll();
		storeRepo.deleteAll();
		storeRepo.deleteAll();
		parkingRepo.deleteAll();

		User yazeed = User.builder()
				.username("Zezo")
				.email("j.demogharbe@gmail.com")
				.contactNumber("972569119769")
				.role(Role.admin)
				.interests("parking,house")
				.firstName("Yazeed")
				.fathersName("Osama")
				.grandfathersName("Muhammad")
				.surName("Mograby")
				.password("Zezo12345")
				.picture(
						    ProfilePicture.builder()
							.contentType("image/jpeg")
							.content(new FileInputStream(new File("src/main/resources/pfp/handsome.jpeg")).readAllBytes())
							.build()
						)
				.build();


		User fadi = User.builder()
				.username("أبو شلش")
				.email("fadishalash@gmail.com")
				.contactNumber("972509119769")
				.role(Role.admin)
				.interests("apartment,store")
				.firstName("Fadi")
				.fathersName("Khalel")
				.grandfathersName("Muhammad")
				.surName("Shalash")
				.password("Fadi12345")
				.picture(
							ProfilePicture.builder()
							.contentType("image/jpeg")
							.content(new FileInputStream(new File("src/main/resources/pfp/fadi.jpg")).readAllBytes())
							.build()
						)
				.build();


		User anas = User.builder()
				.username("أنس")
				.email("anasmanasrah@gmail.com")
				.contactNumber("972599119769")
				.role(Role.admin)
				.password("Anas12345")
				.build();


		User basel = User.builder()
				.username("أبو أنس")
				.email("aboaljameel@dawleh.com")
				.contactNumber("970594323005")
				.role(Role.seller)
				.interests("land")
				.firstName("Basel")
				.fathersName("Jammel")
				.grandfathersName("Abed AlRahman")
				.surName("Wardan")
				.password("Basel12345")
				.picture(
						ProfilePicture.builder()
								.contentType("image/jpeg")
								.content(new FileInputStream(new File("src/main/resources/pfp/basel.jpg")).readAllBytes())
								.build()
				)
				.build();


		User qusai = User.builder()
				.username("Qusai")
				.email("qusaighazi@dawleh.com")
				.contactNumber("972595162772")
				.role(Role.buyer)
				.interests("land,parking")
				.password("Qusai12345")
				.picture(
						ProfilePicture.builder()
								.contentType("image/jpeg")
								.content(new FileInputStream(new File("src/main/resources/pfp/qusai.jpg")).readAllBytes())
								.build()
				)
				.build();


		userRepo.save(qusai);
		userRepo.save(anas);
		userRepo.save(fadi);
		userRepo.save(yazeed);

		Parking sufyanStreetParking = Parking.builder()
				.carsAllowed("automobile,bike")
				.vehicleCapacity(2)
				.build();
		sufyanStreetParking.setName("مصف سيارات شارع سفيان");
		sufyanStreetParking.setAddress("شارع سفيان");
		sufyanStreetParking.setProvince(Province.nablus);
		sufyanStreetParking.setApproved(true);

		sufyanStreetParking.setSize(40.0);
		sufyanStreetParking.setType("parking");

		sufyanStreetParking.setDescription("شارع مزدحم بلزمو محل تصف فيه");

		sufyanStreetParking.setOwner(basel);

		sufyanStreetParking.setMainPicture(
				EstateMainPicture.builder()
						.contentType("image/jpeg")
						.content(new FileInputStream(new File("src/main/resources/parking/sufyan.jpg")).readAllBytes())
						.build()
		);

		sufyanStreetParking.setMedia(List.of(
			 EstateMedia.builder()
					.contentType("image/jpeg")
					.content(new FileInputStream(new File("src/main/resources/parking/sufyan-1.jpg")).readAllBytes())
					 .estate(sufyanStreetParking)
					.build(),
			EstateMedia.builder()
					.contentType("image/jpeg")
					.content(new FileInputStream(new File("src/main/resources/parking/sufyan-2.jpg")).readAllBytes())
					.estate(sufyanStreetParking)
					.build(),
			EstateMedia.builder()
					.contentType("video/mp4")
					.content(new FileInputStream(new File("src/main/resources/parking/sufyan-3.mp4")).readAllBytes())
					.estate(sufyanStreetParking)
					.build()
		));



		parkingRepo.save(sufyanStreetParking);







	}

}

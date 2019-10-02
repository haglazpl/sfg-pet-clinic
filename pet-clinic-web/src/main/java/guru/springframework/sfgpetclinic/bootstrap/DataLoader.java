package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }



    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {

            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Pies");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Kot");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Dominik");
        owner1.setLastName("Maj");
        owner1.setAddress("Chabrowa 28b");
        owner1.setCity("Rybnik");
        owner1.setTelephone("661 047 407");

        Pet dominikPet = new Pet();
        dominikPet.setPetType(savedDogPetType);
        dominikPet.setOwner(owner1);
        dominikPet.setBirthDate(LocalDate.now());
        dominikPet.setName("Puszek");
        owner1.getPets().add(dominikPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Adam");
        owner2.setLastName("Wnuk");
        owner2.setAddress("Chwalowice");
        owner2.setCity("Rybnik");
        owner2.setTelephone("500 120 123");

        Pet kotAdama = new Pet();
        kotAdama.setPetType(savedCatPetType);
        kotAdama.setOwner(owner2);
        kotAdama.setBirthDate(LocalDate.now());
        kotAdama.setName("Kot Adama");
        owner2.getPets().add(kotAdama);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(kotAdama);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Billy");
        vet1.setLastName("Herrington");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Van");
        vet2.setLastName("Darkholme");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.print("Loaded Vets...\n");
    }
}

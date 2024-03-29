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
        owner1.setAddress("Rybnicka");
        owner1.setCity("Rybnik");
        owner1.setTelephone("321 321 321");

        Pet dominikPet = new Pet();
        dominikPet.setPetType(savedDogPetType);
        dominikPet.setOwner(owner1);
        dominikPet.setBirthDate(LocalDate.now());
        dominikPet.setName("Puszek");
        owner1.getPets().add(dominikPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michał");
        owner2.setLastName("Szpak");
        owner2.setAddress("Boguszowice");
        owner2.setCity("Rybnik");
        owner2.setTelephone("123 123 123");

        Pet kot1 = new Pet();
        kot1.setPetType(savedCatPetType);
        kot1.setOwner(owner2);
        kot1.setBirthDate(LocalDate.now());
        kot1.setName("Typowy kot");
        owner2.getPets().add(kot1);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(kot1);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Vet1");
        vet1.setLastName("Vet1surname");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vet2");
        vet2.setLastName("Vet2surname");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.print("Loaded Vets...\n");
    }
}

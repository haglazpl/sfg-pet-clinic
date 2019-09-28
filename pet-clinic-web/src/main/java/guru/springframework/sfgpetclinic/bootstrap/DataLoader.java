package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Pies");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Kot");
        PetType savedCatPetType = petTypeService.save(cat);


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

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Billy");
        vet1.setLastName("Herrington");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Van");
        vet2.setLastName("Darkholme");

        vetService.save(vet2);

        System.out.print("Loaded Vets...\n");
    }
}

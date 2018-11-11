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
        dog.setName("Dog");

        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jordan");
        owner1.setAddress("1 Main Street");
        owner1.setCity("Miami");
        owner1.setTelephone("4152368974");

        Pet michaelsPet = new Pet();
        michaelsPet.setPetType(savedDogType);
        michaelsPet.setOwner(owner1);
        michaelsPet.setBirthDate(LocalDate.now());
        michaelsPet.setName("Rosco");
        owner1.getPets().add(michaelsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Lionel");
        owner2.setLastName("Messi");
        owner2.setAddress("1 Main Street");
        owner2.setCity("Miami");
        owner2.setTelephone("4152368974");

        Pet lionelsCat = new Pet();
        lionelsCat.setPetType(savedCatType);
        lionelsCat.setOwner(owner2);
        lionelsCat.setBirthDate(LocalDate.now());
        lionelsCat.setName("Garfield");
        owner2.getPets().add(lionelsCat);

        ownerService.save(owner2);

        System.out.println("Loaded owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Al");
        vet1.setLastName("Gore");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Karl");
        vet2.setLastName("Marx");

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}

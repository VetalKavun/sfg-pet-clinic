package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import guru.springframework.sfgpetclinic.service.SpecialtiesService;
import guru.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtiesService specialtiesService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtiesService specialtiesService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtiesService = specialtiesService;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();
    }

    private void loadData() {
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtiesService.save(radiology);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtiesService.save(dentistry);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtiesService.save(surgery);

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Machael");
        owner1.setLastName("Weston");
        owner1.setAddress("address");
        owner1.setCity("City");
        owner1.setTelephone("telephone");

        Pet mikesPet = new Pet();
        mikesPet.setOwner(owner1);
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setBirthDay(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glennane");
        owner2.setAddress("address");
        owner2.setCity("City");
        owner2.setTelephone("telephone");
        Pet fionasCat = new Pet();
        fionasCat.setName("Kitty");
        fionasCat.setBirthDay(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);
        owner2.getPets().add(fionasCat);
        ownerService.save(owner2);

        System.out.println("Loaded owners.......");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedDentistry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tom");
        vet2.setLastName("Rod");
        vet2.getSpecialties().add(savedRadiology);

        vetService.save(vet2);

        System.out.println("Loaded vets.......");
    }
}

package com.raf.userservice.mapper;


import com.raf.userservice.domain.Client;
import com.raf.userservice.domain.Manager;
import com.raf.userservice.domain.User;
import com.raf.userservice.dto.*;
import com.raf.userservice.repository.RoleRepository;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    private RoleRepository roleRepository;
    private static int brojClanskeKarte = 1;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto userToUserDto(User user){

        UserDto userDto = new UserDto();
        userDto.setAktivan(user.isAktivan());
        userDto.setZabranjen(user.isZabranjen());
        userDto.setEmail(user.getEmail());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        return userDto;
    }

    public ClientDto userToClientDto(Client user) {
        ClientDto userDto = new ClientDto();
        userDto.setNazivFiskulturneSale(user.getNazivFiskulturneSale());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }

    public User clientCreateDtoToClientUser(ClientCreateDto userCreateDto) {
        Client user = new Client();
        user.setNazivFiskulturneSale(userCreateDto.getNazivFiskulturneSale());
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setDateOfBirth(userCreateDto.getDateOfBirth());
        user.setBrojClanskeKarte(brojClanskeKarte++);
        user.setRole(roleRepository.findRoleByName("ROLE_CLIENT").get());
        return user;
    }

    public User clientUpdateDtoToUser(UserUpdateDto userUpdateDto, User user){

        user.setEmail(userUpdateDto.getEmail());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setUsername(userUpdateDto.getUsername());
        return user;
    }

    public OmoguciPristupUseruDtoOut userToOmoguciPristupKorisnikuOut(User u1){

        OmoguciPristupUseruDtoOut user = new OmoguciPristupUseruDtoOut();
        user.setUsername(u1.getUsername());
        user.setZabranjen(u1.isZabranjen());
        return user;
    }

    public PromenaLoznikeDtoOut userToPromeniLozinkuDtoOut(User u1){

        PromenaLoznikeDtoOut user = new PromenaLoznikeDtoOut();
        user.setUsername(u1.getUsername());
        user.setPassword(u1.getPassword());
        return user;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto){

        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setNazivFiskulturneSale(managerCreateDto.getNazivFiskulturneSale());
        manager.setDatumZaposljavanja(managerCreateDto.getDatumZaposljavanja());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        return manager;
    }

    public ManagerDto managerToManagerDto(Manager manager){

        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setNazivFiskulturneSale(manager.getNazivFiskulturneSale());
        managerDto.setDatumZaposljavanja(manager.getDatumZaposljavanja());
        return managerDto;
    }

    public ZajednickiDto userToZajednickiDto(User user){

        ZajednickiDto zajednickiDto = new ZajednickiDto();
        zajednickiDto.setId(user.getId());
        zajednickiDto.setEmail(user.getEmail());
        zajednickiDto.setFirstName(user.getFirstName());
        zajednickiDto.setLastName(user.getLastName());
        zajednickiDto.setUsername(user.getUsername());
        zajednickiDto.setDateOfBirth(user.getDateOfBirth());
        zajednickiDto.setRole(user.getRole().getName());
        zajednickiDto.setAktivan(user.isAktivan());
        zajednickiDto.setZabranjen(user.isZabranjen());

        if(zajednickiDto.getRole().equals("ROLE_CLIENT")){

            Client client = (Client) user;
            zajednickiDto.setBrojClanskeKarte(client.getBrojClanskeKarte());
            zajednickiDto.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga());
            zajednickiDto.setNazivFiskulturneSale(client.getNazivFiskulturneSale());
        }
        else if(zajednickiDto.getRole().equals("ROLE_MANAGER")){

            Manager manager = (Manager) user;
            zajednickiDto.setNazivFiskulturneSale(manager.getNazivFiskulturneSale());
            zajednickiDto.setDatumZaposljavanja(manager.getDatumZaposljavanja());
        }

        return zajednickiDto;
    }
}

package com.raf.userservice.service.implementation;

import com.raf.userservice.domain.Client;
import com.raf.userservice.domain.Manager;
import com.raf.userservice.domain.User;
import com.raf.userservice.dto.*;
import com.raf.userservice.exception.NotFoundException;
import com.raf.userservice.listener.MessageHelper;
import com.raf.userservice.mapper.UserMapper;
import com.raf.userservice.notifikacije.ChangePasswordNotification;
import com.raf.userservice.notifikacije.RegisterNotification;
import com.raf.userservice.repository.RoleRepository;
import com.raf.userservice.repository.UserRepository;
import com.raf.userservice.security.TokenService;
import com.raf.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private TokenService tokenService;
    private RoleRepository roleRepository;
    private String promeniLozinkuDestination;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String registrujDestination;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, TokenService tokenService, RoleRepository roleRepository,
                           @Value("${destination.changepassword}") String promeniLozinkuDestination,JmsTemplate jmsTemplate,MessageHelper messageHelper,
                           @Value("${destination.register}") String registrujDestination) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.promeniLozinkuDestination = promeniLozinkuDestination;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.registrujDestination = registrujDestination;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public ZajednickiDto pronadjiKorisnika(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        return userMapper.userToZajednickiDto(user);
    }

    @Override
    public ClientDto addClient(ClientCreateDto userCreateDto) {
        Client user = (Client) userMapper.clientCreateDtoToClientUser(userCreateDto);
        userRepository.save(user);

        RegisterNotification registerNotification = new RegisterNotification();
        registerNotification.setEmail(user.getEmail());
        registerNotification.setName(user.getFirstName());
        registerNotification.setSurname(user.getLastName());
        registerNotification.setReceiverId(user.getId());
        registerNotification.setLink("localhost:8080/api/user/" + user.getStringZaAktivaciju() + "/activate");

        jmsTemplate.convertAndSend(registrujDestination, messageHelper.createTextMessage(registerNotification));


        return userMapper.userToClientDto(user);
    }

    @Override
    public ManagerDto addManager(ManagerCreateDto managerCreateDto) {
        Manager user = userMapper.managerCreateDtoToManager(managerCreateDto);
        userRepository.save(user);

        RegisterNotification registerNotification = new RegisterNotification();
        registerNotification.setEmail(user.getEmail());
        registerNotification.setName(user.getFirstName());
        registerNotification.setSurname(user.getLastName());
        registerNotification.setReceiverId(user.getId());
        registerNotification.setLink("localhost:8080/api/user/" + user.getStringZaAktivaciju() + "/activate");

        jmsTemplate.convertAndSend(registrujDestination, messageHelper.createTextMessage(registerNotification));

        return userMapper.managerToManagerDto(user);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto) {

        User user = userRepository.findUserByUsername(userUpdateDto.getOldUserName()).orElseThrow(() -> new NotFoundException(String.format("User with username: %s not found.", userUpdateDto.getOldUserName())));
        user = userMapper.clientUpdateDtoToUser(userUpdateDto,user);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public OmoguciPristupUseruDtoOut omoguciPristup(OmogociPristupUseruDtoIn omogociPristupUseruDtoIn) {

        String username = omogociPristupUseruDtoIn.getUsername();
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException(String.format("User with username: %s not found.", username)));
        if(user.isZabranjen())
            user.setZabranjen(false);
        else
            user.setZabranjen(true);
        userRepository.save(user);
        return userMapper.userToOmoguciPristupKorisnikuOut(user);

    }

    @Override
    public PromenaLoznikeDtoOut promeniLozinku(PromenaLoznikeDtoIn promenaLoznikeDtoIn) {

        User user = userRepository.findUserByUsernameAndPassword(promenaLoznikeDtoIn.getUsername(), promenaLoznikeDtoIn.getOldPassword()).orElseThrow(() -> new NotFoundException(String.format("User with username: %s and password: %s not found.", promenaLoznikeDtoIn.getUsername(),promenaLoznikeDtoIn.getOldPassword())));
        user.setPassword(promenaLoznikeDtoIn.getNewPassword());
        userRepository.save(user);

        ChangePasswordNotification changePasswordNotification = new ChangePasswordNotification();
        changePasswordNotification.setEmail(user.getEmail());
        changePasswordNotification.setUsername(user.getUsername());
        changePasswordNotification.setReceiverId(user.getId());

        jmsTemplate.convertAndSend(promeniLozinkuDestination, messageHelper.createTextMessage(changePasswordNotification));


        return userMapper.userToPromeniLozinkuDtoOut(user);
    }

    @Override
    public BrojZakazanihTreningaDto findBrojZakazanihTreninga(Long id) {

        Client user = (Client) userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        BrojZakazanihTreningaDto brojZakazanihTreningaDto = new BrojZakazanihTreningaDto();
        brojZakazanihTreningaDto.setBrojZakazanihTreninga(user.getBrojZakazanihTreninga());
        return brojZakazanihTreningaDto;
    }

    @Override
    public TipUseraDto findTipUsera(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        TipUseraDto tipUseraDto = new TipUseraDto();

        if(user.getRole().getName().equals("ROLE_CLIENT")){
            tipUseraDto.setTipUsera("ROLE_CLIENT");
        }
        else if(user.getRole().getName().equals("ROLE_MANAGER"))
        tipUseraDto.setTipUsera("ROLE_MANAGER");
        return tipUseraDto;
    }


    @Override
    public void povecajBrojZakazanihTreninga(IdUseraNapraviRezDto povecajBrojZakazanihTreningaDto) {

        Client client = (Client) userRepository.findById(povecajBrojZakazanihTreningaDto.getUserId()).orElseThrow(() -> new NotFoundException(String.format("User with id: %s not found.", povecajBrojZakazanihTreningaDto.getUserId())));
        client.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga() + 1);
        userRepository.save(client);
    }

    @Override
    public void smanjiBrojZakazanihTreninga(IdUseraOtkaziRezDto idUseraOtkazi) {

        Client client = (Client) userRepository.findById(idUseraOtkazi.getUserId()).orElseThrow(() -> new NotFoundException(String.format("User with id: %s not found.", idUseraOtkazi.getUserId())));
        client.setBrojZakazanihTreninga(client.getBrojZakazanihTreninga() - 1);
        userRepository.save(client);
    }

    @Override
    public Void aktivirajNalog(String aktivacijaString) {

        User user =  userRepository.findUserByStringZaAktivaciju(aktivacijaString).orElseThrow(() -> new NotFoundException(String.format("User with string: %s not found.", aktivacijaString)));
        user.setAktivan(true);
        return null;
    }


}

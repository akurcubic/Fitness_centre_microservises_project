package com.raf.userservice.runner;

import com.raf.userservice.domain.Admin;
import com.raf.userservice.domain.Role;
import com.raf.userservice.repository.RoleRepository;
import com.raf.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class DataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public DataRunner(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Role roleClient = new Role("ROLE_CLIENT", "Client role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager = new Role("ROLE_MANAGER", "Manager role");

        roleRepository.save(roleClient);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);

        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAktivan(true);
        admin.setRole(roleAdmin);

        userRepository.save(admin);
    }
}

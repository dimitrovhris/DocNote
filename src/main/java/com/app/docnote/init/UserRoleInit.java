package com.app.docnote.init;

import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserRoleInit implements CommandLineRunner {

    private UserRoleRepository userRoleRepository;

    public UserRoleInit(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long hasConditions = userRoleRepository.count();
        if (hasConditions == 0) {
            List<UserRole> userRoles = new ArrayList<>();
            Arrays.stream(UserRoleEnum.values())
                    .forEach(role -> {
                        userRoles.add(new UserRole(role));
                    });
            userRoleRepository.saveAll(userRoles);
        }

    }
}

package com.example.world_of_tanks.configService;

import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.UserRoleEntity;
import com.example.world_of_tanks.models.enums.UserRoleEnum;
import com.example.world_of_tanks.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorldOfTanksDetailsServiceTest {

    // ТРЯБВА ДА НАУЧА МОКОВЕТЕ КАК ДА СЕ ДЪРЖАТ

    @Mock
    private UserRepository mockUserRepo; // ТОВА Е КУХО РЕПОЗИТОРИ, ВСИЧКИТЕ МУ МЕТОДИ ВРЪЩАТ NULL ИЛИ OPTIONAL<NULL>

    private WorldOfTanksDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new WorldOfTanksDetailsService(
                mockUserRepo
        );
    }

    @Test
    void testLoadUserByUsername_UserExists() {

        // ARRANGE

        UserEntity testUserEntity =
                new UserEntity().
                        setUsername("tedkou")
                        .setPassword("123")
                        .setFullName("teodor")
                        .setEmail("teodor@02")
                        .setRoles(List.of(new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN)));

        when(mockUserRepo.findByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

        // ACT
        UserDetails userDetails =
                toTest.loadUserByUsername(testUserEntity.getUsername());


        // ASSERT
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername()); // ПЪРВО Е EXPECTED, ПОСЛЕ Е ACTUAL


        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(1, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(), authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        // ARRANGE
        // NOTE: No need to arrange anything, mocks return empty optionals!

        //act && assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> toTest.loadUserByUsername("penka"));
    }
}




















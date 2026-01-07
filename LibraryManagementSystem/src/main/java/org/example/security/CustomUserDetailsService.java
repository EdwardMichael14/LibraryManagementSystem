package org.example.security;

import lombok.RequiredArgsConstructor;
import org.example.data.models.Admin;
import org.example.data.models.User;
import org.example.data.repositories.AdminRepository;
import org.example.data.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Try Admin first
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return new UserPrincipal(admin);
        }

        // Try User (customer)
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return new UserPrincipal(user);
        }

        // Nothing found
        throw new UsernameNotFoundException(
                "No user or admin found with email: " + email
        );
    }
}

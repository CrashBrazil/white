package com.byd.project.white.model;

import com.byd.project.white.model.enums.TipoCargo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAdmin;

    @NotNull
    private String emailAdmin;

    @NotNull
    private String nomeCompletoAdmin;

    @NotNull
    private String senhaAdmin;

    @NotNull
    private TipoCargo cargoAdmin;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargoAdmin == TipoCargo.CLIENTE){
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        else if(this.cargoAdmin == TipoCargo.VENDEDOR){
            return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(idAdmin, admin.idAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAdmin);
    }

    @Override
    public @Nullable String getPassword() {
        return this.senhaAdmin;
    }

    @Override
    public String getUsername() {
        return this.emailAdmin;
    }
}

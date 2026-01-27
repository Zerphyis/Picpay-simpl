package dev.Zerphyis.picpay.infra.persistance.entites;

import dev.Zerphyis.picpay.domain.entities.users.UsersType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_document", columnNames = "document"),
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false, length = 14)
    private String document;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UsersType usersType;

    public UsersEntity(
            String fullname,
            String document,
            String email,
            String password,
            BigDecimal balance,
            UsersType usersType
    ) {
        this.fullname = fullname;
        this.document = document;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.usersType = usersType;
    }

    public UsersEntity(){

    }
}

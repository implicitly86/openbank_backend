/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.domain.security;

import com.implicitly.domain.IdentifiedEntity;
import com.implicitly.domain.customer.Customer;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Доменная модель сущности <strong>Пользователь</strong>.
 *
 * @author Emil Murzakaev.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "`user`")
public class User implements IdentifiedEntity {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -3875868932204082043L;

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "sq_user", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Имя пользователя.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Пароль пользователя.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Учетная запись клиента.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Набор ролей пользователя.
     */
    @ManyToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private List<Role> roles;

}

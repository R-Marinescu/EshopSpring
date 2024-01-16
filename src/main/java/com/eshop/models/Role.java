package com.eshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getRoleId() {
        return roleId;
    }

    public String setRoleName(String roleType) {
        return this.roleName = roleType;
    }

    public String getRoleName() {
        return roleName;
    }
}

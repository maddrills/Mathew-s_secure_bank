package com.mathew.bank.Mathewbank.entity.commonEntity;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.persistence.*;

import java.util.Collection;

// create roles
@Entity
@Table(name = "roles")
final public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    //remember ROLE_
    @Column(name = "role_name")
    private String role;

    //all employees under this role
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    //The detach operation removes the entity from the persistent context. When we use CascadeType.DETACH, the child entity will also get removed from the persistent context.
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
            }
            //cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "emp_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    private Collection<Employee> employees;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
}

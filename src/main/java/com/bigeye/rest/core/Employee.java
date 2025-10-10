package com.bigeye.rest.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "status", nullable = false)
  private String status;

  @JsonIgnore
  @Nullable
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  private Employee manager;

  @JsonIgnore
  @OneToMany(mappedBy = "manager")
  private List<Employee> directReports;

  @Column(name = "salary_amount", nullable = false)
  private Double salary;

  // Needed by Hibernate
  public Employee() {
    this.directReports = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Nullable
  public Employee getManager() {
    return manager;
  }

  public void setManager(@Nullable Employee manager) {
    this.manager = manager;
  }

  public List<Employee> getDirectReports() {
    return directReports;
  }

  public void setDirectReports(List<Employee> directReports) {
    this.directReports = directReports;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }
}

package com.africahr.authservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String googleId;
  private String email;
  private String fullName;
  private String profilePicture;

  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role {
    STAFF,
    MANAGER,
    ADMIN
  }

}

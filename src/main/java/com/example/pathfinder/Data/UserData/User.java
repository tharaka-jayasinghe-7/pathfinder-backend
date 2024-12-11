package com.example.pathfinder.Data.UserData;

import com.example.pathfinder.Data.ApplyData.Apply;
import com.example.pathfinder.Data.InterviewData.Interview;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String profilePic;
    private String address;
    private Date dob;
    private int olPassCount;
    private String mobile;
    private String email;
    private String certification;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-apply")
    private List<Apply> applies;

}

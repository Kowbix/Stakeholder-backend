package com.example.Stakeholder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expireDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RegisterToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expireDate = setExpireDate();
    }

    private Date setExpireDate() {
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR_OF_DAY, 1);

        date = calendar.getTime();

        return date;
    }
}

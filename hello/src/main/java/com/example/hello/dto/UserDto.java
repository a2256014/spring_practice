package com.example.hello.dto;

import com.example.hello.annotation.YearMonthDay;
import com.example.hello.object_mapping.dto.Car;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;

    private int age;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "xxx-xxxx-xxxx 양식과 맞지 않습니다.")
    private String phoneNumber;

    @YearMonthDay
    private String reqYearMonth;    //yyyy-mm-dd

    @Valid  //object에는 따로 또 Valid적용 해야함
    private List<Car> cars;

//    @AssertTrue
//    public boolean isReqYearMonthValidation(){  //is 라는 이름으로 만들어야 작동함
//        try {
//            LocalDate localDate = LocalDate.parse(this.reqYearMonth, DateTimeFormatter.ofPattern("yyyyMMdd"));
//        }catch (Exception e){
//            return false;
//        }
//        return true;
//    }
}

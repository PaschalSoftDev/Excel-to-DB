package Practice.ExcelToDB.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BRANCHES")
@Getter
@Setter

public class ExcelToDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "NAME", nullable = false)
    private String name;

    @Column (name = "CODE", length = 3)
    private String code;

    @Column (name = "ADDRESS")
    private String address;

    @Column (name = "CITY")
    private String city;

    @Column (name = "EMAIL")
    private String email;

    @Column (name = "PHONE")
    private String phone;

    @Column (name = "HEAD_OFFICE")
    private boolean headOffice;

    @Column (name = "REGIONAL_OFFICE")
    private boolean regionalOffice;

    @Column (name = "SWIFT_BIC")
    private String swiftCode;

    @Override
    public  String toString () {
        return "Branch{" +
                "id=" + id +
                ", name='" + name +'\'' +
                ", code='" + code +'\'' +
                ", address='" + address +'\'' +
                ", city='" + city +'\'' +
                ", email='" + email +'\'' +
                ", phone='" + phone +'\'' +
                ", headOffice='" + headOffice +'\'' +
                ", regionalOffice='" + regionalOffice +'\'' +
                ", swiftCode='" + swiftCode+'\'' +
                '}';
    }

}



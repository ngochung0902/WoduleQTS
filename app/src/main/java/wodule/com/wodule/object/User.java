package wodule.com.wodule.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MyPC on 13/09/2017.
 */
public class User implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("middle_name")
    String middle_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("native_name")
    String native_name;
    @SerializedName("suffx")
    String suffx;
    @SerializedName("display")
    int display;
    @SerializedName("date_of_birth")
    String date_of_birth;
    @SerializedName("country_of_birth")
    String country_of_birth;
    @SerializedName("residence_address")
    String residence_address;
    @SerializedName("vd1")
    String vd1;
    @SerializedName("vd2")
    String vd2;
    @SerializedName("city")
    String city;
    @SerializedName("country")
    String country;
    @SerializedName("telephone")
    String telephone;
    @SerializedName("email")
    String email;
    @SerializedName("nationality")
    String nationality;
    @SerializedName("ethnility")
    String ethnility;
    @SerializedName("status")
    String status;
    @SerializedName("religion")
    String religion;
    @SerializedName("gender")
    String gender;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("code")
    String code;
    @SerializedName("picture")
    String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSuffx() {
        return suffx;
    }

    public void setSuffx(String suffx) {
        this.suffx = suffx;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNative_name() {
        return native_name;
    }

    public void setNative_name(String native_name) {
        this.native_name = native_name;
    }


    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCountry_of_birth() {
        return country_of_birth;
    }

    public void setCountry_of_birth(String country_of_birth) {
        this.country_of_birth = country_of_birth;
    }

    public String getResidence_address() {
        return residence_address;
    }

    public void setResidence_address(String residence_address) {
        this.residence_address = residence_address;
    }

    public String getVd1() {
        return vd1;
    }

    public void setVd1(String vd1) {
        this.vd1 = vd1;
    }

    public String getVd2() {
        return vd2;
    }

    public void setVd2(String vd2) {
        this.vd2 = vd2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEthnility() {
        return ethnility;
    }

    public void setEthnility(String ethnility) {
        this.ethnility = ethnility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}

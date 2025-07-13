package kr.co.myproject.dto.Famous;

import lombok.Getter;

@Getter
public class FamousDto {
    private String famousKr;

    private String author;

    public FamousDto(String famousKr, String author) {
        this.famousKr = famousKr;
        this.author = author;
    }
}

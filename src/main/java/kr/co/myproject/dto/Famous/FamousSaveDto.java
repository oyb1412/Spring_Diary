package kr.co.myproject.dto.Famous;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FamousSaveDto {
    @JsonProperty("q")
    private String famousEn;

    @JsonProperty("a")
    private String author;

    private String h;
}

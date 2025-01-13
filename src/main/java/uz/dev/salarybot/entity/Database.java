package uz.dev.salarybot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Database {

    private String object;

//    @JsonProperty(value = "results")
//    private List<Page> pages = new ArrayList<>();

    @JsonProperty("next_cursor")
    private Boolean nextCursor;

    @JsonProperty("has_more")
    private Boolean hasMore;


    @Override
    public String toString() {
        return "Database{" +
                "object='" + object + '\'' +
//                ", pages=" + pages +
                ", nextCursor=" + nextCursor +
                ", hasMore=" + hasMore +
                '}';
    }
}

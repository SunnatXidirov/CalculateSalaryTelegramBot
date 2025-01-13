package uz.dev.salarybot.dto;

import lombok.Getter;
import lombok.Setter;
import uz.dev.salarybot.entity.Page;

import java.util.List;
@Setter
@Getter
public class PageResponse {
    private List<Page> results;
}

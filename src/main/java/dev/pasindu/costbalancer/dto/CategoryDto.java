package dev.pasindu.costbalancer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.pasindu.costbalancer.util.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private String name;
    @JsonProperty("type")
    private Categories categories;
}

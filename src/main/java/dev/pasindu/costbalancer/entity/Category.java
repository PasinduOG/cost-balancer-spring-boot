package dev.pasindu.costbalancer.entity;

import dev.pasindu.costbalancer.util.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Categories categories;
    private String icon;
}

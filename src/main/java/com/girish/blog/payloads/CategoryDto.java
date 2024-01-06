package com.girish.blog.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "title should not be empty")
    private String categoryTitle;
    @NotEmpty(message = "description should not be empty")
    @Size(max = 100,message = "should be less thane 100 character ")
    private String categoryDescription;


}

package com.ad.blogpost.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "Must be between 2-20 characters")
    private String categoryTitle;

    private String categoryDescription;

}

package com.ad.blogpost.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String categoryTitle;
    private String categoryDescription;

}

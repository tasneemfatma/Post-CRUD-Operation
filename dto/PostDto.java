package com.crud.crud.operation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;

@NotBlank(message="title should not be blank")
@Size(message = "size should not be less than 10 characters")
    private String title;
    private String description;
}

package com.crud.crud.operation.dto;

import com.crud.crud.operation.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> postDtos;
    private int pageNo;
    private int pageSize;
    private long totalPage;
    private long totalElement;
    private boolean last;
}

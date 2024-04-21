package com.crud.crud.operation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {

    private Timestamp timestamp;
    private String message;
    private String description;

    public  ErrorResponse(Timestamp timestamp,String message, String description){
        this.timestamp= timestamp;
        this.message= message;
        this.description= description;
    }
}

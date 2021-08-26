package com.jandy.plogging.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGoogleMemberResponse {

    private Long id;

    public CreateGoogleMemberResponse(Long id){
        this.id=id;
    }

}

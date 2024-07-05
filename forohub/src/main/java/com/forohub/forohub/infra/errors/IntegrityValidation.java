package com.forohub.forohub.infra.errors;



public class IntegrityValidation extends RuntimeException{


    public IntegrityValidation(String message){
        super((message));

    }

}

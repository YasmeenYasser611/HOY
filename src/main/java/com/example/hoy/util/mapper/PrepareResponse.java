package com.example.hoy.util.mapper;
import com.example.hoy.model.GeneralResponse;

public class PrepareResponse {
    public static <T> GeneralResponse<T> prepareResponse(T data, String message, String code) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>();
        generalResponse.setMessage(message);
        generalResponse.setCode(code);
        generalResponse.setData(data);
        return generalResponse;
    }
}

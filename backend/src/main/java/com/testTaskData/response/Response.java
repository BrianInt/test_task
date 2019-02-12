package com.testTaskData.response;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus result;
    private Object payload;

    public static Response ok(Object payload){
        Response response = new Response(HttpStatus.OK);
        response.setPayload(payload);
        return response;
    }

    public static Response error(Object payload){
        Response response = new Response(HttpStatus.I_AM_A_TEAPOT);
        response.setPayload(payload);
        return response;
    }

    public Response(HttpStatus resultCode){
        setResult(resultCode);
    }

    public void setResult(HttpStatus result) {
        this.result = result;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public HttpStatus getResult() {
        return result;
    }

    public Object getPayload() {
        return payload;
    }
}

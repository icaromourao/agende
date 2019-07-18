package br.com.icaro.agende.service.listener;


import br.com.icaro.agende.model.JWToken;

public interface AuthenticationListener {

    void onSuccess(JWToken token);
    void onFailure(String errorMsg);
}

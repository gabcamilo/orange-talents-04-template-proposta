package br.com.zupacademy.gabriela.proposal.biometry;

import java.util.Base64;

public class CreateBiometryResponse {

    private String fingerprint;

    public CreateBiometryResponse(Biometry biometry) {
        this.fingerprint = Base64.getEncoder().encode(biometry.getFingerprint()).toString();
    }
}

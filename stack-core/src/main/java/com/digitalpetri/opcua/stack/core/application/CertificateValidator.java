package com.digitalpetri.opcua.stack.core.application;


import java.security.cert.X509Certificate;
import java.util.List;

import com.digitalpetri.opcua.stack.core.UaException;

public interface CertificateValidator {

    /**
     * Check that the provided certificate is valid.
     *
     * @param certificate the {@link X509Certificate} to check the validity of.
     * @throws UaException if {@code certificate} is invalid.
     */
    void validate(X509Certificate certificate) throws UaException;

    /**
     * Check that the provided certificate is trusted.
     *
     * @param certificate the {@link X509Certificate} to verify.
     * @param chain       the chain of intermediate certificates, if present, that lead to a trust anchor.
     * @throws UaException if {@code certificate} is not trusted.
     */
    void verifyTrustChain(X509Certificate certificate, List<X509Certificate> chain) throws UaException;

}

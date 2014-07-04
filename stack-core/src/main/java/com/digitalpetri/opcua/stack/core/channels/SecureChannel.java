package com.digitalpetri.opcua.stack.core.channels;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.interfaces.RSAPublicKey;

import com.digitalpetri.opcua.stack.core.StatusCodes;
import com.digitalpetri.opcua.stack.core.UaRuntimeException;
import com.digitalpetri.opcua.stack.core.security.SecurityAlgorithm;
import com.digitalpetri.opcua.stack.core.security.SecurityPolicy;
import com.digitalpetri.opcua.stack.core.types.builtin.ByteString;
import com.digitalpetri.opcua.stack.core.types.enumerated.MessageSecurityMode;
import com.digitalpetri.opcua.stack.core.util.DigestUtil;

public interface SecureChannel {

    KeyPair getKeyPair();
    Certificate getLocalCertificate();
    Certificate getRemoteCertificate();
    SecurityPolicy getSecurityPolicy();
    MessageSecurityMode getMessageSecurityMode();

    long getChannelId();
    long getCurrentTokenId();
    long getPreviousTokenId();
    ByteString getLocalNonce();
    ByteString getRemoteNonce();

    ChannelSecrets.SecretKeys getEncryptionKeys();
    ChannelSecrets.SecretKeys getDecryptionKeys();

    default ByteString getLocalCertificateBytes() {
        try {
            return getLocalCertificate() != null ?
                    ByteString.of(getLocalCertificate().getEncoded()) :
                    ByteString.NullValue;
        } catch (CertificateEncodingException e) {
            throw new UaRuntimeException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    default ByteString getLocalCertificateThumbprint() {
        try {
            return getLocalCertificate() != null ?
                    ByteString.of(DigestUtil.sha1(getLocalCertificate().getEncoded())) :
                    ByteString.NullValue;
        } catch (CertificateEncodingException e) {
            throw new UaRuntimeException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }


    default ByteString getRemoteCertificateBytes() {
        try {
            return getRemoteCertificate() != null ?
                    ByteString.of(getRemoteCertificate().getEncoded()) :
                    ByteString.NullValue;
        } catch (CertificateEncodingException e) {
            throw new UaRuntimeException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    default ByteString getRemoteCertificateThumbprint() {
        try {
            return getRemoteCertificate() != null ?
                    ByteString.of(DigestUtil.sha1(getRemoteCertificate().getEncoded())) :
                    ByteString.NullValue;
        } catch (CertificateEncodingException e) {
            throw new UaRuntimeException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    default int getLocalAsymmetricCipherTextBlockSize() {
        if (isAsymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Rsa15:
                case RsaOaep:
                    return (getAsymmetricKeyLength(getLocalCertificate()) + 1) / 8;
            }
        }

        return 1;
    }

    default int getRemoteAsymmetricCipherTextBlockSize() {
        if (isAsymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Rsa15:
                case RsaOaep:
                    return (getAsymmetricKeyLength(getRemoteCertificate()) + 1) / 8;
            }
        }

        return 1;
    }

    default int getLocalAsymmetricPlainTextBlockSize() {
        if (isAsymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Rsa15:
                    return (getAsymmetricKeyLength(getLocalCertificate()) + 1) / 8 - 11;
                case RsaOaep:
                    return (getAsymmetricKeyLength(getLocalCertificate()) + 1) / 8 - 42;
            }
        }

        return 1;
    }

    default int getRemoteAsymmetricPlainTextBlockSize() {
        if (isAsymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Rsa15:
                    return (getAsymmetricKeyLength(getRemoteCertificate()) + 1) / 8 - 11;
                case RsaOaep:
                    return (getAsymmetricKeyLength(getRemoteCertificate()) + 1) / 8 - 42;
            }
        }

        return 1;
    }

    default int getLocalAsymmetricSignatureSize() {
        SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricSignatureAlgorithm();

        switch (algorithm) {
            case RsaSha1:
            case RsaSha256:
                return (getAsymmetricKeyLength(getLocalCertificate()) + 1) / 8;
            default:
                return 0;
        }
    }

    default int getRemoteAsymmetricSignatureSize() {
        SecurityAlgorithm algorithm = getSecurityPolicy().getAsymmetricSignatureAlgorithm();

        switch (algorithm) {
            case RsaSha1:
            case RsaSha256:
                return (getAsymmetricKeyLength(getRemoteCertificate()) + 1) / 8;
            default:
                return 0;
        }
    }

    default boolean isAsymmetricSigningEnabled() {
        return getSecurityPolicy() != SecurityPolicy.None &&
                getLocalCertificate() != null;
    }

    default boolean isAsymmetricEncryptionEnabled() {
        return getSecurityPolicy() != SecurityPolicy.None &&
                getLocalCertificate() != null &&
                getRemoteCertificate() != null;
    }

    default int getSymmetricCipherTextBlockSize() {
        if (isSymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getSymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Aes128:
                case Aes256:
                    return 16;
            }
        }

        return 1;
    }

    default int getSymmetricPlainTextBlockSize() {
        if (isSymmetricEncryptionEnabled()) {
            SecurityAlgorithm algorithm = getSecurityPolicy().getSymmetricEncryptionAlgorithm();

            switch (algorithm) {
                case Aes128:
                case Aes256:
                    return 16;
            }
        }

        return 1;
    }

    default int getSymmetricSignatureSize() {
        SecurityAlgorithm algorithm = getSecurityPolicy().getSymmetricSignatureAlgorithm();

        switch (algorithm) {
            case HmacSha1:
                return 20;
            case HmacSha256:
                return 32;
            default:
                return 0;
        }
    }

    default int getSymmetricSignatureKeySize() {
        switch (getSecurityPolicy()) {
            case None:
                return 0;
            case Basic128Rsa15:
                return 16;
            case Basic256:
                return 24;
            case Basic256Sha256:
                return 32;
            default:
                return 0;
        }
    }

    default int getSymmetricEncryptionKeySize() {
        switch (getSecurityPolicy()) {
            case None:
                return 0;
            case Basic128Rsa15:
                return 16;
            case Basic256:
            case Basic256Sha256:
                return 32;
            default:
                return 0;
        }
    }

    default boolean isSymmetricSigningEnabled() {
        return getLocalCertificate() != null &&
                getSecurityPolicy() != SecurityPolicy.None &&
                (getMessageSecurityMode() == MessageSecurityMode.Sign ||
                        getMessageSecurityMode() == MessageSecurityMode.SignAndEncrypt);
    }

    default boolean isSymmetricEncryptionEnabled() {
        return getRemoteCertificate() != null &&
                getSecurityPolicy() != SecurityPolicy.None &&
                getMessageSecurityMode() == MessageSecurityMode.SignAndEncrypt;
    }

    static int getAsymmetricKeyLength(Certificate certificate) {
        PublicKey publicKey = certificate != null ?
                certificate.getPublicKey() : null;

        return (publicKey instanceof RSAPublicKey) ?
                ((RSAPublicKey) publicKey).getModulus().bitLength() : 0;
    }

}

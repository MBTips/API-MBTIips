package com.mbtips.common.configuration;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JasyptConfigurationTest {

    private final String password = "password";

    String jasyptEncrypt(String input) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(password);
        return encryptor.encrypt(input);
    }

    String jasyptDecrypt(String input){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(password);
        return encryptor.decrypt(input);
    }

    @Test
    @DisplayName("주어진 문자를 암호화, 복호화 후 동일한 문자열 반환")
    void giveStringAndPasswordWhenEncryptAndDecryptThenSameTrue(){
        // given
        String target = "target";

        // when
        String encrypt = this.jasyptEncrypt(target);
        String decrypt = this.jasyptDecrypt(encrypt);

        // then
        Assertions.assertEquals(target, decrypt);
    }
}
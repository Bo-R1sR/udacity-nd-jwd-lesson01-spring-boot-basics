package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int insertCredentialInDB(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String password = credential.getPassword();
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        return credentialMapper.insertCredentialInDB(new Credential(null, credential.getUrl(), credential.getUsername(), encodedKey, encryptedPassword, credential.getUserid()));
    }

    public List<Credential> getAllCredentialsByUserID(Integer userid) {
        return credentialMapper.getAllCredentialsByUserID(userid);
    }

    public void deleteCredentialByCredentialID(Integer credentialid) {
        credentialMapper.deleteCredentialByCredentialID(credentialid);
    }

    public int updateCredentialInDB(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        credential.setKey(Base64.getEncoder().encodeToString(key));
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credentialMapper.updateCredentialInDB(credential);
    }
}

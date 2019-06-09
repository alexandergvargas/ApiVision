package model;

import org.primefaces.model.UploadedFile;

public class model {
    private String token_acces="ya29.c.El_sBV5trI_1HrZeuroWj0o6Eg_nHmVr85Pw6oeuMfTu_tPkwfd41o7CfHv_MYmDChT9dOQYEHNwzt6rAVYzYG5XCuLpO3LdtQP1BSYp41Ct0t2g4jFwXLC45ohrvlM_-g";
    private String responses;
    private String nameFile;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getToken_acces() {
        return token_acces;
    }

    public void setToken_acces(String token_acces) {
        this.token_acces = token_acces;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    
}

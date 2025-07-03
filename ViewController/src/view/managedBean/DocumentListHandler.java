package view.managedBean;

public class DocumentListHandler {
    private String documentName;
    private String documentTitle;
    private String docCreationDate;
    private String dID;
    private String documentOriginalName;
    private String documentSize;
    private String documentSecurity;
    private String documentUrl;
    private String documentId;
    private String fileType;
    
    public DocumentListHandler(String documentName,String documentTitle,  String docCreationDate, String dID, String documentOriginalName, String documentSize, String documentSecurity, String documentUrl, String documentId, String fileType ) {
        super();
        this.documentName = documentName;
        this.documentTitle = documentTitle;
        this.docCreationDate =docCreationDate;
        this.dID = dID;
        this.documentOriginalName = documentOriginalName;
        this.documentSize =documentSize;
        this.documentSecurity = documentSecurity;
        this.documentUrl = documentUrl;
        this.documentId = documentId;
        this.fileType = fileType;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocCreationDate(String docCreationDate) {
        this.docCreationDate = docCreationDate;
    }

    public String getDocCreationDate() {
        return docCreationDate;
    }

    public void setDID(String dID) {
        this.dID = dID;
    }

    public String getDID() {
        return dID;
    }

    public void setDocumentOriginalName(String documentOriginalName) {
        this.documentOriginalName = documentOriginalName;
    }

    public String getDocumentOriginalName() {
        return documentOriginalName;
    }

    public void setDocumentSize(String documentSize) {
        this.documentSize = documentSize;
    }

    public String getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSecurity(String documentSecurity) {
        this.documentSecurity = documentSecurity;
    }

    public String getDocumentSecurity() {
        return documentSecurity;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}

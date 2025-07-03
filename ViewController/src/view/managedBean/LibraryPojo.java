package view.managedBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.apache.myfaces.trinidad.model.UploadedFile;
public class LibraryPojo implements Serializable {
    @SuppressWarnings("compatibility:-5580818880949747446")
    private static final long serialVersionUID = -5446968930675240240L;
    public LibraryPojo() {
        super();
    }
    private static Map<String, InputStream> uploadedDocuments = new HashMap<String, InputStream>();
    
        public static void setUploadedDocuments(Map<String, InputStream> uploadedDocuments) {
            LibraryPojo.uploadedDocuments = uploadedDocuments;
        }

        public static Map<String, InputStream> getUploadedDocuments() {
            return uploadedDocuments;
        }
}

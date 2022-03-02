package controller;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    private static Logger logger = LogManager.getLogger(AttachmentController.class);

    public static String getUploadPath(HttpSession session) {
        return session.getServletContext().getRealPath("webapp/WEB-INF") + "upload/";
    }

    /**
     * Find the attachment by attachment ID and prepare for download.
     * @param session
     * @param filename
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpSession session, @RequestParam("filename") String filename) throws IOException {
        String path = getUploadPath(session);
        File file = new File(path + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        logger.info("Download file: " + filename);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}

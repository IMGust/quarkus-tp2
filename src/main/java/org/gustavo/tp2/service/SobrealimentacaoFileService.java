package org.gustavo.tp2.service;

import java.io.IOException;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface SobrealimentacaoFileService {

    void salvar(Long id, FileUpload file) throws IOException;

    ArquivoDownload download(String fid);

    void remover(String fid);
}

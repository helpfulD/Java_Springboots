package com.hand.demo.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.domain.entity.SoLine;
import com.hand.demo.domain.repository.SoLineRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
@ImportService(templateCode = "HZERO-ORDER-1653-LINE")
public class LineImportServiceImpl implements IDoImportService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SoLineRepository soLineRepository;
    @Override
    public Boolean doImport(String data) {
        SoLine soLine;
        try {
            soLine = objectMapper.readValue(data, SoLine.class);
        } catch (IOException e) {
            return false;
        }
        soLineRepository.insertSelective(soLine);
        return true;
    }
}
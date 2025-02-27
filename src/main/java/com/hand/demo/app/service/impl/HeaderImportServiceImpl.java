package com.hand.demo.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.domain.entity.SoHeader;
import com.hand.demo.domain.repository.SoHeaderRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
@ImportService(templateCode = "HZERO-ORDER-1653")
public class HeaderImportServiceImpl implements IDoImportService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SoHeaderRepository soHeaderRepository;
    @Override
    public Boolean doImport(String data) {
        SoHeader soHeader;
        try {
            soHeader = objectMapper.readValue(data, SoHeader.class);
        } catch (IOException e) {
            return false;
        }

        soHeaderRepository.insertSelective(soHeader);
        return true;
    }
}
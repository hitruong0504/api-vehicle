package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.mapper.VehicleMapper;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.repository.VehicleRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] exportVehicleReport() throws JRException, IOException {
        List<VehicleResponse> vehicles = vehicleRepository.findAll().stream()
                .map(vehicleMapper::toResponse).toList();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehicles);
        Map<String, Object> parameters = new HashMap<>();

        Resource resource = resourceLoader.getResource("classpath:reports/vehicleReport.jrxml");
        if (!resource.exists()) {
            throw new IOException("Report template not found: reports/vehicleReport.jrxml");
        }

        try (InputStream reportStream = resource.getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new JRException("Error processing report: " + e.getMessage(), e);
        }
    }
}
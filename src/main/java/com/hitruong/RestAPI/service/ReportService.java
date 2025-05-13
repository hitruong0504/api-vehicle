package com.hitruong.RestAPI.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface ReportService {
    byte[] exportVehicleReport() throws JRException, IOException;
}

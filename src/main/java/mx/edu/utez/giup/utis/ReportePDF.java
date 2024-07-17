package mx.edu.utez.giup.utis;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportePDF {

    public void generarReporte(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Seleccionar una imagen de los assets (logo)
        try (FileInputStream archivo = new FileInputStream(req.getSession().getServletContext().getRealPath("img/portada.png"))) {
            // Obtener ubicación y bytes del reporte
            String report = "/WEB-INF/reporteF.jasper";
            File file = new File(req.getServletContext().getRealPath(report));
            try (InputStream input = new FileInputStream(file)) {
                // Colocar los parámetros del reporte
                Map<String, Object> mapa = new HashMap<>();
                mapa.put("Logo", archivo);

                // Obtener una conexión a los datos
                Connection con = null;
                try {
                    con = DatabaseConnectionManager.getConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                resp.setContentType("application/pdf"); // Establecer el tipo de respuesta
                resp.setHeader("Content-Disposition", "Attachment; filename=reporte.pdf"); // Forzar la descarga del archivo

                // Generar el reporte
                try {
                    byte[] bytes = JasperRunManager.runReportToPdf(input, mapa, con);
                    try (OutputStream os = resp.getOutputStream()) {
                        os.write(bytes);
                        os.flush();
                    }
                } catch (Exception e) {
                    throw new ServletException("Error al generar el reporte", e);
                }
            }
        }
    }
}
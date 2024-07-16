package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/generarReporte")
public class ReporteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generarReporte(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generarReporte(req, resp);
    }

    private void generarReporte(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Seleccionar una imagen de los assets (logo)
            String logoPath = req.getSession().getServletContext().getRealPath("/img/logo.png");
            File logoFile = new File(logoPath);
            FileInputStream archivo = new FileInputStream(logoFile);

            // Obtener ubicación y bytes del reporte
            String reportPath = req.getServletContext().getRealPath("/WEB-INF/reporteF.jasper");
            File reportFile = new File(reportPath);
            InputStream input = new FileInputStream(reportFile);

            // Colocar los parámetros del reporte
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("Logo", archivo);

            // Obtener una conexión a los datos
            Connection con = null;
            try {
                con = DatabaseConnectionManager.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Error al obtener conexión a la base de datos", e);
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
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                resp.getWriter().write("Error al generar el reporte: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }}
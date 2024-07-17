package mx.edu.utez.giup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import mx.edu.utez.giup.utis.DatabaseConnectionManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

    private void generarReporte(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        try {
            // Seleccionar una imagen de los assets (logo)
            String logoPath = req.getSession().getServletContext().getRealPath("/img/logo.PNG");
            if (logoPath == null || logoPath.isEmpty()) {
                throw new IOException("La ruta del logo es nula o está vacía");
            }

            File logoFile = new File(logoPath);
            if (!logoFile.exists()) {
                throw new IOException("El archivo del logo no existe en la ruta especificada: " + logoPath);
            }

            BufferedImage logo = ImageIO.read(logoFile);
            if (logo == null) {
                throw new IOException("Error al leer la imagen del logo");
            }

            // Convertir BufferedImage a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean success = ImageIO.write(logo, "png", baos);
            if (!success) {
                throw new IOException("Error al escribir la imagen en el ByteArrayOutputStream");
            }
            byte[] logoBytes = baos.toByteArray();
            baos.close();

            // Obtener ubicación y bytes del reporte
            String reportPath = req.getServletContext().getRealPath("/WEB-INF/reporteF.jasper");
            if (reportPath == null || reportPath.isEmpty()) {
                throw new IOException("La ruta del reporte es nula o está vacía");
            }

            File reportFile = new File(reportPath);
            if (!reportFile.exists()) {
                throw new IOException("El archivo del reporte no existe en la ruta especificada: " + reportPath);
            }

            try (InputStream reportStream = new FileInputStream(reportFile)) {

                // Colocar los parámetros del reporte
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Logo", logoBytes);

                // Obtener una conexión a los datos
                con = DatabaseConnectionManager.getConnection();

                // Configurar la respuesta
                resp.setContentType("application/pdf"); // Establecer el tipo de contenido
                resp.setHeader("Content-Disposition", "attachment; filename=reporte.pdf"); // Forzar la descarga del archivo

                // Generar el reporte
                byte[] bytes = JasperRunManager.runReportToPdf(reportStream, parameters, con);
                try (OutputStream os = resp.getOutputStream()) {
                    os.write(bytes);
                    os.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al manejar archivos: " + e.getMessage());
        } catch (SQLException | JRException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al obtener conexión a la base de datos: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
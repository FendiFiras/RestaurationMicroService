package org.esprit.reservationmicroservice.Services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.esprit.reservationmicroservice.Entities.Reservation;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

    public ByteArrayOutputStream exportReservationsToExcel(List<Reservation> reservations) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservations");

        // Création de l'en-tête
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Utilisateur ID", "Date", "Heure", "Nombre d'invités", "Status", "Détails"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Remplissage des données
        int rowNum = 1;
        for (Reservation reservation : reservations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reservation.getIdReservation());
            row.createCell(1).setCellValue(reservation.getUserId());
            row.createCell(2).setCellValue(reservation.getEventDate().toString());
            row.createCell(3).setCellValue(reservation.getEventTime().toString());
            row.createCell(4).setCellValue(reservation.getNumberOfGuests());
            row.createCell(5).setCellValue(reservation.getEventType().toString());
            row.createCell(6).setCellValue(reservation.getAdditionalDetails());
        }

        // Ajustement des tailles de colonnes
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Écriture dans un flux de sortie
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream;
    }
}

package org.esprit.reservationmicroservice.Services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.esprit.reservationmicroservice.Clients.UserClient;
import org.esprit.reservationmicroservice.Dto.User;
import org.esprit.reservationmicroservice.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

    @Autowired
    private UserClient userClient;

    public ByteArrayOutputStream exportReservationsToExcel(List<Reservation> reservations) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservations");

        // En-têtes complets
        String[] headers = {
                "ID", "Utilisateur ID", "Nom", "Prénom", "Date", "Heure",
                "Nombre d'invités", "Type d'événement", "Format", "Détails"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Remplissage
        int rowNum = 1;
        for (Reservation reservation : reservations) {
            Row row = sheet.createRow(rowNum++);
            User user = userClient.getUtilisateur(reservation.getUserId());

            row.createCell(0).setCellValue(reservation.getIdReservation());
            row.createCell(1).setCellValue(reservation.getUserId());
            row.createCell(2).setCellValue(user.getFirstName());
            row.createCell(3).setCellValue(user.getLastName());
            row.createCell(4).setCellValue(reservation.getEventDate() != null ? reservation.getEventDate().toString() : "N/A");
            row.createCell(5).setCellValue(reservation.getEventTime() != null ? reservation.getEventTime().toString() : "N/A");
            row.createCell(6).setCellValue(reservation.getNumberOfGuests());
            row.createCell(7).setCellValue(
                    reservation.getEventType() != null ? reservation.getEventType().toString() : "Non défini"
            );
            row.createCell(6).setCellValue(
                    reservation.getEventFormat() != null ? reservation.getEventFormat().toString() : "Non défini"
            );
            row.createCell(9).setCellValue(reservation.getAdditionalDetails());
        }

        // Ajustement automatique des colonnes
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream;
    }
}

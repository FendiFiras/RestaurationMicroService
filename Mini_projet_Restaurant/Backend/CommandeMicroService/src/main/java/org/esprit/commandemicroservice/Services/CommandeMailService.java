package org.esprit.commandemicroservice.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

@Service
@Slf4j
public class CommandeMailService {

@Autowired
     JavaMailSender mailSender;

    /**
     * Envoie un email contenant la facture PDF d'une commande à un utilisateur.
     *
     * @param toEmail         Email de destination
     * @param nomClient       Nom complet de l'utilisateur
     * @param pdfFile         Fichier PDF généré
     * @param numeroCommande  Numéro unique de la commande
     */
    public void sendFactureCommande(String toEmail, String nomClient, File pdfFile, String numeroCommande) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("📦 Facture de votre commande #" + numeroCommande);

            String body = "<div style='font-family: Arial, sans-serif; padding: 20px;'>"
                    + "<h2>Bonjour " + nomClient + ",</h2>"
                    + "<p>Merci pour votre commande chez <strong>CodingFactory</strong> ! 🍽️</p>"
                    + "<p>Vous trouverez en pièce jointe la facture de votre commande <strong>#" + numeroCommande + "</strong>.</p>"
                    + "<p style='color: #888;'>À bientôt !</p>"
                    + "<p><strong>Equipe CodingFactory</strong></p>"
                    + "</div>";

            helper.setText(body, true);
            helper.addAttachment("Facture_" + numeroCommande + ".pdf", new FileSystemResource(pdfFile));

            mailSender.send(message);
            log.info("✅ Mail avec facture envoyé à {}", toEmail);

        } catch (MessagingException e) {
            log.error("❌ Erreur lors de l'envoi de l'email de facture : {}", e.getMessage());
            throw new RuntimeException("Échec de l'envoi de la facture par email", e);
        }
    }
}

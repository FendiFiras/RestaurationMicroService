package org.esprit.commandemicroservice.Services;

import org.esprit.commandemicroservice.Dto.User;
import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Entites.ModePaiement;
import org.esprit.commandemicroservice.Entites.TypeCommande;
import org.esprit.commandemicroservice.Repository.CommandeRepo;
import org.esprit.commandemicroservice.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service

public class CommandeService implements ICommandeService {
    @Autowired

    CommandeRepo commandeRepository;


    private static final Logger log = LoggerFactory.getLogger(CommandeService.class);

    @Autowired
    UserClient userClient;
    @Autowired

    PlatServiceFake platServiceFake;
    @Autowired

    LivraisonServiceFake livraisonServiceFake;

    @Override
    public Commande saveCommande(Commande commande) {
        // Générer la date et le numéro de commande
        commande.setDateCommande(new Date());
        commande.setNumeroCommande("CMD-" + System.currentTimeMillis());

        // Si c'est une commande à emporter, on supprime l'id de livraison
        if (commande.getTypeCommande() == TypeCommande.A_EMPORTER) {
            commande.setIdLivraison(null);
        }

        // ✅ Calcul automatique du total selon les plats
        double total = 0.0;
        if (commande.getIdPlats() != null) {
            for (Long idPlat : commande.getIdPlats()) {
                total += platServiceFake.getPrixPlat(idPlat);
            }
        }
        commande.setTotal(total);

        return commandeRepository.save(commande);
    }


    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
    public Commande updateCommande(Long id, Commande updatedCommande) {
        Commande existing = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        existing.setDateCommande(updatedCommande.getDateCommande());
        existing.setStatut(updatedCommande.getStatut());
        existing.setTotal(updatedCommande.getTotal());
        existing.setIdUser(updatedCommande.getIdUser());
        existing.setIdLivraison(updatedCommande.getIdLivraison());
        existing.setIdPlats(updatedCommande.getIdPlats());
        existing.setModePaiement(updatedCommande.getModePaiement());
        existing.setNumeroCommande(updatedCommande.getNumeroCommande());
        existing.setTypeCommande(updatedCommande.getTypeCommande());

        return commandeRepository.save(existing);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
    public String generateCommandePdf(Long idCommande) throws Exception {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        log.info("📡 Appel Feign - Récupération du nom utilisateur pour id: {}", commande.getIdUser());
        User utilisateur = userClient.getUtilisateur(commande.getIdUser());
        String nomClient = utilisateur.getNom(); // utilise la méthode getNom() de ton DTO
        log.info("✅ Nom utilisateur récupéré: {}", nomClient);
        String nomLivreur = livraisonServiceFake.getNomLivreur(commande.getIdLivraison());
        List<String> nomsPlats = commande.getIdPlats().stream()
                .map(platServiceFake::getNomPlat)
                .toList();

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        String filePath = "facture_commande_" + commande.getIdCommande() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // ✅ Outer wrapper with border
        PdfPTable outerWrapper = new PdfPTable(1);
        outerWrapper.setWidthPercentage(100);
        outerWrapper.getDefaultCell().setBorder(Rectangle.BOX);
        outerWrapper.getDefaultCell().setPadding(15f);
        outerWrapper.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // ✅ Inner content table
        PdfPTable innerContent = new PdfPTable(1);
        innerContent.setWidthPercentage(100);

        // 🔷 Header
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.WHITE);
        PdfPCell titleCell = new PdfPCell(new Phrase("CODINGFACTORY - Facture de commande", titleFont));
        titleCell.setBackgroundColor(new BaseColor(0, 102, 204));
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setPadding(12f);
        titleCell.setBorder(Rectangle.NO_BORDER);
        innerContent.addCell(titleCell);

        // 👤 Infos client/commande
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(10f);
        infoTable.setSpacingAfter(10f);
        Font infoFont = new Font(Font.FontFamily.HELVETICA, 11);

        PdfPCell left = new PdfPCell();
        left.setBorder(Rectangle.NO_BORDER);
        left.addElement(new Paragraph("Client : " + nomClient, infoFont));
        left.addElement(new Paragraph("Type : " + commande.getTypeCommande(), infoFont));
        left.addElement(new Paragraph("Paiement : " + commande.getModePaiement(), infoFont));

        PdfPCell right = new PdfPCell();
        right.setBorder(Rectangle.NO_BORDER);
        right.addElement(new Paragraph("Date : " + commande.getDateCommande(), infoFont));
        right.addElement(new Paragraph("N° : " + commande.getNumeroCommande(), infoFont));
        right.addElement(new Paragraph("Livreur : " + nomLivreur, infoFont));

        infoTable.addCell(left);
        infoTable.addCell(right);

        PdfPCell infoWrapper = new PdfPCell(infoTable);
        infoWrapper.setBorder(Rectangle.NO_BORDER);
        innerContent.addCell(infoWrapper);

        // 🍽 Plats commandés
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell headerPlat = new PdfPCell(new Phrase("Plats commandés"));
        headerPlat.setBackgroundColor(BaseColor.LIGHT_GRAY);
        headerPlat.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerPlat.setPadding(8f);
        table.addCell(headerPlat);

        for (String plat : nomsPlats) {
            PdfPCell platCell = new PdfPCell(new Phrase(plat));
            platCell.setPadding(5f);
            table.addCell(platCell);
        }

        PdfPCell tableWrapper = new PdfPCell(table);
        tableWrapper.setBorder(Rectangle.NO_BORDER);
        innerContent.addCell(tableWrapper);

        // 💰 Total
        Font totalFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        Paragraph total = new Paragraph("Total à payer : " + commande.getTotal() + " DT", totalFont);
        total.setAlignment(Element.ALIGN_RIGHT);

        PdfPCell totalCell = new PdfPCell();
        totalCell.addElement(total);
        totalCell.setBorder(Rectangle.NO_BORDER);
        innerContent.addCell(totalCell);

        // 🙏 Footer
        Font footFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
        Paragraph footer = new Paragraph("Merci pour votre confiance. © CodingFactory", footFont);
        footer.setAlignment(Element.ALIGN_CENTER);

        PdfPCell footerCell = new PdfPCell();
        footerCell.setBorder(Rectangle.NO_BORDER);
        footerCell.addElement(footer);
        footerCell.setPaddingTop(15f);
        innerContent.addCell(footerCell);

        // ✅ Embed content inside border
        outerWrapper.addCell(new PdfPCell(innerContent) {{
            setBorder(Rectangle.NO_BORDER);
        }});

        document.add(outerWrapper);
        document.close();

        return filePath;
    }
    @Override
    public List<Commande> getCommandesSortedByTotal(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return commandeRepository.findAllByOrderByTotalAsc();
        } else if ("desc".equalsIgnoreCase(order)) {
            return commandeRepository.findAllByOrderByTotalDesc();
        } else {
            throw new IllegalArgumentException("Valeur 'order' invalide. Utilisez 'asc' ou 'desc'.");
        }
    }
    public List<Commande> findByModePaiement(ModePaiement modePaiement) {
        return commandeRepository.findByModePaiement(modePaiement);
    }
    @Override
    public List<Commande> saveCommandePartagee(List<Map<String, Object>> participants) {
        List<Commande> commandes = new ArrayList<>();

        for (Map<String, Object> participant : participants) {
            Long idUser = Long.valueOf(participant.get("idUser").toString());
            List<Integer> idPlatsRaw = (List<Integer>) participant.get("idPlats");
            Long idLivraison = Long.valueOf(participant.get("idLivraison").toString());

            List<Long> idPlats = idPlatsRaw.stream()
                    .map(Long::valueOf)
                    .toList();

            double total = idPlats.stream()
                    .mapToDouble(id -> platServiceFake.getPrixPlat(id))
                    .sum();

            Commande commande = new Commande();
            commande.setIdUser(idUser);
            commande.setIdPlats(idPlats);
            commande.setIdLivraison(idLivraison);

            commande.setTotal(total);
            commande.setDateCommande(new Date());
            commande.setStatut("EN_ATTENTE_VALIDATION");
            commande.setNumeroCommande("CMD-" + System.currentTimeMillis());
            commande.setModePaiement(ModePaiement.CARTE); // ou autre par défaut
            commande.setTypeCommande(TypeCommande.LIVRAISON); // ou autre

            commandes.add(commandeRepository.save(commande));
        }

        return commandes;
    }

}
























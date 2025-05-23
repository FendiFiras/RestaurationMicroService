package org.esprit.commandemicroservice.Services;


import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Entites.ModePaiement;

import java.util.List;
import java.util.Map;

public interface ICommandeService {
    Commande saveCommande(Commande commande);
    List<Commande> getAllCommandes();
    Commande getCommandeById(Long id);
    void deleteCommande(Long id);
   // List<Commande> getCommandesByUserId(Long idUser);
    String generateCommandePdf(Long idCommande) throws Exception;
    Commande updateCommande(Long id, Commande updatedCommande);
    List<Commande> getCommandesSortedByTotal(String order);
    List<Commande> findByModePaiement(ModePaiement modePaiement);
    List<Commande> saveCommandePartagee(List<Map<String, Object>> participants) ;
}
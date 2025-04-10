package org.esprit.commandemicroservice.Services;


import org.esprit.commandemicroservice.Entites.Commande;

import java.util.List;

public interface ICommandeService {
    Commande saveCommande(Commande commande);
    List<Commande> getAllCommandes();
    Commande getCommandeById(Long id);
    void deleteCommande(Long id);
   // List<Commande> getCommandesByUserId(Long idUser);
    String generateCommandePdf(Long idCommande) throws Exception;
    Commande updateCommande(Long id, Commande updatedCommande);

}
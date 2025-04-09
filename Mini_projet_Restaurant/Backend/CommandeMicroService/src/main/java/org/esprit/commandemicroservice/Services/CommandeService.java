package org.esprit.commandemicroservice.Services;

import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Repository.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service

public class CommandeService implements ICommandeService {
    @Autowired

     CommandeRepo commandeRepository;

    @Override
    public Commande saveCommande(Commande commande) {


        commande.setDateCommande(new Date());

        // Génération d'un numéro unique
        String numero = "CMD-" + System.currentTimeMillis();
        commande.setNumeroCommande(numero);

        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    }
























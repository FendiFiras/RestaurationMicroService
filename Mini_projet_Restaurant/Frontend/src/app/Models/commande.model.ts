export enum ModePaiement {
    CARTE = 'CARTE',
    ESPECES = 'ESPECES',
    PAYPAL = 'PAYPAL'
  }
  
  export enum TypeCommande {
    A_EMPORTER = 'A_EMPORTER',
    LIVRAISON = 'LIVRAISON'
  }
  
  export interface Commande {
    idCommande?: number; // optionnel si généré par le backend
    dateCommande: string; // format ISO 'YYYY-MM-DD'
    statut: string;
    total: number;
  
    idUser: number;
    idLivraison: number;
    idPlats: number[];
  
    modePaiement: ModePaiement;
    typeCommande: TypeCommande;
  
    numeroCommande: string;
  
    
  }
  
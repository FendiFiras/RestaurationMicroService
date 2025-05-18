package org.esprit.menumicroservice.Entities;

import org.springframework.data.jpa.domain.Specification;

public class PlatSpecification {

    // Recherche par nom
    public static Specification<Plat> hasNom(String nom) {
        return (root, query, builder) -> nom == null ? builder.conjunction() : builder.like(root.get("nom"), "%" + nom + "%");
    }

    // Recherche par prix maximum
    public static Specification<Plat> hasPrixMax(Double prixMax) {
        return (root, query, builder) -> prixMax == null ? builder.conjunction() : builder.lessThanOrEqualTo(root.get("prix"), prixMax);
    }
}


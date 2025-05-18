package org.esprit.menumicroservice.Services;

import org.esprit.menumicroservice.Entities.Menu;
import org.esprit.menumicroservice.Repo.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    // New method to update a menu
    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu != null) {
            menu.setNom(menuDetails.getNom());
            menu.setDescription(menuDetails.getDescription());
            return menuRepository.save(menu);
        }
        return null;  // Return null if the menu with the given ID doesn't exist
    }
}

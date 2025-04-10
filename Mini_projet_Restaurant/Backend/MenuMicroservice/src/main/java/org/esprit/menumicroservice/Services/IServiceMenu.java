package org.esprit.menumicroservice.Services;



import org.esprit.menumicroservice.Entities.Menu;

import java.util.List;

public interface IServiceMenu {
    List<Menu> getAllMenus();
    Menu createMenu(Menu menu);
    Menu getMenuById(Long id);
    void deleteMenu(Long id);
}

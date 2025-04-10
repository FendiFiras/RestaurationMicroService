package org.esprit.menumicroservice.Controllers;

import org.esprit.menumicroservice.Entities.Menu;
import org.esprit.menumicroservice.Services.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("GetAllMenu")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @PostMapping("AddNewMenu")
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @GetMapping("GetMenu/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @DeleteMapping("DeleteMenu/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }

    // New endpoint to update a menu
    @PutMapping("UpdateMenu/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        return menuService.updateMenu(id, menuDetails);
    }

}

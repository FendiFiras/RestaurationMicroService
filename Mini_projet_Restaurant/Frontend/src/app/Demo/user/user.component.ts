import { Component, OnInit } from '@angular/core';
import { UserService } from '../../Services/user.service';
import { user } from '../../Models/user.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from "../../elments/navbar/navbar.component";

@Component({
  selector: 'app-user',
  standalone: true,
  templateUrl: './user.component.html',

  styleUrls: ['./user.component.css'],
  
  imports: [FormsModule, CommonModule, HttpClientModule, RouterModule]
})
export class UserComponent implements OnInit {
  users: user[] = [];
  selectedUser: user = {} as user;
  newUser: user = {} as user;
  searchEmail: string = '';
  searchRole: string = '';
  genderStats: { [key: string]: number } = {};
  totalUsers: number = 0;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private userService: UserService) {}

  async ngOnInit(): Promise<void> {
    await this.loadData();
  }

  async loadData(): Promise<void> {
    this.isLoading = true;
    this.errorMessage = '';
    
    try {
      await Promise.all([
        this.loadAllUsers(),
        this.loadGenderStats(),
        this.loadTotalUsers()
      ]);
    } catch (error) {
      this.errorMessage = 'Erreur lors du chargement des données';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  async loadAllUsers(): Promise<void> {
    try {
      this.users = await this.userService.getAllUsers();
    } catch (error) {
      throw error;
    }
  }

  async loadGenderStats(): Promise<void> {
    try {
      this.genderStats = await this.userService.getGenderStats();
    } catch (error) {
      throw error;
    }
  }

  async loadTotalUsers(): Promise<void> {
    try {
      this.totalUsers = await this.userService.countUsers();
    } catch (error) {
      throw error;
    }
  }

  async getUserById(id: number): Promise<void> {
    this.isLoading = true;
    try {
      this.selectedUser = await this.userService.getUserById(id);
    } catch (error) {
      this.errorMessage = 'Erreur lors du chargement de l\'utilisateur';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  async searchByEmail(): Promise<void> {
    if (!this.searchEmail) return;

    this.isLoading = true;
    try {
      const user = await this.userService.getUserByEmail(this.searchEmail);
      this.users = user ? [user] : [];
      this.selectedUser = {} as user;
    } catch (error) {
      this.errorMessage = 'Erreur lors de la recherche';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  async createUser(): Promise<void> {
    this.isLoading = true;
    try {
      await this.userService.createUser(this.newUser);
      await this.loadData();
      this.resetNewUser();
    } catch (error) {
      this.errorMessage = 'Erreur lors de la création';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  async updateUser(): Promise<void> {
    if (!this.selectedUser.idUser) return;

    this.isLoading = true;
    try {
      await this.userService.updateUser(this.selectedUser.idUser, this.selectedUser);
      await this.loadData();
      this.selectedUser = {} as user;
    } catch (error) {
      this.errorMessage = 'Erreur lors de la mise à jour';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  async deleteUser(id: number): Promise<void> {
    if (!confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) return;

    this.isLoading = true;
    try {
      await this.userService.deleteUser(id);
      await this.loadData();
      this.selectedUser = {} as user;
    } catch (error) {
      this.errorMessage = 'Erreur lors de la suppression';
      console.error(error);
    } finally {
      this.isLoading = false;
    }
  }

  resetNewUser(): void {
    this.newUser = {
      idUser: undefined,
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      password: '',
      gender: undefined,
      email: '',
      phoneNumber: '',
      role: undefined
    } as user;
  }

  objectKeys(obj: any): string[] {
    return Object.keys(obj);
  }
}
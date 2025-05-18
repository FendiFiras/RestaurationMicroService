export enum Gender{
    MALE ='MALE',
    FEMALE='FEMALE',
    OTHER = 'OTHER'
}
export enum Role{
    ADMIN ='ADMIN',
    CLIENT='CLIENT',
    ORGANIZER='ORGANIZER'
}


export class user{
    idUser?:number;
    firstName?:string;
    lastName?:string;
    dateOfBirth?:string;
    password?:string;
    gender?:Gender;
    email?:string;
    phoneNumber?:string;
    role?:Role;
    
}


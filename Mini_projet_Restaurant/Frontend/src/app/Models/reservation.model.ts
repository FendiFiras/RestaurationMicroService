import { user } from "./user.model";

export enum EventType{
    ANNIVERSARY='ANNIVERSARY',
    WEDDING='WEDDING',
    CORPORATE='CORPORATE',
    OTHER='OTHER'
}
export enum EventFormat{
    INDOOR='INDOOR',
    OUTDOOR='OUTDOOR',
    VIRTUAL='VIRTUAL'
}


export class Reservation{
    idReservation?:number
    numberOfGuests?:number;
    eventDate?:string;
    eventTime?:string;
    isTimeFlexible?:boolean;
    eventType?:EventType;
    eventFormat?:EventFormat;
    additionalDetails?:string;
    idUser?:user;
    
}
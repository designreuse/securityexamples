import { User } from "./user";

export class LaboratoryResult{

    id: number;
    valueA: number;
    valueB?: number;
    valueC?: String;
    valueD?: boolean;

    patient?: User;
    
}
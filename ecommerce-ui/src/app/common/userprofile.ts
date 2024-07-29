import { UserType } from "./user-type";

export class UserProfile {
  constructor(
    public id: number,
    public  username: string,
    public  firstname: string,
    public  lastname: string,
    public  email: string,
    public  address: string,
    public  cellphone: string,
    public  userType: UserType
  ){

  }
}

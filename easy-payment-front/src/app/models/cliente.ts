export class Cliente {
  id: number;
  nome: string;
  email: string;
  cpf: string;
  rg: string;
  endereco: string;
  renda: string;
  senha: string;

  constructor(id: number, nome:string, email:string, cpf: string, rg: string,  endereco: string, renda: string, senha: string){
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.rg = rg;
    this.endereco = endereco;
    this.renda = renda;
    this.senha = senha;
  }
}

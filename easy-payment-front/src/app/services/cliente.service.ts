import { HttpClient, HttpClientModule } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { environment } from "src/environments/environment"
import { Cliente } from "../models/cliente"
@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  clienteURL = environment.clienteURL;

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Cliente[]> {
    return this.httpClient.get<Cliente[]>(this.clienteURL);
  }

  public write(cliente: Cliente): Observable<Cliente>{
    return this.httpClient.post<Cliente> (this.clienteURL, cliente);
  }

  public delete (id:number): Observable<Cliente>{
    return this.httpClient.delete<Cliente>(this.clienteURL+ `${id}`);
  }
}

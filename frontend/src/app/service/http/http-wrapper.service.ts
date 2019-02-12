import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class HttpWrapperService {
  constructor(private http: HttpClient) {}

  post(url: string, body: any) {
    return this.http.post(url, body);
  }

  get(url: string) {
    return this.http.get(url);
  }
}

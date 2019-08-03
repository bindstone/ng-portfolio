import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) {
  }

  public generateTestData(): Observable<any> {
    return this.http.get("/api/admin/gen-test-data")
  }
}

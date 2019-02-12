import {Injectable} from '@angular/core';
import {HttpWrapperService} from '../http/http-wrapper.service';
import {TestData} from '../../model/test-data';
import {Observable, throwError} from 'rxjs';
import {GlobalConst} from '../../globals/global-const';
import {catchError, map} from 'rxjs/internal/operators';

@Injectable()
export class TestDateService {
  constructor(private httpWrapper: HttpWrapperService) {}

  send(data: TestData): Observable<TestData> {
    return this.httpWrapper.post(`${GlobalConst.SERVER_ADDR}api/saveData`, data).pipe(
      map((response: any) => {
        if (response.result !== GlobalConst.RESPONSE_RESULT_SUCCESS) {
          throw response.payload;
        }
        return response.payload;
      }),
      catchError((response: any) => {
        console.log((response));
        if (response.error) {
          return throwError(response.error.message);
        }
        return throwError(response);
      })
    );
  }

  get(): Observable<TestData[]> {
    return this.httpWrapper.get(`${GlobalConst.SERVER_ADDR}api/getData`).pipe(
      map((response: any) => {
        if (response.result !== GlobalConst.RESPONSE_RESULT_SUCCESS) {
          throw response.payload;
        }
        return response.payload;
      }),
      catchError((response: any) => {
        if (response.error) {
          return throwError(response.error.message);
        }
        return throwError(response);
      })
    );
  }
}

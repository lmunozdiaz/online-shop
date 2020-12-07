import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    apiUrl = "http://localhost:8080/api"

    public nameTerms = new Subject<string>();
    public name$ = this.nameTerms.asObservable();
    constructor(private http: HttpClient) {
        const memo = localStorage.getItem('currentUser');
    }

    getOneTimeCode(emailId: string): Observable<string> {

        // the modified url for the backend endpoint
        const searchUrl = `${this.apiUrl}/users/getOnetimeCode/${emailId}`;

        return this.http.get<string>(searchUrl);

    }


    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            console.log(error); // log to console instead

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}
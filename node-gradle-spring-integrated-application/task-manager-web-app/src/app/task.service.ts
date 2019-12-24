import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from './task';


@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseUrl = 'http://localhost:8080/task-manager/v1/task';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor( private http : HttpClient) { }


  getTask(taskId: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/${taskId}`);
  }

  createTask(task: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, task);
  }

  updateTask(taskId: String, task: Task): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${taskId}`, task);
  }
  
  deleteTask(taskId: String): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${taskId}`, { responseType: 'text' });
  }

  completeTask(taskId: String): Observable<any> {
    return this.http.patch(`${this.baseUrl}/complete-task/${taskId}`,'{"taskStatus":"COMPLETED"}',this.httpOptions);
  }

  getTaskList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getTaskListMinimal(): Observable<any> {
    return this.http.get(`${this.baseUrl}/minimal`);
  }
}

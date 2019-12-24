import { TaskDetailsComponent } from '../task-details/task-details.component';
import { TaskService } from "../task.service";
import { Task } from "../task";

import { Observable } from "rxjs";
import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { NgFormSelectorWarning } from '@angular/forms';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})

export class TaskListComponent implements OnInit {

  tasks : Observable<Task[]>;
  searchByTaskName : string;
  searchByTaskPName : string;
  searchByStartDate : string;
  searchByEndDate : string;
  searchByFromPriority : string;
  searchByToPriority : string;

  constructor( private taskService : TaskService,private router : Router ) {
   
  }

  ngOnInit() {
    console.log("ngOnInit");
    this.reloadData();  
  }



  taskDetails(taskId : String){
    this.router.navigate(['details',taskId])
  }

  taskUpdate(taskId : String){
    this.router.navigate(['updateTask',taskId])
  }
  
  deleteTask(taskId : String){
    this.taskService.deleteTask(taskId).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      }
    )
  }

  reloadData() {
    this.tasks = this.taskService.getTaskList();
    }

  completeTask(taskId : String){
    this.taskService.completeTask(taskId).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      }
    )
  }

}

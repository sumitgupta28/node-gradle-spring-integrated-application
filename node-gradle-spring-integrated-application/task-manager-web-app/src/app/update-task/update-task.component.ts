import { Component, OnInit } from '@angular/core';
import { Task } from '../task';
import { TaskService } from '../task.service';
import { Router , ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  taskId : String;
  task : Task;
  submitted = false;
  tasks : Observable<Task[]>;
  taskUpdateFrom: FormGroup;
  
  constructor(private route: ActivatedRoute,private router: Router,
    private taskService: TaskService) { }

    reloadMinimalData() {
      this.tasks = this.taskService.getTaskList();
    }

  ngOnInit() {
    this.reloadMinimalData();

    this.task = new Task();
    this.taskId = this.route.snapshot.params['taskId'];
    this.taskService.getTask(this.taskId).subscribe(
      data => {
        console.log(data);
        this.task=data;
      }, error => console.log(error));

  }

  save() {
    this.taskService.updateTask(this.taskId,this.task)
      .subscribe(data => console.log(data), error => console.log(error));
    this.gotoList();
  }



  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/tasks']);
  }

}

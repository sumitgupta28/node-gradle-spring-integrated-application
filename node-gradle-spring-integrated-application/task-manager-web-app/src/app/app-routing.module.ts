import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { TaskDetailsComponent } from './task-details/task-details.component';
import { CreateTaskComponent } from './create-task/create-task.component';
import { TaskListComponent } from './task-list/task-list.component';
import { UpdateTaskComponent } from './update-task/update-task.component';


const routes: Routes = [
  { path: 'tasks', component: TaskListComponent },
  { path: 'addTask', component: CreateTaskComponent },
  { path: 'details/:taskId', component: TaskDetailsComponent },
  { path: 'updateTask/:taskId', component: UpdateTaskComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    onSameUrlNavigation : "reload"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }

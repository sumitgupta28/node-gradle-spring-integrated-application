import { PipeTransform, Pipe } from '@angular/core';
import { Task } from '../task';
@Pipe({
    name: "taskFilter"
})
export class TaskFilterPipe implements PipeTransform {
    transform(tasks: Task[], searchByTaskName : string,
        searchByTaskPName : string,
        searchByFromPriority : string,
        searchByToPriority : string,
        searchByStartDate : string,
        searchByEndDate : string): Task[] {
         
            if (tasks && tasks.length){
                return tasks.filter(task => 
                    {
                        if(searchByTaskName && task.task.toLowerCase().indexOf(searchByTaskName.toLowerCase()) === -1){ return false} 
                        if(searchByTaskPName && task.parentTask && (task.parentTask.toLowerCase().indexOf(searchByTaskPName.toLowerCase()) === -1)){ return false} 
                        if(searchByFromPriority && (parseInt(task.priority) < parseInt(searchByFromPriority)) ){ return false} 
                        if(searchByToPriority && (parseInt(task.priority) > parseInt(searchByToPriority)) ){ return false} 
                        if(searchByStartDate && new Date(task.startDate).getTime >= new Date(searchByToPriority).getTime ){ return false} 
                        if(searchByEndDate && new Date(task.endDate).getTime <= new Date(searchByToPriority).getTime ){ return false} 

                        return true;
                    }
                    
                )
            }else
            return tasks;
        }
}
